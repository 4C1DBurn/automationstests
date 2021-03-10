package tests;

import PageObjects.BingPage;
import PageObjects.IframePage;
import libs.DatabaseManager.DatabaseManager;
import libs.Utilites.Utilities;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Tests extends BeforeSuit {

    @Test(groups = {"TEST"}, priority = 1)
    public void compareTwoImagesInBase64() throws Exception {
        

        String imageExample2Base64 = null;
            imageExample2Base64 = Utilities.encodeFileToBase64Binary(new File(String.valueOf(Utilities.saveFile(
                    "http://apimeme.com/meme?meme=Alarm-Clock&top=Top+text&bottom=Bottom+text",
                        System.getProperty("user.dir")
                                                                    + File.separatorChar + BeforeSuit.imagesPath()
                                                                    + "example2.jpeg"))));
        String imageExampleBase64 = Utilities.encodeFileToBase64Binary(new File(System.getProperty("user.dir")
                + File.separatorChar
                + BeforeSuit.imagesPath()
                + "example.jpeg"));

        assertEquals(imageExample2Base64, imageExampleBase64);
    }

    @Test(groups = {"TEST"}, priority = 2)
    public void checkPopulationDensityAndSumOfPopulation() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager("country");
        ResultSet populationDensity =
                databaseManager.selectCustom("select country from Countries where (Population/Area) < 50 GROUP BY country and country in ('USA');");

        assertEquals(populationDensity.getString("Country"),"USA");

        assertTrue(databaseManager.selectCustom("select sum(Population) from Countries")
                .getInt(1) < 2000000000, "Sum of population is greater");
    }

    @Test(groups = {"TEST"}, priority = 3)
    public void iFrame() {
        IframePage iframePage = open("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_iframe", IframePage.class);
        iframePage.changeSource();
        iframePage.clickRunButton();
        BingPage bingPage = new BingPage();
        bingPage.enterSearchText("Redmond");
        bingPage.desiredSearchPhaseInList();

        assertEquals("https://www." +bingPage.returnFirstSearchResult(),"https://www.bing.com/travelguide?q=Redmond");


    }

}
