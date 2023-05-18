package util

import org.openqa.selenium.WebDriver

object DriverFactory {
    private val driverThreadLocal = ThreadLocal<WebDriver>()

    fun setDriver(driver: WebDriver) {
        driverThreadLocal.set(driver)
    }

    fun getDriver(): WebDriver? {
        return try {
            driverThreadLocal.get()
        } catch (e: NullPointerException) {
            null
        }
    }

    fun removeDriver() {
        driverThreadLocal.remove()
    }
}