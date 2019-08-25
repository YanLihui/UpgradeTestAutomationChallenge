package tech.credify.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import tech.credify.utils.WebEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static tech.credify.utils.GlobalConstant.*;

public class TestBase {


    public static WebDriver webDriver;
    public static Properties prop;
    public  static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;
    private static String propertyFilePath = System.getProperty("user.dir")+
            "/src/main/java/tech/credify/config/config.properties";

   public TestBase()
   {
       try {

           prop = new Properties();
           FileInputStream ip = new FileInputStream(propertyFilePath);
           prop.load(ip);

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }catch (IOException e){
           e.printStackTrace();
       }
   }

   public static void initialization(final String pageUrl)
   {
       String browserName = prop.getProperty("browser");
       if (browserName.equals("chrome"))
       {
           WebDriverManager.chromedriver().setup();
           webDriver = new ChromeDriver();

       }else
           if (browserName.equals("firefox"))
       {
           WebDriverManager.firefoxdriver().setup();
           webDriver = new FirefoxDriver();

       }

       e_driver = new EventFiringWebDriver(webDriver);
       // Now create object of EventListerHandler to register it with EventFiringWebDriver
       eventListener = new WebEventListener();
       e_driver.register(eventListener);
       webDriver = e_driver;

       webDriver.manage().window().maximize();
           webDriver.manage().deleteAllCookies();
           webDriver.manage().timeouts().pageLoadTimeout(PAGE_TIMEOUT, TimeUnit.SECONDS);
           webDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT,TimeUnit.SECONDS);
           webDriver.get(pageUrl);
   }

    /**
     * return false if element is not clickable (enabled and displayed)
     *
     * @param element
     */
    public <T> boolean isElementClickable(final T element)
    {
        try
        {
            final WebElement elementInstance = getElement(element);
            return elementInstance.isEnabled() && elementInstance.isDisplayed();
        }
        catch (WebDriverException e)
        {
            return false;
        }
    }


    public <T> WebElement getElement(final T element)
    {
        return (element instanceof WebElement) ? (WebElement) element : webDriver.findElement((By) element);
    }


}
