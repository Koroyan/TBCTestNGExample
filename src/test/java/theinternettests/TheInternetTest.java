package theinternettests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.model.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import theinternettests.Utils.CustomSoftAssert;
import theinternettests.Utils.CustomSoftAssertForMessageStep;
import theinternettests.theinternet.ChallengingDOM;
import theinternettests.theinternet.ShiftingContent;
import theinternettests.theinternet.UploadPage;

import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.Selenide.*;

public class TheInternetTest {

    UploadPage uploadPage;
    ShiftingContent shiftingContent;
    ChallengingDOM challengingDOM;

//    @BeforeClass
//    public void before() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
//        System.out.println("before");
//        uploadPage = new UploadPage();
//        shiftingContent = new ShiftingContent();
//        challengingDOM = new ChallengingDOM();
//        open(EndPoinds.THE_INTERNET_BASE_URL);
//    }
//
//    @Step
//    @Story("upload page")
//    @Description("upload file test")
//    @Test(priority = 1)
//    public void uploadFileTest() throws IOException {
//        File uploadFile = new File("C:\\Users\\admin\\Pictures\\Screenshots\\test.png");
//        uploadPage.upload(uploadFile);
//        sleep(1000);
//    }
//
//
//    @Step
//    @Story("shifting content page")
//    @Description("change hover design test")
//    @Test(priority = 2)
//    public void hoverDesignTest() {
//        open(EndPoinds.THE_INTERNET_BASE_URL);
//        shiftingContent.testDesignHoverChanges();
//        sleep(1000);
//    }
//
//    @Step
//    @Story("shifting content page")
//    @Description("image position test")
//    @Test(priority = 3)
//    public void imagePositionTest() {
//        open(EndPoinds.SHIFTING_CONTENT_URL);
//        shiftingContent.testImagePositionChanged();
//        sleep(1000);
//    }
//
//    @Step
//    @Story("challenging DOM page")
//    @Description("matrix row test")
//    @Test(priority = 4)
//    public void matrixTest() throws Exception {
//        open(EndPoinds.THE_INTERNET_BASE_URL);
//        challengingDOM.matrixTest();
//        sleep(1000);
//    }


    @Story("soft assert with method name")
    @Description("soft assert description")
    @Test
    public void softAssertTest() throws Exception {
        CustomSoftAssert softAssert = new CustomSoftAssert();
        f1(softAssert);
        f2(softAssert);
        f3(softAssert);
        f4(softAssert);
        softAssert.assertAll();
    }

    public void f1(SoftAssert softAssert) throws IOException {
            softAssert.assertEquals(1,2);
    }
    public void f2(SoftAssert softAssert){
        softAssert.assertEquals(4,4);
    }
    public void f3(SoftAssert softAssert){
        softAssert.assertEquals(0,0);
    }
    public void f4(SoftAssert softAssert){
        softAssert.assertEquals(1,0);
    }

    @Story("soft assert with message")
    @Description("soft assert message description")
    @Test
    public void softAssertMessageTest() throws Exception {
        CustomSoftAssertForMessageStep softAssert = new CustomSoftAssertForMessageStep();
        softAssert.assertEquals(1,2,"1 is equals 2?");
        softAssert.assertEquals(2,2,"2 is equal 2?");
        softAssert.assertEquals("Mariam","Mari","Mariami is equals Mari?");
        softAssert.assertEquals(1.4,1.4,"1.4 is equals 1.4?");
        softAssert.assertAll();
    }


}
