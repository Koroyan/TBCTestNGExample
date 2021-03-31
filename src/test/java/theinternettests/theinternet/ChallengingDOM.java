package theinternettests.theinternet;


import org.openqa.selenium.By;
import org.testng.Assert;
import theinternettests.exceptions.CustomException;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class ChallengingDOM {
    private final By challengingDOMTextLinkButton = By.xpath("//*[@id=\"content\"]/ul/li[5]/a");
    //  private final By matrix = By.xpath("//*[@id=\"content\"]/div/div/div/div[2]/table/tbody");


    public void matrixTest() throws CustomException {
        $(challengingDOMTextLinkButton).click();
        // ElementsCollection matrix = $$(this.matrix);
        // System.out.println(matrix.size());
        for (int i = 1; i <= 10; i++) {
            String text = $(By.xpath("//*[@id=\"content\"]/di/div/div/div[2]/table/tbody/tr[" + i + "]/td[1]")).text();
            step("index " + i + " text " + text);
            //if (text.charAt(text.length() - 1) != '0')
                //throw new CustomException(i, text);
            isLastIndexEqualsZero(text);
        }
        step("success");
    }


    private void isLastIndexEqualsZero(String text) {
        Assert.assertNotEquals(text.charAt(text.length() - 1), '0');
    }
}
