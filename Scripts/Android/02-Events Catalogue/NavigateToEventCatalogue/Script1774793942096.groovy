import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase 
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.common.actions.CommonFunctions as CommonFunctions
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Test Case: Android/Events Catalogue/NavigateToEventCatalogue
 * Purpose : Ensure user is logged in, then open the Dock Menu and navigate to "Events Catalogue" via menu search.
 * Pre-req : App is launched and on a state where the Dock Menu can be accessed after login.
 * Notes   : This test case performs login by calling 'Android/Login/Login Member' if Dock Menu is not visible.
 */

// -----------------------------------------------------------------------------
// STEP 0 - Ensure Session is active and user is logged in (Dock Menu visible)
// Have to do this as separate if statements because the verifyElementExist will error if device instance is not found
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 0: Verify Dock Menu is visible (used as login indicator)')
boolean applicationrunning = CommonFunctions.isApplicationRunning()

if (!applicationrunning) {
	KeywordUtil.logInfo(' Application instance not found. Executing Login Member test case')
	WebUI.callTestCase(findTestCase('Android/01-Login/Login Member'), [:], FailureHandling.STOP_ON_FAILURE)
} else {
		boolean dockMenuVisible = Mobile.verifyElementExist(findTestObject('Android/02-Dock Menu/android.widget.ImageView - Dock Menu Button'), 10, FailureHandling.OPTIONAL)
		
		if (!dockMenuVisible ) {
			KeywordUtil.logInfo('Dock Menu not found. Executing Login Member test case')
			WebUI.callTestCase(findTestCase('Android/01-Login/Login Member'), [:], FailureHandling.STOP_ON_FAILURE)
			Mobile.delay(20)
		}
	}

// -----------------------------------------------------------------------------
// STEP 1 - Open Dock Menu
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 1: Tap Dock Menu button')
CommonFunctions.safeTap("Dock Menu Button", findTestObject('Android/02-Dock Menu/android.widget.ImageView - Dock Menu Button'), 60)

// -----------------------------------------------------------------------------
// STEP 2 - Wait for menu to render
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 2: Wait for Dock Menu to load')
Mobile.delay(5)

// -----------------------------------------------------------------------------
// STEP 3 - Search for Events Catalogue in menu
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 3: Tap Menu Search Field')
CommonFunctions.safeTap("Tap Menu Search Field", findTestObject('Android/02-Dock Menu/android.widget.EditText  - Menu Search Field'), 60)
Mobile.delay(5)

KeywordUtil.logInfo('STEP 4: Type "Events Catalogue" into Menu Search Field')
CommonFunctions.safeSendKeys("Type into Menu Search Field", findTestObject('Android/02-Dock Menu/android.widget.EditText  - Menu Search Field'), 'Events Catalogue', 10)

KeywordUtil.logInfo('STEP 5: Hide keyboard (if displayed)')
Mobile.hideKeyboard()

// -----------------------------------------------------------------------------
// STEP 4 - Open Events Catalogue
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 6: Tap "Events Catalogue" menu item')
CommonFunctions.safeTap("Events Catalogue Element", findTestObject('Android/02-Dock Menu/android.widget.Button - Events Catalogue'), 10)
Mobile.delay(10)
