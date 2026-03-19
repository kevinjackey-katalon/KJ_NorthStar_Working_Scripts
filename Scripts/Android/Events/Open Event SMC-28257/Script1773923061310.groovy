import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.common.actions.CommonFunctions as CommonFunctions
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


// -----------------------------------------------------------------------------
// STEP 0 - Ensure Session is active and user is on the correct page (magnifying glass visible)
// Have to do this as separate if statements because the verifyElementExist will error if device instance is not found
// -----------------------------------------------------------------------------
KeywordUtil.logInfo('STEP 0: Verify magnifying glass is visible (used as login indicator)')
boolean applicationrunning = CommonFunctions.checkDeviceRunning()

if (!applicationrunning) {
	KeywordUtil.logInfo(' Application instance not found. Executing Login Member test case')
	WebUI.callTestCase(findTestCase('Android/Events Catalogue/NavigateToEventCatalogue'), [:], FailureHandling.STOP_ON_FAILURE)
} else {
		boolean magnifyingGlassVisible = Mobile.verifyElementExist(findTestObject('Event Navigation/android.widget.Button - magnifying glass'), 10, FailureHandling.OPTIONAL)
		
		if (!magnifyingGlassVisible ) {
			KeywordUtil.logInfo('Magnifying Glass not found. Executing Event Catalogue test case')
			WebUI.callTestCase(findTestCase('Android/Events Catalogue/NavigateToEventCatalogue'), [:], FailureHandling.STOP_ON_FAILURE)
		}
	
	}

CommonFunctions.safeTap(findTestObject('Event Navigation/android.widget.Button - magnifying glass'), 10)

CommonFunctions.safeTap(findTestObject('Events Catalogue/android.widget.EditText - Search Events List'), 10)

CommonFunctions.safeSendKeys(findTestObject('android.widget.EditText (4)'), 'Reservation-SMC-28257', 10)

CommonFunctions.safeTap(findTestObject('android.view.View - event Reservation-SMC-2_439a59'), 10)

Mobile.verifyElementExist(findTestObject('Events Catalogue/android.widget.TextView - Event Number'), 10)

Mobile.verifyElementExist(findTestObject('Events Catalogue/android.widget.TextView - Participant Count'), 10)

Mobile.verifyElementExist(findTestObject('Events Catalogue/android.widget.TextView -Event Status'), 10)

Mobile.verifyElementExist(findTestObject('Events Catalogue/android.widget.TextView - Event Date'), 10)

Mobile.verifyElementExist(findTestObject('Events Catalogue/android.widget.Button - View details'), 10)

Mobile.verifyElementExist(findTestObject('Events Catalogue/android.widget.TextView - Event Time'), 10)

Mobile.verifyElementExist(findTestObject('Events Catalogue/android.widget.TextView - Event Location'), 10)

