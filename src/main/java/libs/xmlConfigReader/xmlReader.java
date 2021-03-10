package libs.xmlConfigReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class xmlReader {

    private static Document xmlBody;
    private static String xmlFilePath;

    private static void setXmlFilePath(String xmlFilePath) {
        xmlReader.xmlFilePath = xmlFilePath;
    }

    static Document getXmlBody() {
        return xmlBody;
    }

    static String getTextByTags(String parentTag, String childTag) {
        NodeList children = xmlBody.getElementsByTagName(parentTag);
        Node node = children.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            return eElement.getElementsByTagName(childTag).item(0).getTextContent();
        }
        return null;
    }

    static String getTextByTagName (String tagName) {
        return xmlBody.getElementsByTagName(tagName).item(0).getTextContent();
    }

    xmlReader(String configPath)
    {
        try {
            setXmlFilePath(configPath);
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setValidating(false);
            DocumentBuilder builder = f.newDocumentBuilder();
            xmlBody = builder.parse(new File(configPath));
            xmlBody.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

