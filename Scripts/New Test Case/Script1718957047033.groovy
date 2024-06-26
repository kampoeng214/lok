import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.text.SimpleDateFormat
import java.util.Date
import groovy.json.JsonOutput



WebUI.openBrowser('')

WebUI.navigateToUrl('https://10.8.60.18:5601')

// Execute JavaScript to click on "Advanced" link
String advanceLinkScript = 'document.getElementById("details-button").click();'

WebUI.executeJavaScript(advanceLinkScript, null)

// Wait for the "Proceed to" link to appear
WebUI.delay(1)

// Execute JavaScript to click on "Proceed to [site]" link
String proceedLinkScript = 'document.getElementById("proceed-link").click();'

WebUI.executeJavaScript(proceedLinkScript, null)

// Wait for the "Proceed to" link to appear
WebUI.delay(10)

WebUI.setText(findTestObject('Object Repository/Page_Elastic/input_Username_username'), 'elastic')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Elastic/input_Password_password'), 'quUC2paKxlLpNl26o/O3Tg==')

WebUI.sendKeys(findTestObject('Object Repository/Page_Elastic/input_Password_password'), Keys.chord(Keys.ENTER))

WebUI.delay(2)

WebUI.navigateToUrl('https://10.8.60.18:5601/app/home')

WebUI.delay(5)

// Define a list of URLs or scenarios
def urls = [
	'https://10.8.60.18:5601/app/r/s/CEIac',
	'https://10.8.60.18:5601/app/r/s/yrq1u',
	'https://10.8.60.18:5601/app/r/s/osfsA'
]

// Telegram bot token and chat ID
String telegramBotToken = "5546381658:AAEdmGEOp6hd892KQnQpHkxUBo7EBM1XJ1w"
String chatId = "-614880073"

// Function to send image to Telegram
def sendImageToTelegram(String botToken, String chatId, String imagePath) {
	def command = [
		'curl',
		'-F', "chat_id=${chatId}",
		'-F', "photo=@${imagePath}",
		"https://api.telegram.org/bot${botToken}/sendPhoto"
	]
	
	def process = command.execute()
	process.waitFor()
	if (process.exitValue() == 0) {
		println "Image sent to Telegram successfully."
	} else {
		println "Failed to send image to Telegram: ${process.err.text}"
	}
}

// Iterate through each URL and capture screenshots with unique names
urls.each { url ->
	WebUI.navigateToUrl(url)
	
	// Add delay after opening URL (e.g., 5 seconds)
	WebUI.delay(15)
	
	// Generate a unique filename based on timestamp
	String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
	String screenshotName = "screenshot_${timestamp}.png"	
	String screenshotPath = "C:/Users/Lenovo/Pictures/lol/${screenshotName}"
	
	// Take screenshot and save with unique filename
	WebUI.takeScreenshot("C:/Users/Lenovo/Pictures/lol/${screenshotName}") // Save screenshots in a 'Screenshots' folder
	println "Screenshot captured: ${screenshotName}"
	sendImageToTelegram(telegramBotToken, chatId, screenshotPath)
	
	// Add delay between screenshots (e.g., 5 seconds)
	WebUI.delay(15)
}
