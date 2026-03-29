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

/**
 * Common reusable helper functions across Mobile test cases.
 *
 * Notes:
 * - This class focuses on “safe” interactions that wait/retry before performing actions.
 * - Logging is intentionally verbose to aid troubleshooting in execution logs.
 * - Per request: documentation/comments were added only; coding logic/flow remains unchanged.
 */
class CommonFunctions {

	/**
	 * Determine if the current mobile application/device session instance is running.
	 *
	 * Step documentation:
	 * 1) Log that a device check is starting.
	 * 2) Attempt to obtain the current Appium driver from {@link MobileDriverFactory}.
	 * 3) If a driver exists, read driver capabilities.
	 * 4) Check capability 'kt:requestId' (used by some execution environments) to infer an active session.
	 * 5) If any exception occurs (e.g., driver not created yet), log the reason and return false.
	 *
	 * @return true if an Appium driver is available and has a non-null 'kt:requestId' capability; otherwise false.
	 */
	static boolean isApplicationRunning () {
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

				// STEP 2 - Mark device as active if requestId is present
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
	 * Safely taps a mobile element with retry and optional keyboard handling.
	 *
	 * Step documentation:
	 * 1) Log the action intent (object name).
	 * 2) Normalize timeout (if 0 -> default 10 seconds).
	 * 3) Retry loop up to {@code retries} times:
	 *    - Wait for element present (OPTIONAL).
	 *    - If found, tap (OPTIONAL) and exit loop.
	 * 4) If not found after all retries, mark test failed and stop execution.
	 * 5) Hide keyboard unless {@code nohideKeyboard} is true.
	 *
	 * @param objectName Friendly name used for logging.
	 * @param to Katalon TestObject representing the target element.
	 * @param timeout Max seconds to wait for presence on each attempt (0 will be treated as 10).
	 * @param retries Number of attempts before failing.
	 * @param nohideKeyboard When false, hides the keyboard after the action.
	 */
	static void safeTap(String objectName, TestObject to, int timeout = 10, int retries = 3, boolean nohideKeyboard = false) {

		KeywordUtil.logInfo("[CommonFunctions][safeTap] Attempting to tap element '${objectName}'")

		// STEP 1 - Normalize timeout
		if (timeout == 0) {
			timeout = 30
		}

		int tapLoopCount = 1

		// STEP 2 - Wait/retry loop
		for (tapLoopCount = 1; tapLoopCount <= retries; tapLoopCount++) {
			
			KeywordUtil.logInfo('In safeTap retry loop, iteration: ' + tapLoopCount + ', object timeout: ' + timeout)

			if (Mobile.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL) == true) {
				KeywordUtil.logInfo("Element found on attempt ${tapLoopCount}, proceeding to tap")
				Mobile.tap(to, timeout, FailureHandling.OPTIONAL)
				break
			}
		}

		// STEP 3 - Fail if all retries exhausted
		if (tapLoopCount > retries) {
			KeywordUtil.markFailed("Failed to tap element ${objectName} after ${retries} attempts")
			KeywordUtil.markFailedAndStop("Failed to tap element ${objectName} after ${retries} attempts")

		}

		// STEP 4 - Hide any keyboard elements (unless suppressed)
		if (nohideKeyboard == false) {
			Mobile.hideKeyboard()
		}
	}


	/**
	 * Safely sends keys to a mobile element with retry and optional keyboard handling.
	 *
	 * Step documentation:
	 * 1) Normalize timeout (if 0 -> default 10 seconds).
	 * 2) Log the action intent (object name + input value).
	 * 3) Retry loop up to {@code retries} times:
	 *    - Wait for element present (OPTIONAL).
	 *    - If found, tap (OPTIONAL) then send keys (OPTIONAL), and exit loop.
	 * 4) If not found after all retries, mark test failed and stop execution.
	 * 5) Hide keyboard unless {@code nohideKeyboard} is true.
	 *
	 * @param objectName Friendly name used for logging.
	 * @param to Katalon TestObject representing the target input element.
	 * @param typedvalue Value to send to the element.
	 * @param timeout Max seconds to wait for presence on each attempt (0 will be treated as 10).
	 * @param retries Number of attempts before failing.
	 * @param nohideKeyboard When false, hides the keyboard after the action.
	 */
	static void safeSendKeys(String objectName, TestObject to, String typedvalue, int timeout = 10, int retries = 3, boolean nohideKeyboard = false) {

		// STEP 1 - Normalize timeout
		if (timeout == 0) {
			timeout = 30
		}

		KeywordUtil.logInfo("[CommonFunctions][safeSendKeys] Attempting to send keys to element '${objectName}' with value '${typedvalue}'")

		int sendKeysLoopCount = 1

		// STEP 2 - Wait/retry loop
		for (sendKeysLoopCount = 1; sendKeysLoopCount <= retries; sendKeysLoopCount++) {
			
			KeywordUtil.logInfo('In safeSendKeys retry loop, iteration: ' + sendKeysLoopCount + ', object timeout: ' + timeout)

			if (Mobile.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL) == true) {
				KeywordUtil.logInfo("Element found on attempt ${sendKeysLoopCount}, proceeding to tap and send keys")
				Mobile.tap(to, timeout, FailureHandling.OPTIONAL)
				Mobile.sendKeys(to, typedvalue, FailureHandling.OPTIONAL)
				break
			}
		}

		// STEP 3 - Fail if all retries exhausted
		if (sendKeysLoopCount > retries) {
			KeywordUtil.markFailed("Failed to send keys to element ${objectName} after ${retries} attempts")
			KeywordUtil.markFailedAndStop("Failed to send keys to element ${objectName} after ${retries} attempts")

		}

		// STEP 4 - Hide any keyboard elements (unless suppressed)
		if (nohideKeyboard == false) {
			Mobile.hideKeyboard()
		}
	}


	/**
	 * Get mobile driver for current session.
	 *
	 * Step documentation:
	 * 1) Retrieve and return the current driver instance from {@link MobileDriverFactory}.
	 *
	 * @return mobile driver for current session.
	 */
	@Keyword
	def WebDriver getCurrentSessionMobileDriver() {
		return MobileDriverFactory.getDriver();
	}
}