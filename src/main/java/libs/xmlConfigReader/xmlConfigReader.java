package libs.xmlConfigReader;

import libs.Utilites.Utilities;
import org.w3c.dom.Document;

import static libs.Utilites.Utilities.fixFilePath;

public class xmlConfigReader extends xmlReader {
    private static String configPath = getConfigPath() ;
    private static Document doc = initXml();

    public xmlConfigReader(String configPath)
    {
        super(configPath);
        xmlConfigReader.configPath = configPath;
        doc = getXmlBody();
    }

    public xmlConfigReader() {
        super(configPath);
        doc = getXmlBody();
    }

    public String getImagesPath() {
        return fixFilePath(getTextByTagName("imagesPath"));
    }

    public String getDbPath() {
        return fixFilePath(getTextByTagName("dbPath"));
    }

    private static String getConfigPath(){
        return Utilities.fixFilePath("src\\main\\java\\configs\\paths.xml");
    }

    private static Document initXml() {
        if (doc == null)
            new xmlConfigReader();
        return doc;
    }


}

