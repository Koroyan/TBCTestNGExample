package theinternettests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import theinternettests.theinternet.ChallengingDOM;
import theinternettests.theinternet.ShiftingContent;
import theinternettests.theinternet.UploadPage;


import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class TheInternetTest {

    UploadPage uploadPage;
    ShiftingContent shiftingContent;
    ChallengingDOM challengingDOM;

    @BeforeClass
    public void before() {
        System.out.println("before");
        uploadPage = new UploadPage();
        shiftingContent = new ShiftingContent();
        challengingDOM = new ChallengingDOM();
        open(EndPoinds.THE_INTERNET_BASE_URL);
    }

    @Step
    @Story("upload page")
    @Description("upload file test")
    @Test(priority = 1)
    public void uploadFileTest() throws IOException {
        File uploadFile = new File("C:\\Users\\admin\\Pictures\\Screenshots\\test.png");
        uploadPage.upload(uploadFile);
        sleep(5000);
    }


    @Step
    @Story("shifting content page")
    @Description("change hover design test")
    @Test(priority = 2)
    public void hoverDesignTest() {
        open(EndPoinds.THE_INTERNET_BASE_URL);
        shiftingContent.testDesignHoverChanges();
    }

    @Step
    @Story("shifting content page")
    @Description("image position test")
    @Test(priority = 3)
    public void imagePositionTest() {
        open(EndPoinds.SHIFTING_CONTENT_URL);
        shiftingContent.testImagePositionChanged();
    }

    @Step
    @Story("challenging DOM page")
    @Description("matrix row test")
    @Test(priority = 4)
    public void matrixTest() throws Exception {
        open(EndPoinds.THE_INTERNET_BASE_URL);
        challengingDOM.matrixTest();
    }
}
