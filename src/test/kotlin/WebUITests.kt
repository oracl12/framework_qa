
import bo.BoardBO
import bo.LoginBO
import bo.MainWorkingBO
import constants.*
import listeners.TestResultListener
import util.DriverPool
import org.openqa.selenium.WebDriver
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
        DriverFactory.setDriver(driver!!)
    }

    @Test
    fun loginTest(){
        driver!!["https://trello.com/login"]
        LoginBO(driver!!).login(SharedTrelloConstants.trelloEmail, SharedTrelloConstants.trelloPassword)
        LoginBO(driver!!).checkIfLoggedIn()
    }

    @Test(dependsOnMethods = ["loginTest"])
    fun createBoard(){
        MainWorkingBO(driver!!).createBoard("Some great$randomNumber name")
        MainWorkingBO(driver!!).checkIfBoardCreatedAndWeOnIt("Some great$randomNumber name")
    }

    @Test(dependsOnMethods = ["createBoard"])
    fun createCardForBoard(){
        BoardBO(driver!!).createList("Some another great$randomNumber name")
        BoardBO(driver!!).checkIfListCreated("Some another great$randomNumber name")
    }

    @AfterClass
    fun closeDriver(){
        DriverFactory.removeDriver()
        DriverPool.quitDriver(driver!!)
    }
}