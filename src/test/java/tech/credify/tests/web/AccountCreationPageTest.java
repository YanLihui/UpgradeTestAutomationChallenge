package tech.credify.tests.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tech.credify.base.TestBase;
import tech.credify.pages.AccountCreationPage;
import tech.credify.pages.NonDMFunnelPage;
import tech.credify.pages.PersonalOfferHomePage;

import java.io.IOException;

import static tech.credify.utils.GlobalConstant.*;

public class AccountCreationPageTest extends TestBase {

    private NonDMFunnelPage personalLoanPortal;
    private AccountCreationPage accountCreationPage;
    private PersonalOfferHomePage personalOfferHomePage;
    public AccountCreationPageTest()
    {
        super();
    }

    private static Logger logger = LogManager.getLogger(AccountCreationPageTest.class);

    @BeforeMethod
    public void setup() throws InterruptedException {

        logger.info("\n====================================Account Creation Test Setup... ====================================\n");
        initialization(CHECK_YOUR_RATE_URL);
        personalLoanPortal = new NonDMFunnelPage();
        accountCreationPage = personalLoanPortal.getRate(prop.getProperty("desiredAmount"));

    }

    @Test
    public void createAccountTest() throws IOException, InterruptedException {

        logger.info("\n====================================Creating Account and save data to excel ====================================\n");
        personalOfferHomePage = accountCreationPage.createAccount();
        personalOfferHomePage.saveDataToExcel();
        personalOfferHomePage.logout();
    }

    @AfterMethod
    public void tearDown()
    {
        webDriver.quit();
    }
}
