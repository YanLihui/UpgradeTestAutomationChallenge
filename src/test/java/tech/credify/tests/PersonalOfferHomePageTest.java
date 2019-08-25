package tech.credify.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tech.credify.base.TestBase;
import tech.credify.pages.LoginPage;
import tech.credify.pages.LogoutPage;
import tech.credify.pages.PersonalOfferHomePage;
import tech.credify.utils.TestUtil;

import static tech.credify.utils.GlobalConstant.*;

public class PersonalOfferHomePageTest extends TestBase {

    private LoginPage loginPage;
    private PersonalOfferHomePage personalOfferHomePage;
    private LogoutPage logoutPage;
    String sheetName = "personalOffer";

    public PersonalOfferHomePageTest(){
        super();
    }

    @BeforeMethod
    public void setup()  {

        initialization(LOGIN_URL);
        loginPage = new LoginPage();
        personalOfferHomePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }

//    @Test(priority = 1)
//    public void getPersonalHomePageTitleTest(){
//
//        try {
//            Thread.sleep(POLL_DELAY_MILLINSECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        final String actualTitle = personalOfferHomePage.getPersonalOfferHomePageTitle();
//        Assert.assertEquals(actualTitle,PERSONAL_HOMEPAGE_TITLE,"Home Page title does not match.");
//    }

    @Test(priority = 1, dataProvider="getTestData")
    public void PersonalOfferTest(final String expectedLoanAmount, final String expectedMonthlyPayment, final String expectedInterestRate,
    final String expectedLoanTerm, final String expectedAPR){

        try {
            Thread.sleep(POLL_DELAY_MILLINSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String defaultLoanAmount = personalOfferHomePage.getUserloanAmount();
        Assert.assertEquals(expectedLoanAmount,defaultLoanAmount);

        String monthlyPayment = personalOfferHomePage.getMonthlyPayment();
        Assert.assertEquals(expectedMonthlyPayment,monthlyPayment);

        String defaultLoanInterestRate = personalOfferHomePage.getDefaultLoanInterestRate();
        Assert.assertEquals(expectedInterestRate,defaultLoanInterestRate);

        String defaultLoanTerm = personalOfferHomePage.getDefaultLoanTerm();
        Assert.assertEquals(expectedLoanTerm,defaultLoanTerm);

        String apr=personalOfferHomePage.getAPR();
        Assert.assertEquals(expectedAPR,apr);

        logoutPage = personalOfferHomePage.logout();
    }

    @DataProvider
    public Object[][] getTestData(){
        Object data[][] = TestUtil.getTestData(sheetName);
        return data;
    }


    @AfterMethod
    public void tearDown(){

        webDriver.quit();
    }
}
