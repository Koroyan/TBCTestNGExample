package theinternettests.Utils;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Parameter;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.util.ResultsUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static io.qameta.allure.Allure.getLifecycle;

public class CustomSoftAssertForMessageStep extends SoftAssert {
    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        // String screenshotAsBase64 = Selenide.screenshot(OutputType.BASE64);
        // byte[] decoded = Base64.getDecoder().decode(screenshotAsBase64);
        AllureLifecycle lifecycle = getLifecycle();
        String methodName = assertCommand.getMessage();
        lifecycle.getCurrentTestCase().ifPresent(uuid -> {
            final String stepUUID = UUID.randomUUID().toString();
            lifecycle.startStep(stepUUID, new StepResult()
                    .withName(methodName)
                    .withStatus(Status.FAILED));
            //lifecycle.updateStep(stepResult -> stepResult.setStart(stepResult.getStart() - event.getDuration()));
            // lifecycle.addAttachment("Screenshot", "image/png", "png", decoded);
            // lifecycle.addAttachment("Page source", "text/html", "html", getPageSourceBytes());
            lifecycle.updateStep(stepResult -> {
                final StatusDetails details = ResultsUtils.getStatusDetails(ex)
                        .orElse(new StatusDetails());
                stepResult.setStatusDetails(details);
                stepResult.setParameters(new Parameter().withName("expected").withValue(assertCommand.getExpected().toString()));
                stepResult.setParameters(new Parameter().withName("actual").withValue(assertCommand.getActual().toString()));
            });

            lifecycle.stopStep(stepUUID);
        });
    }

    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {
        AllureLifecycle lifecycle = getLifecycle();
        String methodName = assertCommand.getMessage();
        lifecycle.getCurrentTestCase().ifPresent(uuid -> {
            final String stepUUID = UUID.randomUUID().toString();
            lifecycle.startStep(stepUUID, new StepResult()
                    .withName(methodName)
                    .withStatus(Status.PASSED));
            lifecycle.updateStep(stepResult -> {
                stepResult.setParameters(new Parameter().withName("expected").withValue(assertCommand.getExpected().toString()));
                stepResult.setParameters(new Parameter().withName("actual").withValue(assertCommand.getActual().toString()));
            });

            lifecycle.stopStep(stepUUID);
        });
    }
    private static byte[] getScreenshotBytes() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    private static byte[] getPageSourceBytes() {
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

}
