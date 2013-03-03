package br.com.bitwaysystem.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validates {

	public static String url;

	public static boolean validateURL() {

		Pattern p = Pattern.compile("^(https?|ftp|file)://.+$");
		Matcher m = p.matcher(url);
		return (m.matches());

	}

}