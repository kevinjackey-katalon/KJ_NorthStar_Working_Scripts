import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory


import internal.GlobalVariable

/**
 * Listener: OpenandCloseMobileApplication
 * Purpose : Ensure a mobile session is available for each test case.
 *
 * Logic:
 *  - If running on TestCloud (capability kt:requestId is present) => do NOT start app locally.
 *  - Otherwise (local run / no requestId) => start app using GlobalVariable.appPath.
 */
class CloseMobileApplication {

       
    @AfterTestCase
    def afterTestCase(TestCaseContext testCaseContext) {
        KeywordUtil.logInfo("[Listener] AFTER TEST CASE: ${testCaseContext.getTestCaseId()} | status=${testCaseContext.getTestCaseStatus()}")

        // OPTIONAL: close after each test case (uncomment if you want isolation)
        // KeywordUtil.logInfo('[Listener][OPTIONAL] Closing application after test case')
        // Mobile.closeApplication(FailureHandling.OPTIONAL)
    }

    @AfterTestSuite
    def afterTestSuite(TestSuiteContext testSuiteContext) {
        KeywordUtil.logInfo("[Listener] AFTER TEST SUITE: ${testSuiteContext.getTestSuiteId()}")

        // OPTIONAL: close after suite (uncomment if desired)
        // KeywordUtil.logInfo('[Listener][OPTIONAL] Closing application after test suite')
        // Mobile.closeApplication(FailureHandling.OPTIONAL)
    }
}