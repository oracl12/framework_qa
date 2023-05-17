package wrappers

import org.openqa.selenium.WebElement

class ElementWrapper(private val element: WebElement) {
    fun clickOnButton() {
        element.click()
    }

    fun setInputText(text: String) {
        element.sendKeys(text)
    }

    fun isVisible() {
        element.isDisplayed
    }

    fun isEnableToClick() {
        element.isEnabled
    }
}