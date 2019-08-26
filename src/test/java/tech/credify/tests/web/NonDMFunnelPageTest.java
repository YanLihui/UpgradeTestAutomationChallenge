package tech.credify.tests.web;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tech.credify.base.TestBase;
import tech.credify.pages.AccountCreationPage;
import tech.credify.pages.NonDMFunnelPage;

import static tech.credify.utils.GlobalConstant.CHECK_YOUR_RATE_URL;

public class NonDMFunnelPageTest extends TestBase {

    NonDMFunnelPage nonDMFunnelPage;
    AccountCreationPage accountCreationPage;

    public NonDMFunnelPageTest()
    {
        super();
    }

    @BeforeMethod
    public void setUp()
    {
        initialization(CHECK_YOUR_RATE_URL);
        nonDMFunnelPage = new NonDMFunnelPage();
    }

    @Test
    public void getRateTest()
    {
        accountCreationPage = nonDMFunnelPage.getRate(prop.getProperty("desiredAmount"));

    }

    @AfterMethod
    public void tearDown()
    {
        webDriver.quit();
    }
}
