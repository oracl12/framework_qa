package listeners

import models.Result
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult
import util.DriverFactory
import util.HibernateUtil
import java.io.File


class TestResultListener : ITestListener {
    private var driver: WebDriver? = null

    override fun onStart(context: ITestContext?) {
        // Not implemented
    }

    override fun onTestStart(result: ITestResult?) {
        driver = DriverFactory.getDriver()
    }

    override fun onTestSuccess(result: ITestResult?) {
        saveTestData(result)
        super.onTestSuccess(result)
    }

    override fun onTestFailure(result: ITestResult?) {
        saveTestData(result)
        println(driver)
        println("123123")
        if (driver != null) {
            println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            takeSnapShot(driver!!, result)
        }

        super.onTestFailure(result)
    }

    override fun onTestSkipped(result: ITestResult?) {
        // Not implemented
    }

    override fun onTestFailedButWithinSuccessPercentage(result: ITestResult?) {
        // Not implemented
    }

    override fun onFinish(context: ITestContext?) {
        // Not implemented
    }

    private fun saveTestData(result: ITestResult?) {
        val session = HibernateUtil.getSessionFactory().openSession()

        val resultRecord = Result()
        resultRecord.name = result!!.name
        if (result.throwable != null) {
            resultRecord.throwable = result.throwable.toString()
        }
        resultRecord.time = (result.endMillis.minus(result.startMillis)).toString()
        resultRecord.isSuccess = result.isSuccess

        session.beginTransaction()
        session.save(resultRecord)
        session.transaction.commit()
    }

    private fun takeSnapShot(driver: WebDriver, result: ITestResult?){
        val scrShot = driver as TakesScreenshot
        val srcFile: File = scrShot.getScreenshotAs(OutputType.FILE)
        val destFile = File("E:\\framework_at\\target\\screenshots\\${result!!.name}.png")
        FileUtils.moveFile(srcFile, destFile)
    }
}