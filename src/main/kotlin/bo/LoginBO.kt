package bo

import po.LoginPO
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait

class LoginBO(driver: WebDriver) {
    private var wait = WebDriverWait(driver, 2, 2000)
    private val loginPO = LoginPO(driver, wait)

    fun login(emailText: String, passwordText: String) {
        loginPO.getEmailInput().setInputText(emailText)
        loginPO.getContinueButton().clickOnButton()
        loginPO.getPasswordInput().setInputText(passwordText)
        loginPO.getSubmitButton().clickOnButton()
    }
}