package bo

import io.qameta.allure.Step
import po.LoginPO
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.AssertJUnit

class LoginBO(driver: WebDriver) {
    private var wait = WebDriverWait(driver, 3, 500)
    private val loginPO = LoginPO(driver, wait)

    @Step("Login with params: {0} {1}")
    fun login(emailText: String, passwordText: String) {
        loginPO.getEmailInput().setInputText(emailText)
        loginPO.getContinueButton().clickOnButton()
        loginPO.getPasswordInput().setInputText(passwordText)
        loginPO.getSubmitButton().clickOnButton()
    }

    @Step("Check if we are logged in")
    fun checkIfLoggedIn() {
        AssertJUnit.assertTrue(loginPO.getLoginLogoButton().isVisible())
    }
}