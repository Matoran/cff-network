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
                System.out.println(racineNoeuds.item(i).getNodeName());
                if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element element = (Element) racineNoeuds.item(i);
                    switch (element.getNodeName()) {
                        case "titre":
                            network = new Network(element.getNodeValue());
                            break;
                        case "ville":
                            int longitude = Integer.parseInt(element.getAttribute("longitude"));
                            int latitude = Integer.parseInt(element.getAttribute("latitude"));
                            if (network != null){
                                network.addCity(new City(element.getAttribute("nom"), longitude, latitude));
                            } else {
                                System.out.println("network not initialized");
                            }
                            break;
                        case "liaison":

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


        //Network network = new Network();
        return new Network("bonjour");
    }

    public static void saveNetwork(Network network) {

    }


}
