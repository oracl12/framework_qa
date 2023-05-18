package bo

import po.BoardPO
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait

class BoardBO(driver: WebDriver) {
    private var wait = WebDriverWait(driver, 4, 1000)
    private val boardPO = BoardPO(driver, wait)

    fun createList(listNameText: String) {
        boardPO.getListNameInput().setInputText(listNameText)
        boardPO.getSubmitButton().clickOnButton()
    }
}