import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase 
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.common.actions.CommonFunctions
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.platform.specific.AndroidOnly

// -----------------------------------------------------------------------------
// TEST CASE: Android/Events/Open and Validate Event SMC-28257
// PURPOSE : Ensure user is on Event Catalogue, search event 'Reservation-SMC-28257',
//           open it, and verify key event details are displayed.
// -----------------------------------------------------------------------------

// [TC-DESCRIPTION]
// Precondition:
// - User is logged in OR app can navigate through init/login flow via NavigateToEventCatalogue.
// - Device has the app installed and Appium session can be started.
//
// Expected outcome:
// - Reservation-SMC-28257 is opened successfully.
// - Event details fields (number, participant count, status, date, time, location) and View details button are displayed.

WebUI.comment('TC PURPOSE: Open Reservation-SMC-28257 from Event Catalogue and validate key details are displayed')

// -----------------------------------------------------------------------------
// STEP 0 - Ensure session is active and user is on the correct page
// Indicator: magnifying glass is visible
// Have to do this as separate if statements because verifyElementExist will error
// if device instance is not found
// -----------------------------------------------------------------------------
// [STEP DESCRIPTION]
// Goal: Guarantee test starts from Event Catalogue screen.
// Logic:
//  - If app is not running, run NavigateToEventCatalogue to start session and land on catalogue.
//  - If app is running, verify catalogue indicator (magnifying glass). If missing, navigate again.
WebUI.comment('STEP 0: Verify app session is running and user is on Event Catalogue (magnifying glass visible)')
KeywordUtil.logInfo('STEP 0: Verify app session is running and user is on Event Catalogue (magnifying glass visible)')

boolean applicationrunning = CommonFunctions.isApplicationRunning()

if (!applicationrunning) {
    // [STEP 0.1 DESCRIPTION]
    // Condition: No application instance detected.
    // Action: Execute navigation flow to launch app and open Event Catalogue.
    WebUI.comment('STEP 0.1: Application instance not found -> Navigate to Event Catalogue (login/init flow)')
    KeywordUtil.logInfo('STEP 0.1: Application instance not found. Navigating to Event Catalogue (login/init flow).')
    WebUI.callTestCase(findTestCase('Android/02-Events Catalogue/NavigateToEventCatalogue'), [:], FailureHandling.STOP_ON_FAILURE)
} else {
    // [STEP 0.2 DESCRIPTION]
    // Condition: Application instance detected.
    // Action: Validate current page by checking catalogue indicator.
    WebUI.comment('STEP 0.2: Application instance found -> Verify magnifying glass exists (page indicator)')
    KeywordUtil.logInfo('STEP 0.2: Application instance found. Verifying magnifying glass exists (login/page indicator).') 

    boolean magnifyingGlassVisible = Mobile.verifyElementExist(
        findTestObject('Object Repository/Android/Events Catalogue/android.widget.Button - magnifying glass'),
        10,
        FailureHandling.OPTIONAL
    )

    if (!magnifyingGlassVisible) {
        // [STEP 0.3 DESCRIPTION]
        // Condition: App is running but not on Event Catalogue screen.
        // Action: Navigate to Event Catalogue to reset test starting point.
        WebUI.comment('STEP 0.3: Magnifying glass not found -> Navigate to Event Catalogue')
        KeywordUtil.logInfo('STEP 0.3: Magnifying glass not found. Navigating to Event Catalogue.')
        WebUI.callTestCase(findTestCase('Android/02-Events Catalogue/NavigateToEventCatalogue'), [:], FailureHandling.STOP_ON_FAILURE)
    } else {
        // [STEP 0.3 DESCRIPTION]
        // Condition: App is running and catalogue indicator is present.
        // Action: Continue with test (already on expected screen).
        WebUI.comment('STEP 0.3: Magnifying glass found -> Already on Event Catalogue')
        KeywordUtil.logInfo('STEP 0.3: Magnifying glass found. Already on expected page.')
    }
}

// -----------------------------------------------------------------------------
// STEP 1 - Select/Open the desired reservation/event
// -----------------------------------------------------------------------------
// [STEP DESCRIPTION]
// Goal: Find the target reservation in the catalogue and open its details page.
// Action: Use AndroidOnly.selectDesiredReservation to search/select by reservation ID.
String reservationID = 'Reservation-SMC-28257'
WebUI.comment("STEP 1: Search and open event/reservation '${reservationID}'")
KeywordUtil.logInfo("STEP 1: Search and open event/reservation '${reservationID}'")
AndroidOnly.selectDesiredReservation(reservationID)

// -----------------------------------------------------------------------------
// STEP 2+ - Verify event details are displayed
// -----------------------------------------------------------------------------
// [STEP DESCRIPTION]
// Goal: Validate that key UI fields on the event details screen are present.
// Note: Using CONTINUE_ON_FAILURE to collect all missing fields in one execution.

// STEP 2 - Event Number
// Expected: Event Number field/label/value is visible.
WebUI.comment('STEP 2: Verify Event Number is displayed')
KeywordUtil.logInfo('STEP 2: Verify Event Number is displayed')
Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-28257/android.widget.TextView - Event Number'), 10, FailureHandling.CONTINUE_ON_FAILURE)

// STEP 3 - Participant Count
// Expected: Participant Count field/label/value is visible.
WebUI.comment('STEP 3: Verify Participant Count is displayed')
KeywordUtil.logInfo('STEP 3: Verify Participant Count is displayed')
Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-28257/android.widget.TextView - Participant Count'), 10, FailureHandling.CONTINUE_ON_FAILURE)

// STEP 4 - Event Status
// Expected: Event Status field/label/value is visible.
WebUI.comment('STEP 4: Verify Event Status is displayed')
KeywordUtil.logInfo('STEP 4: Verify Event Status is displayed')
Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-28257/android.widget.TextView - Event Status'), 10, FailureHandling.CONTINUE_ON_FAILURE)

// STEP 5 - Event Date
// Expected: Event Date field/label/value is visible.
WebUI.comment('STEP 5: Verify Event Date is displayed')
KeywordUtil.logInfo('STEP 5: Verify Event Date is displayed')
Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-28257/android.widget.TextView - Event Date'), 10, FailureHandling.CONTINUE_ON_FAILURE)

// STEP 6 - View details button
// Expected: View details button is visible.
WebUI.comment('STEP 6: Verify View details button is displayed')
KeywordUtil.logInfo('STEP 6: Verify View details button is displayed')
Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-28257/android.widget.Button - View Details'), 10, FailureHandling.CONTINUE_ON_FAILURE)

// STEP 7 - Event Time
// Expected: Event Time field/label/value is visible.
WebUI.comment('STEP 7: Verify Event Time is displayed')
KeywordUtil.logInfo('STEP 7: Verify Event Time is displayed')
Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-28257/android.widget.TextView - Event Time'), 10, FailureHandling.CONTINUE_ON_FAILURE)

// STEP 8 - Event Location
// Expected: Event Location field/label/value is visible.
WebUI.comment('STEP 8: Verify Event Location is displayed')
KeywordUtil.logInfo('STEP 8: Verify Event Location is displayed')
Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-28257/android.widget.TextView - Event Location'), 10, FailureHandling.CONTINUE_ON_FAILURE)

// END
// Expected: All validations executed; failures (if any) are reported in the log.
WebUI.comment('END: Open Event SMC-28257 validations completed')
KeywordUtil.logInfo('END: Open Event SMC-28257 validations completed')