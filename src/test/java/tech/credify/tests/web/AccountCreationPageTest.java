package tech.credify.tests.web;

import org.apache.log4j.Logger;
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

    Logger logger = Logger.getLogger(AccountCreationPageTest.class);

    @BeforeMethod
    public void setup() {


        logger.info("====================================Account Creation Test Setup Start ====================================\n");

        initialization(CHECK_YOUR_RATE_URL);
        personalLoanPortal = new NonDMFunnelPage();
        accountCreationPage = personalLoanPortal.getRate(prop.getProperty("desiredAmount"));

        logger.info("====================================Account Creation Test Setup End ====================================\n");

    }

    @Test
    public void createAccountTest() throws IOException {

        logger.info("====================================Creating Account ====================================\n");


        personalOfferHomePage = accountCreationPage.createAcount();

        try {
            Thread.sleep(POLL_DELAY_MILLINSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        personalOfferHomePage.saveDataToExcel();

    }

    @AfterMethod
    public void tearDown()
    {
        webDriver.quit();
    }

}
