
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleTest {
    protected static WebDriver driver;

    @Test
    public void testEdgeIE() throws Exception {
        System.setProperty("webdriver.ie.driver", "C:\\Dev\\SELENIUM_EDGE_IE\\X4_32_IEDriverServer.exe");
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.attachToEdgeChrome();
        ieOptions.withEdgeExecutablePath("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
        addOptions(ieOptions);
        WebDriver driver = new InternetExplorerDriver(ieOptions);
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("before get");

        
        driver.get("http://192.168.122.1/test.html");

        (new WebDriverWait(driver, Duration.ofSeconds(120))).until(d->extracted(d));
        

        System.err.println("got  handle");

        Thread.sleep(10000);

        ((JavascriptExecutor) driver).executeScript("window.close();");
       
        Thread.sleep(10000);

       try {

        driver.close();
        
       } catch (Throwable t) {
        System.err.println("got excepton:" + t);
       }
        Thread.sleep(10000);

    }

    private Object extracted(WebDriver d) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.err.println("waiting..."+d.getWindowHandles().size());
        return d.getWindowHandles().size()>1;
    }

    private void addOptions(InternetExplorerOptions options) {

        options.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        // options.setCapability (CapabilityType.VERSION, WEBDRIVER_IE_VERSION);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.SUPPORTS_WEB_STORAGE, true);
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        // options.setCapability (CapabilityType.SUPPORTS FINDING_BY_CSS, true);
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
        options.setCapability(CapabilityType.OVERLAPPING_CHECK_DISABLED, true);
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, false);
        options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        // options.setCapability (InternetExplorerDriver. UNEXPECTED_ALERT_BEHAVIOR,
        // IGNORE);
        options.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
        // options.setCapability (CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, IGNORE);
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
        options.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 10000);
        options.setCapability("disable-popup-blocking", true);
        // options.setJavascriptEnabled(true);
        // options.setCapability (InternetExplorerDriver. INITIAL_BROWSER_URL,
        // Dialog2URL.getUrlPabLogin());
        // options.setAcceptInsecureCerts(true);
    }
}
