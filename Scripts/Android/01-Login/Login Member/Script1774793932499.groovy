import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.common.actions.CommonFunctions as CommonFunctions 
import com.platform.specific.AndroidOnly as AndroidOnly
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

/**
 * Test Case: Android/Login/Login Member
 * Purpose : Log in to the Android app using member number and password.
 * Data    : GlobalVariable.loginUsername, GlobalVariable.loginPassword
 * Notes   : If GlobalVariable.autoGrantPermissions = false, handle permission popups after login.
 */

// -----------------------------------------------------------------------------
// STEP 0 - Set Android driver preferences and launch application
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 0: Set Android driver preferences (platform-specific)')
AndroidOnly.setDriverPreferences_Android()
Mobile.startApplication(GlobalVariable.appPath, true)
Mobile.delay(25) // Wait for app to load, adjust as needed

// -----------------------------------------------------------------------------
// STEP 1 - Navigate to Login screen
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 1: Tap "Continue to Login"')
CommonFunctions.safeTap("Continue to Login Button", findTestObject('Object Repository/Android/01-Login/android.widget.Button - Continue to Login'),30)

// -----------------------------------------------------------------------------
// STEP 2 - Enter credentials (Member Number + Password)
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 2: Enter Member Number')
CommonFunctions.safeSendKeys("Enter User ID",findTestObject('Object Repository/Android/01-Login/android.widget.member_number'), GlobalVariable.loginUsername)

KeywordUtil.logInfo('STEP 3: Enter Password')
CommonFunctions.safeSendKeys("Enter Password", findTestObject('Object Repository/Android/01-Login/android.widget.member_password'), GlobalVariable.loginPassword)

// -----------------------------------------------------------------------------
// STEP 3 - Submit login
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 4: Tap "Sign In"')
CommonFunctions.safeTap("Signin Button", findTestObject('Object Repository/Android/01-Login/android.widget.Button - Sign In'))

// -----------------------------------------------------------------------------
// STEP 4 - Handle permission popups (only when AutoGrantPermissions is disabled)
// -----------------------------------------------------------------------------
KeywordUtil.logInfo("STEP 5: Handle permission popups if autoGrantPermissions = ${GlobalVariable.autoGrantPermissions}")

if (GlobalVariable.autoGrantPermissions == false) {

	// Calendar permission
	if (Mobile.verifyElementExist(findTestObject('Android/01-Login/android.widget.Button - Allow Calendar'), 5, FailureHandling.OPTIONAL)) {
		KeywordUtil.logInfo(' - Calendar permission popup detected. Tapping "Allow"')
		CommonFunctions.safeTap(findTestObject('Android/01-Login/android.widget.Button - Allow Calendar'), 0)
	}

	// Camera access informational / continue dialog
	if (Mobile.verifyElementExist(findTestObject('Android/01-Login/android.widget.Button - CONTINUE - camera access'), 5, FailureHandling.OPTIONAL)) {
		KeywordUtil.logInfo(' - Camera access "CONTINUE" popup detected. Tapping "CONTINUE"')
		CommonFunctions.safeTap(findTestObject('Android/01-Login/android.widget.Button - CONTINUE - camera access'), 0)
	}

	// Camera permission (while using the app)
	if (Mobile.verifyElementExist(findTestObject('Android/01-Login/android.widget.Button - While using the app - take pictures'), 5, FailureHandling.OPTIONAL)) {
		KeywordUtil.logInfo(' - Camera permission popup detected. Tapping "While using the app"')
		CommonFunctions.safeTap(findTestObject('Android/01-Login/android.widget.Button - While using the app - take pictures'), 0)
	}
}

//Allow time for login to complete
Mobile.delay(5)