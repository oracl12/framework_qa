package bo

import po.MainWorkingPO
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.AssertJUnit

class MainWorkingBO(driver: WebDriver) {
    private var wait = WebDriverWait(driver, 5, 500)
    private val mainWorkingPO = MainWorkingPO(driver, wait)

    fun createBoard(boardNameText: String) {
        val createButton = mainWorkingPO.getCreateBoardButton()
        createButton.clickOnButton()

        val boardNameInput = mainWorkingPO.getBoardNameInput()
        boardNameInput.setInputText(boardNameText)

        mainWorkingPO.getSubmitButton().clickOnButton()
    }

    fun checkIfBoardCreatedAndWeOnIt(text: String){
        AssertJUnit.assertEquals(mainWorkingPO.getBoardNameH().getText(), text)
    }
}