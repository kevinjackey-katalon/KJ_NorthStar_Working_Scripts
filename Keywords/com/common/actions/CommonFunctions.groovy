package com.common.actions
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Capabilities
import io.appium.java_client.AppiumDriver



class CommonFunctions {
	
	/**
	 * Determine if device instance is currently running
	 */
	static boolean checkDeviceRunning () {
		boolean deviceActive = false
		
		KeywordUtil.logInfo("[CommonFunctions] Device Check ")
		
				// STEP 1 - Get driver + capabilities (if available)
				KeywordUtil.logInfo('[CommonFunctions][STEP 1] Attempt to get current Appium session & capabilities')
		
				String requestId = null
				try {
					AppiumDriver driver = MobileDriverFactory.getDriver()
					if (driver != null) {
						Capabilities caps = driver.getCapabilities()
						requestId = (caps != null) ? (String) caps.getCapability('kt:requestId') : null
						
						if (requestId != null) {
							deviceActive = true
						}
					}
				} catch (Throwable t) {
					// Defensive: in some flows there may be no driver yet.
					KeywordUtil.logInfo('[CommonFunctions] No driver available at this point. Reason: ' + t.getMessage())
					deviceActive = false
				}
				
		return deviceActive
	}
	
	/**
	 * Perform waitForElement loop when performing a tap action in order to prevent stale element errors
	 */
	static void safeTap(TestObject to, int timeout = 10, int retries = 3) {
				
				if (timeout == 0) {
					timeout = 10
				}
		
				int attempt = 0
		
				while (attempt < retries) {
					try {
						Mobile.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)
						Mobile.tap(to, timeout, FailureHandling.OPTIONAL)
		
						return
		
					} catch (Exception e) {
		
						attempt++
		
						if (attempt >= retries) {
							KeywordUtil.markFailed("Failed to tap element after ${retries} attempts: " + e.message)
						}
		
						Mobile.delay(1)
					}
				}
			}
		
	/**
	 * Perform waitForElement and tap loop when performing a sendKeys action in order to prevent stale element errors
	 */
	static void safeSendKeys(TestObject to, String text, int timeout = 10, int retries = 3) {
		if (timeout == 0) {
			timeout = 10
		}

		int attempt = 0

		while (attempt < retries) {
			try {
				Mobile.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)
				Mobile.tap(to, timeout, FailureHandling.OPTIONAL)
				Mobile.setText(to, text, timeout)

				return

			} catch (Exception e) {

				attempt++

				if (attempt >= retries) {
					KeywordUtil.markFailed("Failed to send keys after ${retries} attempts: " + e.message)
				}

				Mobile.delay(1)
			}
		}
		
		//Hide any keyboard elements
		Mobile.hideKeyboard()
	}
			

	/**
	 * Get mobile driver for current session
	 * @return mobile driver for current session
	 */
	@Keyword
	def WebDriver getCurrentSessionMobileDriver() {
		return MobileDriverFactory.getDriver();
	}
}