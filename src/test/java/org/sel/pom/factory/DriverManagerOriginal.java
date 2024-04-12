package org.sel.pom.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sel.pom.constants.BrowserType;

public class DriverManagerOriginal {

    public WebDriver initializeDriver(String browser){
        WebDriver driver;
//        driver = new EdgeDriver();
//        String browser = System.getProperty("browser","CHROME");
//        String localBrowser;

//        localBrowser = browser;
        driver = switch (BrowserType.valueOf(browser)) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
            default -> throw new IllegalArgumentException("Invalid browser name: " + browser);
        };
//        WebDriverManager.chromedriver().cachePath("Drivers").setup();

        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }
}
