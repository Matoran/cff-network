package network_tools;

import objects.City;
import objects.Network;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by matoran on 3/15/17.
 */
public class XmlHelper {
    public static Network loadNetwork(String path) {
        Network network = null;
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(path));
            final Element racine = document.getDocumentElement();
            final NodeList racineNoeuds = racine.getChildNodes();

            for (int i = 0; i < racineNoeuds.getLength(); i++) {

                if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element element = (Element) racineNoeuds.item(i);
                    switch (element.getNodeName()) {
                        case "titre":
                            network = new Network(element.getTextContent().trim());
                            break;
                        case "ville":
                            int longitude = Integer.parseInt(element.getElementsByTagName("longitude").item(0).getTextContent().trim());
                            int latitude = Integer.parseInt(element.getElementsByTagName("latitude").item(0).getTextContent().trim());
                            String name = element.getElementsByTagName("nom").item(0).getTextContent().trim();
                            if (network != null) {
                                network.addCity(new City(name, longitude, latitude));
                            }else{
                                System.out.println("format file error: no title");
                                System.exit(1);
                            }
                            break;
                        case "liaison":
                            String city1 = element.getElementsByTagName("vil_1").item(0).getTextContent().trim();
                            String city2 = element.getElementsByTagName("vil_2").item(0).getTextContent().trim();
                            Integer distance = Integer.parseInt(element.getElementsByTagName("temps").item(0).getTextContent().trim());
                            if (network != null) {
                                network.addConnection(city1, city2, distance);
                            }else{
                                System.out.println("format file error: no title");
                                System.exit(1);
                            }
                            break;
                        default:
                            System.out.println("error unknow type");
                            System.exit(1);

                    }
                }

            }
        } catch (final ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return network;
    }

    public static void saveNetwork(Network network, String path) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.newDocument();
            final Element racine = document.createElement("reseau");
            document.appendChild(racine);
            final Element title = document.createElement("titre");
            title.appendChild(document.createTextNode(network.getName()));
            racine.appendChild(title);
            for (City city : network.getCities()) {
                final Element cityElement = document.createElement("ville");
                final Element cityName = document.createElement("nom");
                cityName.appendChild(document.createTextNode(city.getName()));
                final Element cityLongitude = document.createElement("longitude");
                cityLongitude.appendChild(document.createTextNode(String.valueOf(city.getLongitude())));
                final Element cityLatitude = document.createElement("latitude");
                cityLatitude.appendChild(document.createTextNode(String.valueOf(city.getLatitude())));
                cityElement.appendChild(cityName);
                cityElement.appendChild(cityLongitude);
                cityElement.appendChild(cityLatitude);
                racine.appendChild(cityElement);
            }
            for (int line = 0; line < network.getDistanceMatrix().length-1; line++) {
                for (int column = line+1; column < network.getDistanceMatrix()[line].length; column++) {
                    if(network.getDistanceMatrix()[line][column] != Integer.MAX_VALUE){
                        final Element connectionElement = document.createElement("liaison");
                        final Element vil1 = document.createElement("vil_1");
                        vil1.appendChild(document.createTextNode(network.getCities().get(line).getName()));
                        final Element vil2 = document.createElement("vil_2");
                        vil2.appendChild(document.createTextNode(network.getCities().get(column).getName()));
                        final Element distance = document.createElement("temps");
                        distance.appendChild(document.createTextNode(String.valueOf(network.getDistanceMatrix()[line][column])));
                        connectionElement.appendChild(vil1);
                        connectionElement.appendChild(vil2);
                        connectionElement.appendChild(distance);
                        racine.appendChild(connectionElement);
                    }
                }
            }

            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer();
            final DOMSource source = new DOMSource(document);
            final StreamResult output = new StreamResult(new File(path));
            //formatage
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            //sortie
            transformer.transform(source, output);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();

        }

    }


}
