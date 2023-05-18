package wrappers

import org.openqa.selenium.WebElement

class ElementWrapper(private val element: WebElement) {
    fun clickOnButton() {
        element.click()
    }

    fun setInputText(text: String) {
        element.sendKeys(text)
    }

    fun getText(): String {
        return element.text
    }

    fun isVisible(): Boolean {
        return element.isDisplayed
    }

    fun isEnableToClick(): Boolean {
        return element.isEnabled
    }
}