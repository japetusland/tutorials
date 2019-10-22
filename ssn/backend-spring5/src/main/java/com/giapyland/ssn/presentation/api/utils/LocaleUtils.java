package com.giapyland.ssn.presentation.api.utils;

import java.util.Locale;

import com.giapyland.ssn.utils.StringUtils;

public class LocaleUtils {
	
	public static Locale GetLocale(String clientLanguage) {
		Locale locale = new Locale("en");
		
		if (!StringUtils.IsNullOrWhiteSpace(clientLanguage)) {
			String[] clientLocale = clientLanguage.split("-");
			
			if (clientLocale.length == 1)
				locale = new Locale(clientLocale[0]);
			else
				locale = new Locale(clientLocale[0], clientLocale[1]);
		}
		
		return locale;
	}
}
