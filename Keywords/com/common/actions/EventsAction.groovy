package com.common.actions

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling

public class EventsAction {

	/**
	 * Tap on Choose Days & Times button
	 * @param timeout - timeout in seconds to wait for element (default: 15)
	 * @return true if successful, throws exception otherwise
	 */
	@Keyword
	def tapChooseDaysTimesButton(int timeout = 2) {
		try {
			// Create test object dynamically
			TestObject chooseDaysButton = new TestObject('ChooseDaysTimes')
			chooseDaysButton.setSelectorMethod(SelectorMethod.BASIC)
			chooseDaysButton.addProperty('text', ConditionType.CONTAINS, 'Choose Days')

			// Wait for element to be present
			Mobile.waitForElementPresent(chooseDaysButton, timeout)

			// Tap on element
			Mobile.tap(chooseDaysButton, 2)

			println("✓ Successfully tapped Choose Days & Times button")
			return true

		} catch (Exception e) {
			println("✗ Failed to tap Choose Days & Times button: ${e.message}")
			throw e
		}
	}

	/**
	 * Handle reservation flow based on existing reservation status
	 * @param timeout - timeout in seconds
	 */
	@Keyword
	def handleReservationFlow(int timeout = 2) {

		
		TestObject reservationMessage = new TestObject('ReservationMessage')
		reservationMessage.setSelectorMethod(SelectorMethod.BASIC) 
		reservationMessage.addProperty('class', ConditionType.EQUALS, 'android.widget.TextView')
		reservationMessage.addProperty('text', ConditionType.EQUALS, 'You already have a reservation for this Event.')

		// Check if message exists
		boolean messageExists = Mobile.waitForElementPresent(reservationMessage, timeout, FailureHandling.OPTIONAL)

		if (messageExists) {
			// Verify the text content
			String actualText = Mobile.getText(reservationMessage, 5, FailureHandling.OPTIONAL)

			if (actualText != null && actualText.trim().equals('You already have a reservation for this Event.')) {
				Mobile.comment('✓ Existing reservation detected')

				// Tap "Add Another Reservation" button
				TestObject addAnotherBtn = new TestObject('AddAnotherReservation')
				addAnotherBtn.setSelectorMethod(SelectorMethod.BASIC) 
				addAnotherBtn.addProperty('class', ConditionType.EQUALS, 'android.widget.Button')
				addAnotherBtn.addProperty('text', ConditionType.CONTAINS, 'Add Another Reservation')

				Mobile.waitForElementPresent(addAnotherBtn, timeout)
				Mobile.tap(addAnotherBtn, 2)
				Mobile.comment('✓ Tapped "Add Another Reservation"')

				return
			}
		}
		
		Mobile.comment('ℹ No existing reservation - Tapping Choose Days & Times')
		tapChooseDaysTimesButton(timeout)
	}
}
