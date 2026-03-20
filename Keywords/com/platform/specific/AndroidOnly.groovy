package com.platform.specific

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.common.actions.CommonFunctions as CommonFunctions
import internal.GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

/**
 * Android-only custom keywords.
 *
 * Notes:
 * - Keep this class Android specific (object IDs point to Android repository paths).
 * - Capabilities are sourced from GlobalVariables (Execution Profile).
 */
public class AndroidOnly {

	/**
	 * Keyword: setDriverPreferences_Android
	 *
	 * Purpose:
	 * - Apply Android desired capabilities (Appium) from GlobalVariables into Katalon RunConfiguration.
	 * - Supports both:
	 *   1) Local device execution via `setMobileDriverPreferencesProperty`
	 *   2) Remote/TestCloud execution via `setDriverPreferencesProperty("Remote", ... )`
	 *
	 * Preconditions:
	 * - Relevant GlobalVariables are defined in the active Execution Profile, e.g.
	 *   platformName, automationName, appPath, timeouts, etc.
	 *
	 * Expected result:
	 * - Driver preferences are set before `Mobile.startApplication()` / `Mobile.startExistingApplication()`.
	 */
	@Keyword
	def setDriverPreferences_Android() {
		// -----------------------------------------------------------------------------
		// LOG - Print capability values from GlobalVariable (helps troubleshooting)
		// -----------------------------------------------------------------------------
		KeywordUtil.logInfo("Applying mobile caps from Global Variables: " +
			"ignoreHiddenApiPolicyError=${GlobalVariable.ignoreHiddenApiPolicyError}, " +
			"PlatformName=${GlobalVariable.platformName}, " +
			"AutomationName=${GlobalVariable.automationName}, " +
			"AutoGrantPermissions=${GlobalVariable.autoGrantPermissions}, " +
			"DontStopAppOnReset=${GlobalVariable.dontStopAppOnReset}, " +
			"AdbExecTimeout=${GlobalVariable.adbExecTimeout}, " +
			"AndroidInstallTimeout=${GlobalVariable.androidInstallTimeout}, " +
			"Uiautomator2ServerLaunchTimeout=${GlobalVariable.uiautomator2ServerLaunchTimeout}, " +
			"NewCommandTimeout=${GlobalVariable.newCommandTimeout}, " +
			"AppWaitDuration=${GlobalVariable.appWaitDuration}, " +
			"app=${GlobalVariable.appPath}, " +
			"Uiautomator2ServerInstallTimeout=${GlobalVariable.uiautomator2ServerInstallTimeout}")

		// -----------------------------------------------------------------------------
		// LOCAL DEVICE - Set Mobile driver preferences (used by Katalon Mobile keywords)
		// -----------------------------------------------------------------------------
		RunConfiguration.setMobileDriverPreferencesProperty('platformName', GlobalVariable.platformName)
		RunConfiguration.setMobileDriverPreferencesProperty('automationName', GlobalVariable.automationName)
		RunConfiguration.setMobileDriverPreferencesProperty('autoGrantPermissions', GlobalVariable.autoGrantPermissions)
		RunConfiguration.setMobileDriverPreferencesProperty('ignoreHiddenApiPolicyError', GlobalVariable.ignoreHiddenApiPolicyError)
		RunConfiguration.setMobileDriverPreferencesProperty('dontStopAppOnReset', GlobalVariable.dontStopAppOnReset)
		RunConfiguration.setMobileDriverPreferencesProperty('adbExecTimeout', GlobalVariable.adbExecTimeout)
		RunConfiguration.setMobileDriverPreferencesProperty('androidInstallTimeout', GlobalVariable.androidInstallTimeout)
		RunConfiguration.setMobileDriverPreferencesProperty('uiautomator2ServerInstallTimeout', GlobalVariable.uiautomator2ServerInstallTimeout)
		RunConfiguration.setMobileDriverPreferencesProperty('uiautomator2ServerLaunchTimeout', GlobalVariable.uiautomator2ServerLaunchTimeout)
		RunConfiguration.setMobileDriverPreferencesProperty('appWaitDuration', GlobalVariable.appWaitDuration)
		RunConfiguration.setMobileDriverPreferencesProperty('newCommandTimeout', GlobalVariable.newCommandTimeout)
		RunConfiguration.setMobileDriverPreferencesProperty('app', GlobalVariable.appPath)

		// -----------------------------------------------------------------------------
		// REMOTE/TESTCLOUD - Set Remote driver preferences (used when running Remote)
		// -----------------------------------------------------------------------------
		RunConfiguration.setDriverPreferencesProperty('Remote', 'platformName', GlobalVariable.platformName)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'automationName', GlobalVariable.automationName)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'autoGrantPermissions', GlobalVariable.autoGrantPermissions)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'ignoreHiddenApiPolicyError', GlobalVariable.ignoreHiddenApiPolicyError)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'dontStopAppOnReset', GlobalVariable.dontStopAppOnReset)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'adbExecTimeout', GlobalVariable.adbExecTimeout)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'androidInstallTimeout', GlobalVariable.androidInstallTimeout)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'uiautomator2ServerInstallTimeout', GlobalVariable.uiautomator2ServerInstallTimeout)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'Uiautomator2ServerLaunchTimeout', GlobalVariable.uiautomator2ServerLaunchTimeout)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'appWaitDuration', GlobalVariable.appWaitDuration)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'newCommandTimeout', GlobalVariable.newCommandTimeout)
		RunConfiguration.setDriverPreferencesProperty('Remote', 'app', GlobalVariable.appPath)
	}

	/**
	 * Keyword: selectDesiredReservation
	 *
	 * Purpose:
	 * - Search for a reservation/event by ID from the Event Catalogue and open it.
	 *
	 * Params:
	 * @param reservationID the reservation/event identifier to search for.
	 *
	 * Preconditions:
	 * - User is on a screen where the Event search (magnifying glass) is visible.
	 * - The referenced Android Test Objects exist in Object Repository.
	 *
	 * Expected result:
	 * - The reservation detail page is opened for the matching search result.
	 */
	@Keyword
	static void selectDesiredReservation(String reservationID) {
		// -----------------------------------------------------------------------------
		// STEP 1 - Open Event List Search
		// -----------------------------------------------------------------------------
		KeywordUtil.logInfo('STEP 1: Tap magnifying glass to open Event search')
		CommonFunctions.safeTap(findTestObject('Android/Events Catalogue/android.widget.Button - magnifying glass'), 10)

		// -----------------------------------------------------------------------------
		// STEP 2 - Focus and populate Search Field
		// -----------------------------------------------------------------------------
		KeywordUtil.logInfo('STEP 2: Tap Search Events input field')
		CommonFunctions.safeTap(findTestObject('Android/Events Catalogue/android.widget.EditText - Search Events List'), 10)

		// -----------------------------------------------------------------------------
		// STEP 3 - Search for the targeted event
		// -----------------------------------------------------------------------------
		String eventSearchText = reservationID
		KeywordUtil.logInfo('STEP 3: Enter search text: ' + eventSearchText)
		CommonFunctions.safeSendKeys(findTestObject('Android/Events Catalogue/android.widget.EditText - Search Events List'), eventSearchText, 10)
		Mobile.delay(5) // Step description: Allow time for search result list to render

		// -----------------------------------------------------------------------------
		// STEP 4 - Open the event from search results
		// -----------------------------------------------------------------------------
		KeywordUtil.logInfo('STEP 4: Select event from results: ' + eventSearchText)
		CommonFunctions.safeTap(findTestObject('Object Repository/Android/Events Catalogue/android.widget.button - Click Reservation Search Result'), 10)
		Mobile.delay(5) // Step description: Allow time for event detail page to load
	}
}
