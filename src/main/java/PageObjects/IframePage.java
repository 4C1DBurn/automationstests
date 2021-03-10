package PageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class IframePage {

    private SelenideElement runButton = $("div.trytopnav button");

    public void changeSource() {
        String s = $("textarea").innerText();
        executeJavaScript("window.editor.setValue(document.getElementById(\"textarea\").innerText.replace(\"https://www.w3schools.com\",\"https://www.bing.com\"));");
        $("div.trytopnav button").click();
    }

    public void clickRunButton() {
        $(runButton).click();
    }

}
