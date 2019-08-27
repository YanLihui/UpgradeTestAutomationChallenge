package tech.credify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tech.credify.base.TestBase;

public class NonDMFunnelPage extends TestBase {

    @FindBy (xpath = "//input[@name= 'desiredAmount']")
    private WebElement inputDesiredAmount;
    @FindBy (xpath = "//select[@data-auto = 'dropLoanPurpose']")
    private WebElement dropLoanPurpose;
    @FindBy (xpath = "//button[@data-auto = 'CheckYourRate']")
    private WebElement btnCheckRate;

    public NonDMFunnelPage() {
        PageFactory.initElements(webDriver, this);
    }

    public AccountCreationPage getRate(String loanAmount )
    {
        inputDesiredAmount.sendKeys(loanAmount);

        Select drpLoanPurpose = new Select(dropLoanPurpose);
        drpLoanPurpose.selectByValue("CREDIT_CARD");
        btnCheckRate.click();
        return new AccountCreationPage();
    }
}