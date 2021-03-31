package theinternettests.theinternet;


import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class UploadPage {

    private final By uploadTextLinkButton =  By.cssSelector("a[href*='/upload']");
    private final By uploadFileButton = By.id("file-upload");
    private final By submitButton = By.id("file-submit");
    private final By fileUploadedContent = By.id("content");



    @Step
    public void upload(File uploadFile) throws IOException {
        openUploadPage();
        $(uploadFileButton).uploadFile(uploadFile);
        $(submitButton).click();
        $(fileUploadedContent).shouldHave(text("File Uploaded!"));
        byte[] uploaded_files = uploadedFile("uploaded file", uploadFile);
    }
    @Attachment(value = "{0}", type = "image/png")
    private byte[] uploadedFile(final String testName, final File screenShot) throws IOException {
        return Files.readAllBytes(screenShot.toPath());
    }

    private void openUploadPage(){
        element(uploadTextLinkButton).click();
    }
}
