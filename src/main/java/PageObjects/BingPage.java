package PageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BingPage {

    private SelenideElement bingLogo = $("svg#bLogo");
    private SelenideElement searchInput = $("input#sb_form_q");
    private SelenideElement searchResult = $("ol#b_results cite");
    private SelenideElement desiredPhrase = $x("//strong[contains(text(),'washington')]");

    public void enterSearchText(String searchPhase) {
        switchTo().frame($("iframe#iframeResult"));
        switchTo().frame($("iframe[src='https://www.bing.com']"));
        $(bingLogo).shouldBe(Condition.visible).should(Condition.appear);
        $(searchInput).should(Condition.appear).sendKeys(searchPhase);
    }

    public void desiredSearchPhaseInList() {
        $(desiredPhrase).scrollIntoView(false).click();
    }

    public String returnFirstSearchResult() {
       return $(searchResult).scrollIntoView(false).getText();
    }

}
