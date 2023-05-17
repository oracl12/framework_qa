package util

import org.openqa.selenium.WebDriver

object DriverFactory {
    private val driverThreadLocal = ThreadLocal<WebDriver>()

    fun setDriver(driver: WebDriver) {
        driverThreadLocal.set(driver)
    }

    fun getDriver(): WebDriver {
        return driverThreadLocal.get()
    }

    fun removeDriver() {
        driverThreadLocal.remove()
    }
}