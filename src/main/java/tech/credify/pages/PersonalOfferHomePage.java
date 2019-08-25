package tech.credify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tech.credify.base.TestBase;
import tech.credify.utils.TestUtil;

import java.io.IOException;
import java.util.ArrayList;


public class PersonalOfferHomePage extends TestBase {

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

    @FindBy(xpath = "//div[@class = 'header-nav']")
    WebElement toggleMenu;

    @FindBy(css = "a[href='/phone/logout']")
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


    public LogoutPage logout (){

        toggleMenu.click();
        btnSignOut.click();
        return new LogoutPage();
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
}
