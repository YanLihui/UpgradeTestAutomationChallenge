package tech.credify.tests.web;

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

    @BeforeMethod
    public void setup() {

        initialization(CHECK_YOUR_RATE_URL);
        personalLoanPortal = new NonDMFunnelPage();
        accountCreationPage = personalLoanPortal.getRate(prop.getProperty("desiredAmount"));
    }

    @Test
    public void createAccountTest() throws IOException {

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
