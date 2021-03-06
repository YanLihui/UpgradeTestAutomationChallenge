package tech.credify.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tech.credify.base.TestBase;


public class LoginPage extends TestBase {

    @FindBy(xpath = "//input[@name = 'username']")
    private WebElement inputUsername;
    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement buttonSignin;

    public LoginPage() {
        PageFactory.initElements(webDriver, this);

    }

    public String getLoginPageTitle(){

        return webDriver.getTitle();
    }

    public PersonalOfferHomePage login(String uname, String pwd)
    {
        inputUsername.sendKeys(uname);
        inputPassword.sendKeys(pwd);
        clickOnButtonsubmit();
        return new PersonalOfferHomePage();
    }

    public void clickOnButtonsubmit(){

        Actions action = new Actions(webDriver);
        action.click(buttonSignin).build().perform();

    }

}

