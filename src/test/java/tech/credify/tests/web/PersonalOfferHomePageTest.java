package tech.credify.tests.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tech.credify.base.TestBase;
import tech.credify.pages.LoginPage;
import tech.credify.pages.PersonalOfferHomePage;
import tech.credify.utils.TestUtil;

import static tech.credify.utils.GlobalConstant.*;

public class PersonalOfferHomePageTest extends TestBase {

    private LoginPage loginPage;
    private PersonalOfferHomePage personalOfferHomePage;
    String sheetName = "personalOffer";

    private static Logger logger = LogManager.getLogger(PersonalOfferHomePageTest.class);

    public PersonalOfferHomePageTest(){
        super();
    }

    @BeforeMethod
    public void setup()  {

        initialization(LOGIN_URL);
        loginPage = new LoginPage();
        personalOfferHomePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test(priority = 1, dataProvider="getTestData")
    public void personalOfferDetailsValidationTest(final String expectedLoanAmount, final String expectedMonthlyPayment, final String expectedInterestRate,
                                                   final String expectedLoanTerm, final String expectedAPR) throws InterruptedException {

        logger.info("\n===================================Starting validating the Loan details ====================================\n");


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

        personalOfferHomePage.logout();

        logger.info("\n=================================== Validation Done ====================================\n");

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
