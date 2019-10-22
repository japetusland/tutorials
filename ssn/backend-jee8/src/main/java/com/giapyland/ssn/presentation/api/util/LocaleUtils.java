package com.giapyland.ssn.presentation.api.util;

import java.util.Locale;
import java.util.ResourceBundle;

import com.giapyland.ssn.SsnApplication;
import com.giapyland.ssn.utils.StringUtils;

public class LocaleUtils {

	public static String getErrorMessage(String key, String clientLanguage) {
		try {
			Locale cLoc = new Locale("en");
			if (!StringUtils.IsNullOrWhiteSpace(clientLanguage)) {
				String[] clientLocale = clientLanguage.split("-");
				if (clientLocale.length == 1)
					cLoc = new Locale(clientLocale[0]);
				else
					cLoc = new Locale(clientLocale[0], clientLocale[1]);
			}
			ResourceBundle bundle = ResourceBundle.getBundle("com.giapyland.ssn.errors", cLoc);
			return bundle.getString(key);
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
		}
		return "";
	}
}
