package po

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import wrappers.ElementWrapper

class MainWorkingPO(driver: WebDriver, private val wait: WebDriverWait) {

    @FindBy(xpath = "//*[contains(text(), 'Створити нову дошку')]")
    private var createBoardButton: WebElement? = null

    @FindBy(xpath = "/html/body/div[3]/div/section/div/form/div[1]/label/input")
    private var boardNameInput: WebElement? = null

    @FindBy(xpath = "/html/body/div[3]/div/section/div/form/button")
    private var submitButton: WebElement? = null

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div[1]/div/div/span[1]/div[2]/h1")
    private var boardNameH: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun getCreateBoardButton(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(createBoardButton))
        return ElementWrapper(createBoardButton!!)
    }

    fun getBoardNameInput(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(boardNameInput))
        return ElementWrapper(boardNameInput!!)
    }

    fun getSubmitButton(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(submitButton))
        return ElementWrapper(submitButton!!)
    }

    fun getBoardNameH(): ElementWrapper {
        wait.until(ExpectedConditions.visibilityOf(boardNameH))
        return ElementWrapper(boardNameH!!)
    }
}