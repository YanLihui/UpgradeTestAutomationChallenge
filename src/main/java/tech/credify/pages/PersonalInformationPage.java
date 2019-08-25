package tech.credify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tech.credify.base.TestBase;

import java.awt.*;

public class PersonalInformationPage extends TestBase {


    @FindBy (xpath = "//input[@name='borrowerFirstName']")
    WebElement inputBorrowerFirstName;
    @FindBy (xpath = "//input[@name='borrowerLastName']")
    WebElement inputBorrowerLastName;
    @FindBy (xpath = "//input[@name='borrowerStreet']")
    WebElement inputBorrowerStreet;
    @FindBy (xpath = "//input[@name='borrowerCity']")
    WebElement inputBorrowerCity;
    @FindBy (xpath = "//input[@name='borrowerState']")
    WebElement inputBorrowerState;
    @FindBy (xpath = "//input[@name='borrowerZipCode']")
    WebElement inputBorrowerZipCode;
    @FindBy (xpath = "//input[@name='borrowerDateOfBirth']")
    WebElement inputBorrowerDateOfBirth;
    @FindBy (xpath = "//input[@name='borrowerIncome']")
    WebElement inputBorrowerIncome;
    @FindBy (xpath = "//input[@name='borrowerAdditionalIncome']")
    WebElement inputBorrowerAdditionalIncome;
    @FindBy (xpath = "//input[@name='username']")
    WebElement inputBorrowerEmail;
    @FindBy (xpath = "//input[@name='password']")
    WebElement inputBorrowerpassword;
    @FindBy (xpath = "//div[contains(text(),'I have')]")
    WebElement checkConsent;
    @FindBy (xpath = "//button[@data-auto ='submitPersonalInfo']")
    WebElement btnSubmit;

    public PersonalInformationPage() {
        PageFactory.initElements(webDriver, this);
    }

    public PersonalOfferHomePage createAcount()
    {
        inputBorrowerFirstName.sendKeys(prop.getProperty("firstName"));
        inputBorrowerLastName.sendKeys(prop.getProperty("lastName"));
        inputBorrowerDateOfBirth.sendKeys(prop.getProperty("borrowerDateOfBirth"));
        inputBorrowerStreet.sendKeys(prop.getProperty("borrowerStreet"));
        inputBorrowerCity.sendKeys(prop.getProperty("borrowerCity"));
        inputBorrowerState.sendKeys(prop.getProperty("borrowerState"));
        inputBorrowerZipCode.sendKeys(prop.getProperty("borrowerZipCode"));
        inputBorrowerIncome.sendKeys(prop.getProperty("borrowerIncome"));
        inputBorrowerAdditionalIncome.sendKeys(prop.getProperty("borrowerAdditionalIncome"));
        inputBorrowerEmail.sendKeys(prop.getProperty("username"));
        inputBorrowerpassword.sendKeys(prop.getProperty("password"));
        checkConsent.click();
        btnSubmit.click();

        return new PersonalOfferHomePage();


    }
}
