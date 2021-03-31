package theinternettests.theinternet;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class ShiftingContent {

    private final By shiftingContentTextLinkButton = By.xpath("//*[@id=\"content\"]/ul/li[39]/a");
    private final By menuElementTextLinkButton = By.xpath("//*[@id=\"content\"]/div/a[1]");
    private final By anImageElementTextLinkButton = By.xpath("//*[@id=\"content\"]/div/a[2]");
    private final By homeLiElement = By.xpath("//*[@id=\"content\"]/div/ul/li[1]/a");
    private final By secondButton = By.xpath("//*[@id=\"content\"]/div/p[3]/a");
    private final By image = By.className("shift");


    public void testDesignHoverChanges(){
        $(shiftingContentTextLinkButton).click();
        $(menuElementTextLinkButton).click();
        SelenideElement elementHome = $(homeLiElement);
        String beforeCssValue = elementHome.getCssValue("color");
        actions().moveToElement(elementHome).perform();
        String afterCssValue = elementHome.getCssValue("color");
        System.out.println(beforeCssValue+" "+afterCssValue);
        equals(beforeCssValue,afterCssValue);
        step("success");
    }

    @Step("before value {beforeValue} : after value {afterValue}")
    private void equals(String beforeValue, String afterValue){
        Assert.assertNotEquals(beforeValue,afterValue);
    }


    public void testImagePositionChanged(){
        $(anImageElementTextLinkButton).click();
        SelenideElement imageElement = $(image);
        String beforePosition = imageElement.getCssValue("left");
        $(secondButton).click();
        String afterPosition = imageElement.getCssValue("left");
        equals(beforePosition,afterPosition);
        step("success");
    }
}
