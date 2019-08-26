package tech.credify.tests.web;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tech.credify.base.TestBase;
import tech.credify.pages.LoginPage;
import tech.credify.pages.PersonalOfferHomePage;

import static tech.credify.utils.GlobalConstant.LOGIN_URL;

public class LoginPageTest  extends TestBase {

    LoginPage loginPage;
    PersonalOfferHomePage personalOfferHomePage;

   public LoginPageTest()
   {
       super();
   }

    @BeforeMethod
    public void setUp()
    {
        initialization(LOGIN_URL);
        loginPage = new LoginPage();
    }

    @Test(enabled = false)
    public void loginWithCorrectCredentials()
    {
        personalOfferHomePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertEquals(prop.getProperty("offersWelcomeMessage"),personalOfferHomePage.getWelcomeMessage());

    }

    @AfterMethod
    public void tearDown()
    {
        webDriver.quit();
    }
}
