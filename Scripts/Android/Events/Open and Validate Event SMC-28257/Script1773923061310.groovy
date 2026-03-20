import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.platform.specific.AndroidOnly as AndroidOnly
import com.common.actions.CommonFunctions as CommonFunctions
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// -----------------------------------------------------------------------------
// TEST CASE: Android/Events/Open and Validate Event SMC-28257
// PURPOSE : Ensure user is on Event Catalogue, search event 'Reservation-SMC-28257',
//           open it, and verify key event details are displayed.
// -----------------------------------------------------------------------------

WebUI.comment('TC PURPOSE: Open Reservation-SMC-28257 from Event Catalogue and validate key details are displayed')

// -----------------------------------------------------------------------------
// STEP 0 - Ensure session is active and user is on the correct page
// Indicator: magnifying glass is visible
// Have to do this as separate if statements because verifyElementExist will error
// if device instance is not found
// -----------------------------------------------------------------------------
WebUI.comment('STEP 0: Verify app session is running and user is on Event Catalogue (magnifying glass visible)')
KeywordUtil.logInfo('STEP 0: Verify app session is running and user is on Event Catalogue (magnifying glass visible)')

boolean applicationrunning = CommonFunctions.checkDeviceRunning()

if (!applicationrunning) {
    WebUI.comment('STEP 0.1: Application instance not found -> Navigate to Event Catalogue (login/init flow)')
    KeywordUtil.logInfo('STEP 0.1: Application instance not found. Navigating to Event Catalogue (login/init flow).')
    WebUI.callTestCase(findTestCase('Android/Events Catalogue/NavigateToEventCatalogue'), [:], FailureHandling.STOP_ON_FAILURE)
} else {
    WebUI.comment('STEP 0.2: Application instance found -> Verify magnifying glass exists (page indicator)')
    KeywordUtil.logInfo('STEP 0.2: Application instance found. Verifying magnifying glass exists (login/page indicator).')

    boolean magnifyingGlassVisible = Mobile.verifyElementExist(
        findTestObject('Android/Events Catalogue/android.widget.Button - magnifying glass'),
        10,
        FailureHandling.OPTIONAL
    )

    if (!magnifyingGlassVisible) {
        WebUI.comment('STEP 0.3: Magnifying glass not found -> Navigate to Event Catalogue')
        KeywordUtil.logInfo('STEP 0.3: Magnifying glass not found. Navigating to Event Catalogue.')
        WebUI.callTestCase(findTestCase('Android/Events Catalogue/NavigateToEventCatalogue'), [:], FailureHandling.STOP_ON_FAILURE)
    } else {
        WebUI.comment('STEP 0.3: Magnifying glass found -> Already on Event Catalogue')
        KeywordUtil.logInfo('STEP 0.3: Magnifying glass found. Already on expected page.')
    }
}

// -----------------------------------------------------------------------------
// STEP 1 - Select/Open the desired reservation/event
// -----------------------------------------------------------------------------
String reservationID = 'Reservation-SMC-28257'
WebUI.comment("STEP 1: Search and open event/reservation '${reservationID}'")
KeywordUtil.logInfo("STEP 1: Search and open event/reservation '${reservationID}'")
AndroidOnly.selectDesiredReservation(reservationID)

// -----------------------------------------------------------------------------
// STEP 2+ - Verify event details are displayed
// -----------------------------------------------------------------------------
WebUI.comment('STEP 2: Verify Event Number is displayed')
KeywordUtil.logInfo('STEP 2: Verify Event Number is displayed')
Mobile.verifyElementExist(findTestObject('Android/Events Catalogue/Event SMC-28257/android.widget.TextView - Event Number'), 10)

WebUI.comment('STEP 3: Verify Participant Count is displayed')
KeywordUtil.logInfo('STEP 3: Verify Participant Count is displayed')
Mobile.verifyElementExist(findTestObject('Android/Events Catalogue/Event SMC-28257/android.widget.TextView - Participant Count'), 10)

WebUI.comment('STEP 4: Verify Event Status is displayed')
KeywordUtil.logInfo('STEP 4: Verify Event Status is displayed')
Mobile.verifyElementExist(findTestObject('Android/Events Catalogue/Event SMC-28257/android.widget.TextView - Event Status'), 10)

WebUI.comment('STEP 5: Verify Event Date is displayed')
KeywordUtil.logInfo('STEP 5: Verify Event Date is displayed')
Mobile.verifyElementExist(findTestObject('Android/Events Catalogue/Event SMC-28257/android.widget.TextView - Event Date'), 10)

WebUI.comment('STEP 6: Verify View details button is displayed')
KeywordUtil.logInfo('STEP 6: Verify View details button is displayed')
Mobile.verifyElementExist(findTestObject('Android/Events Catalogue/android.widget.Button - View details'), 10)

WebUI.comment('STEP 7: Verify Event Time is displayed')
KeywordUtil.logInfo('STEP 7: Verify Event Time is displayed')
Mobile.verifyElementExist(findTestObject('Android/Events Catalogue/Event SMC-28257/android.widget.TextView - Event Time'), 10)

WebUI.comment('STEP 8: Verify Event Location is displayed')
KeywordUtil.logInfo('STEP 8: Verify Event Location is displayed')
Mobile.verifyElementExist(findTestObject('Android/Events Catalogue/Event SMC-28257/android.widget.TextView - Event Location'), 10)

WebUI.comment('END: Open Event SMC-28257 validations completed')
KeywordUtil.logInfo('END: Open Event SMC-28257 validations completed')