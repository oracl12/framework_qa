package po

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import wrappers.ElementWrapper


class LoginPO(driverWeb: WebDriver, private val wait: WebDriverWait) {

    @FindBy(xpath = "//*[@id=\"user\"]")
    var email: WebElement? = null

    @FindBy(xpath = "//*[@id=\"password\"]")
    var password: WebElement? = null

    @FindBy(xpath = "//*[@id=\"login\"]")
    var continueButton: WebElement? = null

    @FindBy(xpath = "//*[@id=\"login-submit\"]")
    var submitButton: WebElement? = null

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div[4]/button/span/span")
    var loginLogo: WebElement? = null

    init {
        PageFactory.initElements(driverWeb, this)
    }

    fun getEmailInput(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(email))
        return ElementWrapper(email!!)
    }

    fun getPasswordInput(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(password))
        return ElementWrapper(password!!)
    }

    fun getContinueButton(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(continueButton))
        return ElementWrapper(continueButton!!)
    }

    fun getSubmitButton(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(submitButton))
        return ElementWrapper(submitButton!!)
    }

    fun getLoginLogoButton(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(loginLogo))
        return ElementWrapper(loginLogo!!)
    }
}