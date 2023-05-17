
import bo.BoardBO
import bo.LoginBO
import bo.MainWorkingBO
import constants.*
import listeners.TestResultListener
import util.DriverPool
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.AssertJUnit.assertEquals
import org.testng.AssertJUnit.assertTrue
import org.testng.annotations.*
import util.DriverFactory

@Listeners(TestResultListener::class)
class WebUITests {
    private var driver: WebDriver? = null
    private var randomNumber: Int? = null

    @Parameters("browser")
    @BeforeClass
    fun setUp(browserName: String) {
        driver = DriverPool.getDriver(browserName)
        randomNumber = (0..1000).random()
        driver!!["https://trello.com/login"]
        DriverFactory.setDriver(driver!!)
    }

    @Test
    fun loginTest(){
        LoginBO(driver!!).login(SharedTrelloConstants.trelloEmail, SharedTrelloConstants.trelloPassword)

        WebDriverWait(driver, 4, 4000).until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"header\"]/a/div/div")
            )
        )
        assertTrue(driver!!.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div[6]/button/div/span")).isDisplayed)
    }

    @Test(dependsOnMethods = ["loginTest"])
    fun createBoard(){
        MainWorkingBO(driver!!).createBoard("Some great$randomNumber name")

        WebDriverWait(driver, 3, 3000).until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"content\"]/div/div[1]/div[1]/div/div/span[1]/div[2]/h1")
            )
        )
        assertEquals(driver!!.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div[1]/div/div/span[1]/div[2]/h1")).text, "Some great$randomNumber name")
    }

    @Test(dependsOnMethods = ["createBoard"])
    fun createCardForBoard(){
        BoardBO(driver!!).createList("Some another great$randomNumber name")

        WebDriverWait(driver, 1, 1000).until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"board\"]/div[1]/div/div[1]/textarea")
            )
        )
        assertEquals(driver!!.findElement(By.xpath("//*[@id=\"board\"]/div[1]/div/div[1]/textarea")).text, "Some another great$randomNumber name")
    }

    @AfterClass
    fun closeDriver(){
        DriverFactory.removeDriver()
        DriverPool.quitDriver(driver!!)
    }
}