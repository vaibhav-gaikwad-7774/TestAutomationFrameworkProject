package io.learn.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class WebDriverFactory {

    private static String remote = System.getProperty("remote"); // Use the system property to determine remote execution
    private static String hubUrl = "http://localhost:4444/wd/hub";

    private WebDriverFactory() {}

    // function which will create a driver instance for me

    public static WebDriver createDriver(Browser browser) {
        WebDriver driver;

        if("true".equalsIgnoreCase(remote)) { // it will be false by default
            driver = getRemoteWebDriver(browser);
        } else {
            driver = getLocalWebDriver(browser);
        }
        return driver;
    }

    // local
    public static WebDriver getLocalWebDriver(Browser browser) {
        WebDriver driver;

        // local WebDriver setup
        switch (browser) {
            case EDGE:
                driver = new EdgeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    // remote
    public static WebDriver getRemoteWebDriver(Browser browser) {
        // remote WebDriver setup
        WebDriver driver;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser.getBrowserName());
        try {
            driver = new RemoteWebDriver(new URL(hubUrl).toURI().toURL(), capabilities);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e); // custom exception
        }
        return driver;
    }
}
