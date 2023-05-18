package po

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import wrappers.ElementWrapper

class BoardPO(driverWeb: WebDriver, private val wait: WebDriverWait) {

    @FindBy(xpath = "//*[@id=\"board\"]/div/form/input")
    var listNameInput: WebElement? = null

    @FindBy(xpath = "//*[@id=\"board\"]/div/form/div/input")
    var submitButton: WebElement? = null

    @FindBy(xpath = "//*[@id=\"board\"]/div[1]/div/div[1]/textarea")
    var listH: WebElement? = null

    init {
        PageFactory.initElements(driverWeb, this)
    }

    fun getListNameInput(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(listNameInput))
        return ElementWrapper(listNameInput!!)
    }

    fun getSubmitButton(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(submitButton))
        return ElementWrapper(submitButton!!)
    }

    fun getListH(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(listH))
        return ElementWrapper(listH!!)
    }
}