package bo

import po.MainWorkingPO
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait

class MainWorkingBO(driver: WebDriver) {
    private var wait = WebDriverWait(driver, 5, 1000)
    private val mainWorkingPO = MainWorkingPO(driver, wait)

    fun createBoard(boardNameText: String) {
        val createButton = mainWorkingPO.getCreateBoardButton()
        createButton.clickOnButton()

        val boardNameInput = mainWorkingPO.getBoardNameInput()
        boardNameInput.setInputText(boardNameText)

        mainWorkingPO.getSubmitButton().clickOnButton()
    }
}