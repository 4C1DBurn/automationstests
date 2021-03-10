package tests;

import libs.DatabaseManager.DatabaseManager;
import libs.Utilites.Utilities;
import libs.xmlConfigReader.xmlConfigReader;
import org.testng.annotations.*;

import java.io.File;

//@Listeners(ScreenshotListener.class)
public class BeforeSuit {

    private xmlConfigReader paths;


    @BeforeClass(groups = {"config", "TEST"}, alwaysRun = true)
    public void BeforeClass() {
        paths = new xmlConfigReader();
        try {
            DatabaseManager databaseManager = new DatabaseManager("country");
            databaseManager.createDataBase();
            databaseManager.createTable();
            databaseManager.insert("insert INTO Countries (Country, Population, Area) VALUES ('Ukraine', 41588354,603628);");
            databaseManager.insert("insert INTO Countries (Country, Population, Area) VALUES ('France', 67399000,640679);");
            databaseManager.insert("insert INTO Countries (Country, Population, Area) VALUES ('USA', 328239523,9833520);");
            databaseManager.insert("insert INTO Countries (Country, Population, Area) VALUES ('China', 1400050000,9596961);");
            System.out.println("Creating DB Done");

            Utilities.saveFile("http://apimeme.com/meme?meme=Alarm-Clock&top=Top+text&bottom=Bottom+text",
                    System.getProperty("user.dir") + File.separatorChar + paths.getImagesPath() + "example.jpeg");
            System.out.println("Downloading image from URL done");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @AfterTest(groups = {"config"}, alwaysRun = true)
    public void AfterClass() {
        Utilities.deleteFile(System.getProperty("user.dir") + File.separatorChar + paths.getDbPath());
    }

}
