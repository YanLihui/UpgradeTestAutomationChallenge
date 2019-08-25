package tech.credify.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tech.credify.base.TestBase;
import tech.credify.pages.LoginPage;
import tech.credify.pages.PersonalInformationPage;
import tech.credify.pages.PersonalLoanPage;
import tech.credify.pages.PersonalOfferHomePage;

import java.io.IOException;

import static tech.credify.utils.GlobalConstant.*;

public class PersonalInfoPageTest extends TestBase {

    private PersonalLoanPage personalLoanPortal;
    private PersonalInformationPage personalInformationPage;
    private PersonalOfferHomePage personalOfferHomePage;

    public PersonalInfoPageTest()
    {
        super();
    }

    @BeforeMethod
    public void setup() {

        initialization(CHECK_YOUR_RATE_URL);
        personalLoanPortal = new PersonalLoanPage();
        personalInformationPage = personalLoanPortal.getRate(prop.getProperty("desiredAmount"));
    }

    @Test
    public void personalInfoPageTest() throws IOException {
        personalOfferHomePage = personalInformationPage.createAcount();

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
