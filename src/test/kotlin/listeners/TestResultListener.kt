package listeners

import io.qameta.allure.Allure
import io.qameta.allure.Attachment
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
        super.onTestStart(result)
    }

    override fun onTestSuccess(result: ITestResult?) {
        super.onTestSuccess(result)
    }

    override fun onTestFailure(result: ITestResult?) {
        saveTestData(result)
        if (driver != null) {
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

    @Attachment(value = "Db savings for {result.name}")
    private fun saveTestData(result: ITestResult?) {
        val session = HibernateUtil.getSessionFactory().openSession()

        val resultRecord = Result()
        resultRecord.name = result!!.name

        if (result.throwable != null) {
            val msg = result.throwable.toString()
            if (msg.length < 253) {
                resultRecord.throwable = msg
            }
        }
        resultRecord.time = (result.endMillis.minus(result.startMillis)).toString()
        resultRecord.isSuccess = result.isSuccess

        session.beginTransaction()
        session.save(resultRecord)
        session.transaction.commit()
        saveTextLog("Result of test will be saved at db with status: ${result.isSuccess}")
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    private fun takeSnapShot(driver: WebDriver, result: ITestResult?){
        val scrShot = driver as TakesScreenshot
        val srcFile: File = scrShot.getScreenshotAs(OutputType.FILE)
        val destFile = File("E:\\framework_at\\target\\screenshots\\${result!!.name}${result.startMillis}.png")
        FileUtils.moveFile(srcFile, destFile)
        Allure.addAttachment("Screenshot", "image/png", destFile.inputStream(), "png")
        saveTextLog("Screenshot was captured for method: ${result.name}")
    }

    @Attachment(value = "{0}", type = "text/plain")
    private fun saveTextLog(msg: String): String {
        return msg
    }
}