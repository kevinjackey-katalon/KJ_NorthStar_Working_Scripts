package com.platform.specific

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

public class AndroidOnly {
	
	/**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */
	@Keyword
	def setDriverPreferences_Android(){
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
		
		
		//Set Local Device Run Configuration
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
		
		//Set TestCloud Device Configuration
		RunConfiguration.setDriverPreferencesProperty("Remote",'platformName', GlobalVariable.platformName)
		RunConfiguration.setDriverPreferencesProperty("Remote",'automationName', GlobalVariable.automationName)
		RunConfiguration.setDriverPreferencesProperty("Remote",'autoGrantPermissions', GlobalVariable.autoGrantPermissions)
		RunConfiguration.setDriverPreferencesProperty("Remote",'ignoreHiddenApiPolicyError', GlobalVariable.ignoreHiddenApiPolicyError)
		RunConfiguration.setDriverPreferencesProperty("Remote",'dontStopAppOnReset', GlobalVariable.dontStopAppOnReset)
		RunConfiguration.setDriverPreferencesProperty("Remote",'adbExecTimeout', GlobalVariable.adbExecTimeout)
		RunConfiguration.setDriverPreferencesProperty("Remote",'androidInstallTimeout', GlobalVariable.androidInstallTimeout)
		RunConfiguration.setDriverPreferencesProperty("Remote",'uiautomator2ServerInstallTimeout', GlobalVariable.uiautomator2ServerInstallTimeout)
		RunConfiguration.setDriverPreferencesProperty("Remote",'Uiautomator2ServerLaunchTimeout', GlobalVariable.uiautomator2ServerLaunchTimeout)
		RunConfiguration.setDriverPreferencesProperty("Remote",'appWaitDuration', GlobalVariable.appWaitDuration)
		RunConfiguration.setDriverPreferencesProperty("Remote",'newCommandTimeout', GlobalVariable.newCommandTimeout)
		RunConfiguration.setDriverPreferencesProperty("Remote",'app', GlobalVariable.appPath)
	
	}

}
