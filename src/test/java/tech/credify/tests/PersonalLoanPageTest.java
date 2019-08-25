package tech.credify.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tech.credify.base.TestBase;
import tech.credify.pages.LoginPage;
import tech.credify.pages.PersonalInformationPage;
import tech.credify.pages.PersonalLoanPage;
import tech.credify.pages.PersonalOfferHomePage;

import static tech.credify.utils.GlobalConstant.CHECK_YOUR_RATE_URL;
import static tech.credify.utils.GlobalConstant.LOGIN_URL;

public class PersonalLoanPageTest extends TestBase {

    PersonalLoanPage personalLoanPage;
    PersonalInformationPage personalInformationPage;

    public PersonalLoanPageTest()
    {
        super();
    }

    @BeforeMethod
    public void setUp()
    {
        initialization(CHECK_YOUR_RATE_URL);
        personalLoanPage = new PersonalLoanPage();
    }

    @Test
    public void getRateTest()
    {
        personalInformationPage = personalLoanPage.getRate(prop.getProperty("desiredAmount"));

    }



    @AfterMethod
    public void tearDown()
    {
        webDriver.quit();
    }
}
