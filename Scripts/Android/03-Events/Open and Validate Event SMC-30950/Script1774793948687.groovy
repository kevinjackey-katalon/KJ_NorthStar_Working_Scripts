import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase 
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.common.actions.CommonFunctions
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.platform.specific.AndroidOnly

// -----------------------------------------------------------------------------
// TEST CASE: Android/Events/Open and Validate Event SMC-30950
// ORIGIN  : MOBILE_RECORDER
// PURPOSE : Ensure user is on Event Catalogue, search event 'Reservation-SMC-30950',
//           open it, and validate key UI elements/states along the reservation flow.
// NOTE    : Step documentation added only. Execution logic is unchanged.
// -----------------------------------------------------------------------------

WebUI.comment('TC PURPOSE: Open Reservation-SMC-30950 from Event Catalogue and validate key details are displayed')

// -----------------------------------------------------------------------------
// STEP 0 - Ensure session is active and user is on the correct page
// Expected: App is running AND Event Catalogue page is displayed
// Indicator: Magnifying glass button exists
// Notes: verifyElementExist may error if device instance is not found, so guard with
//        application-running check first.
// -----------------------------------------------------------------------------
WebUI.comment('STEP 0: Verify app session is running and user is on Event Catalogue (magnifying glass visible)')
KeywordUtil.logInfo('STEP 0: Verify app session is running and user is on Event Catalogue (magnifying glass visible)')

boolean applicationrunning = CommonFunctions.isApplicationRunning()

if (!applicationrunning) {
    // STEP 0.1 - No active app instance -> navigate via shared flow
    WebUI.comment('STEP 0.1: Application instance not found -> Navigate to Event Catalogue (login/init flow)')
    KeywordUtil.logInfo('STEP 0.1: Application instance not found. Navigating to Event Catalogue (login/init flow).')
    WebUI.callTestCase(findTestCase('Android/02-Events Catalogue/NavigateToEventCatalogue'), [:], FailureHandling.STOP_ON_FAILURE)
} else {
    // STEP 0.2 - App instance found -> verify page indicator
    WebUI.comment('STEP 0.2: Application instance found -> Verify magnifying glass exists (page indicator)')
    KeywordUtil.logInfo('STEP 0.2: Application instance found. Verifying magnifying glass exists (login/page indicator).') 

    boolean magnifyingGlassVisible = Mobile.verifyElementExist(
        findTestObject('Object Repository/Android/03-Events Catalogue/android.widget.Button - magnifying glass'),
        10,
        FailureHandling.OPTIONAL
    )

    if (!magnifyingGlassVisible) {
        // STEP 0.3 - Not on Event Catalogue -> navigate
        WebUI.comment('STEP 0.3: Magnifying glass not found -> Navigate to Event Catalogue')
        KeywordUtil.logInfo('STEP 0.3: Magnifying glass not found. Navigating to Event Catalogue.')
        WebUI.callTestCase(findTestCase('Android/02-Events Catalogue/NavigateToEventCatalogue'), [:], FailureHandling.STOP_ON_FAILURE)
    } else {
        // STEP 0.3 - Already on Event Catalogue
        WebUI.comment('STEP 0.3: Magnifying glass found -> Already on Event Catalogue')
        KeywordUtil.logInfo('STEP 0.3: Magnifying glass found. Already on expected page.')
    }
}

// -----------------------------------------------------------------------------
// STEP 1 - Search and open the desired reservation/event
// Input   : reservationID
// Action  : uses AndroidOnly.selectDesiredReservation to locate and open item
// -----------------------------------------------------------------------------
String reservationID = 'Reservation-SMC-30950'
WebUI.comment("STEP 1: Search and open event/reservation '${reservationID}'")
KeywordUtil.logInfo("STEP 1: Search and open event/reservation '${reservationID}'")
AndroidOnly.selectDesiredReservation(reservationID)

