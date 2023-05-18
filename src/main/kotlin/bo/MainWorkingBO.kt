package bo

import io.qameta.allure.Step
import po.MainWorkingPO
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.AssertJUnit

class MainWorkingBO(driver: WebDriver) {
    private var wait = WebDriverWait(driver, 5, 500)
    private val mainWorkingPO = MainWorkingPO(driver, wait)

    @Step("Creating board with name: {0}")
    fun createBoard(boardNameText: String) {
        val createButton = mainWorkingPO.getCreateBoardButton()
        createButton.clickOnButton()

        val boardNameInput = mainWorkingPO.getBoardNameInput()
        boardNameInput.setInputText(boardNameText)

        mainWorkingPO.getSubmitButton().clickOnButton()
    }

    @Step("Check if board with name {0} exists and we are in it")
    fun checkIfBoardCreatedAndWeOnIt(text: String){
        AssertJUnit.assertEquals(mainWorkingPO.getBoardNameH().getText(), text)
    }
}