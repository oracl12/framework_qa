package util

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

object DriverPool {
    fun getDriver(browserName: String): WebDriver {
        return when (browserName) {
            "firefox" -> createFireFoxDriver()
            "chrome" -> createChromeDriver()
            else -> throw Exception("Such browser is not available")
        }
    }

    fun quitDriver(driver: WebDriver) {
        driver.close()
    }

    private fun createChromeDriver(): WebDriver {
        WebDriverManager.chromedriver().setup()
        return ChromeDriver(
            ChromeOptions()
                .addArguments("start-maximized")
                .addArguments("disable-infobars")
                .addArguments("--disable-extensions")
        )
    }

    private fun createFireFoxDriver(): WebDriver {
        WebDriverManager.firefoxdriver().setup()
        val driver = FirefoxDriver(FirefoxOptions())
        driver.manage().window().maximize()
        return driver
    }
}