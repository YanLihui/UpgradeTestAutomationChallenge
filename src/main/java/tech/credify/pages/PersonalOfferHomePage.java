package tech.credify.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tech.credify.base.TestBase;
import tech.credify.utils.TestUtil;

import java.io.IOException;
import java.util.ArrayList;


public class PersonalOfferHomePage extends TestBase {

    @FindBy(xpath = "//h2[contains(text(),'Great news, here are your offers!')]")
    WebElement welcomeMessage;
    @FindBy(xpath = "//span[@data-auto = 'userLoanAmount']")
    WebElement userLoanAmount;
    @FindBy(xpath = "//span[@data-auto = 'defaultMonthlyPayment']")
    WebElement defaultMonthlyPayment;
    @FindBy(xpath = "//div[@data-auto = 'defaultLoanTerm']")
    WebElement defaultLoanTerm;
    @FindBy(xpath = "//div[@data-auto = 'defaultLoanInterestRate']")
    WebElement defaultLoanInterestRate;
    @FindBy(xpath = "//div[@data-auto = 'defaultMoreInfoAPR']/div")
    WebElement defaultMoreInfoAPR;

    @FindBy(xpath = "//label[@class = 'header-nav__toggle']")
    WebElement toggleMenu;

    @FindBy(linkText = "Sign Out")
    WebElement btnSignOut;

    public PersonalOfferHomePage() {
        PageFactory.initElements(webDriver, this);
    }

    public String getPersonalOfferHomePageTitle() {

        return webDriver.getTitle();
    }

    public String getUserloanAmount() {

        return userLoanAmount.getText();
    }

    public String getMonthlyPayment()
    {
        return defaultMonthlyPayment.getText();

    }

    public String getDefaultLoanInterestRate(){

        return defaultLoanInterestRate.getText();
    }

    public String getDefaultLoanTerm(){
        return  defaultLoanTerm.getText();
    }


    public String getAPR()
    {
        return defaultMoreInfoAPR.getText();
    }


    public void logout () throws InterruptedException {

        waitforElementThenClick(webDriver, toggleMenu);
        waitforElementThenClick(webDriver, btnSignOut);
    }

    public void saveDataToExcel() throws IOException {

        ArrayList<String> offerDetails = new ArrayList<String>();
        offerDetails.add(getUserloanAmount());
        offerDetails.add(getMonthlyPayment());
        offerDetails.add(getDefaultLoanInterestRate());
        offerDetails.add(getDefaultLoanTerm());
        offerDetails.add(getAPR());

        TestUtil testUtil = new TestUtil();

        testUtil.saveOfferInformationToExcel("personalOffer",offerDetails);

    }

    public String getWelcomeMessage()
    {
        return welcomeMessage.getText();
    }

}
