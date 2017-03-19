package network_tools;

import com.sun.org.apache.xpath.internal.SourceTree;
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
                            network = new Network(element.getNodeValue());
                            break;
                        case "ville":
                            int longitude = Integer.parseInt(element.getElementsByTagName("longitude").item(0).getTextContent().replace(" ", ""));
                            int latitude = Integer.parseInt(element.getElementsByTagName("latitude").item(0).getTextContent().replace(" ", ""));
                            String name = element.getElementsByTagName("nom").item(0).getTextContent().replace(" ", "");
                            if (network != null) {
                                network.addCity(new City(name, longitude, latitude));
                            }else{
                                System.out.println("format file error: no title");
                                System.exit(1);
                            }
                            break;
                        case "liaison":
                            String city1 = element.getElementsByTagName("vil_1").item(0).getTextContent().replace(" ", "");
                            String city2 = element.getElementsByTagName("vil_2").item(0).getTextContent().replace(" ", "");
                            Integer distance = Integer.parseInt(element.getElementsByTagName("temps").item(0).getTextContent().replace(" ", ""));
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

    public static void saveNetwork(Network network) {

    }


}