// -----------------------------------------------------------------------------
// STEP 2 - Open the day/time selection flow
// Action  : Tap 'Choose Days & Times'
// -----------------------------------------------------------------------------
WebUI.comment('STEP 2: Tap Choose Days & Times')
KeywordUtil.logInfo('STEP 2: Tap Choose Days & Times')
CommonFunctions.safeTap('Choose Days and Times Button', findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.Button - Choose Days  Times'), 60)



// -----------------------------------------------------------------------------
// STEP 3 - Edit days for the reservation
// Action  : Tap 'Edit Days'
// -----------------------------------------------------------------------------
WebUI.comment('STEP 3: Tap Edit Days')
KeywordUtil.logInfo('STEP 3: Tap Edit Days')
CommonFunctions.safeTap("Edit Days Item", findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.TextView - Edit Days'), 60)



// -----------------------------------------------------------------------------
// STEP 4 - Select desired time/session
// Actions :
//          - Open time dropdown (Continue on failure)
//          - Choose 10:00 AM - 02:00 PM session 2 (Continue on failure)
// -----------------------------------------------------------------------------
WebUI.comment('STEP 4: Select desired time/session from dropdown')
KeywordUtil.logInfo('STEP 4: Select desired time/session from dropdown')
CommonFunctions.safeTap("Select Desired Time Dropdown", findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.view.View - Select Desired Time'), 10)
//CommonFunctions.safeTap("Select 1000 Session", findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.view.View - 1000 AM - 0200 PM Session 2'), 10)

// STEP 4.1 - (Recorded) Blank comment in original script; kept as-is to avoid logic changes
Mobile.comment('')

// -----------------------------------------------------------------------------
// STEP 5 - Validate selection controls are disabled and day checkbox not checkable
// Verifications (Continue on failure):
//   - Select All enabled=false
//   - Clear Selection enabled=false
//   - Day Selection Checkbox checkable=false
// -----------------------------------------------------------------------------
WebUI.comment('STEP 5: Verify selection controls are disabled (Select All/Clear Selection) and day checkbox is not checkable')
KeywordUtil.logInfo('STEP 5: Verify selection controls are disabled (Select All/Clear Selection) and day checkbox is not checkable')
if (Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.Button - Select All'), 20, FailureHandling.CONTINUE_ON_FAILURE) == true) {
	Mobile.verifyElementAttributeValue(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.Button - Select All'), 'enabled', 'false', 10)
}


if (Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.Button - Clear Selection'), 20, FailureHandling.CONTINUE_ON_FAILURE) == true) {
	Mobile.verifyElementAttributeValue(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.Button - Clear Selection'), 'enabled', 'false', 10)
}

if (Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.CheckBox - Day Selection Checkbox'), 20, FailureHandling.CONTINUE_ON_FAILURE) == true) {
	Mobile.verifyElementAttributeValue(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.CheckBox - Day Selection Checkbox'), 'checkable', 'false', 10)
}

// -----------------------------------------------------------------------------
// STEP 6 - Confirm selected day/time settings
// Action  : Tap Confirm
// -----------------------------------------------------------------------------
WebUI.comment('STEP 6: Tap Confirm to apply day/time selection')
KeywordUtil.logInfo('STEP 6: Tap Confirm to apply day/time selection')
CommonFunctions.safeTap('Confirm Button', findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.Button - Confirm'), 10)

// -----------------------------------------------------------------------------
// STEP 7 - Choose attendee (Myself)
// Action  : Tap Myself (Continue on failure)
// -----------------------------------------------------------------------------
WebUI.comment('STEP 7: Select attendee = Myself')
KeywordUtil.logInfo('STEP 7: Select attendee = Myself')
CommonFunctions.safeTap("Myself Button", findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.Button - Myself'), 20)

// -----------------------------------------------------------------------------
// STEP 8 - Accept waiver/consent
// Action  : Tap Waiver and Consent checkbox (Continue on failure)
// -----------------------------------------------------------------------------
WebUI.comment('STEP 8: Accept Waiver and Consent')
KeywordUtil.logInfo('STEP 8: Accept Waiver and Consent')
CommonFunctions.safeTap('Waiver and Consent Checkbox', findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.checkbox - Waiver and Consent'), 10)

// -----------------------------------------------------------------------------
// STEP 9 - Create reservation
// Action  : Verify Create Reservation button is present and enabled
// -----------------------------------------------------------------------------
WebUI.comment('STEP 9: Verify Create Reservation is clickable')
KeywordUtil.logInfo('STEP 9: Verify Create Reservation is clickable')
Mobile.verifyElementExist(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.Button - Create Reservation'), 10)
Mobile.verifyElementAttributeValue(findTestObject('Object Repository/Android/03-Events Catalogue/Event SMC-30950/android.widget.Button - Create Reservation'), 'clickable', 'true', 10)


WebUI.comment('END: Open Event SMC-30950 validations completed')
KeywordUtil.logInfo('END: Open Event SMC-30950 validations completed')