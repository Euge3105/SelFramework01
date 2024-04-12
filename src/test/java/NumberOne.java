import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NumberOne {

    public static void main(String[] args) {

//        String browserName = "chrome";
//        WebDriver driver = null;

//        if(browserName.equals("chrome")){
////            System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
//            driver = new ChromeDriver();
//        }else if(browserName.equals("firefox")){
////            System.setProperty("webdriver.edggeckoe.driver","./gechodriver.exe");
//            driver = new FirefoxDriver();
//        }else if(browserName.equals("edge")){
////            System.setProperty("webdriver.edge.driver","./msedgedriver.exe");
//            driver = new EdgeDriver();
//        }

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://pprod-v5consumerweb.rumbleon.com/");
//        driver.get("https://omayo.blogspot.com/");



//        jse.executeScript("alert('juihuhuhhuuh')");
        // prompt, confirm, alert,
        // document.querySelector
        WebElement elem1 = driver.findElement(By.cssSelector("[class='ant-btn css-j5xhnb-emotion--HomepageVin--HomepageVin']"));
//        jse.executeScript("document.getElementsByClassName(\"ant-btn css-j5xhnb-emotion--HomepageVin--HomepageVin\")[0].click();");

        javaScriptClick(driver, elem1);



    }

    public static void javaScriptClick(WebDriver driver, WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()",element);


    }

}
