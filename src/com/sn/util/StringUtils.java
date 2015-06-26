package com.sn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	static public boolean isBlank(String s) {
		if (s == null || s.length() == 0 || s.equals("")
				|| s.trim().length() <= 0)
			return true;
		return false;
	}

	static public boolean isBlank(StringBuilder s) {
		if (s == null || s.length() == 0 || s.equals(""))
			return true;
		return false;
	}

	static public boolean isValidEmail(String s) {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(s);
		boolean matchFound = m.matches();
		return matchFound;
	}

	static public boolean isValidEmailOLD(String s) {

		if (!isBlank(s)) {
			int atPos = s.indexOf("@");
			int dtPos = s.indexOf(".");
			if (atPos < 1 || dtPos < atPos + 2 || dtPos + 2 >= s.length()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
