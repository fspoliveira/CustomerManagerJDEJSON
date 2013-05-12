package br.com.bitwaysystem.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates é a classe Base que contém um método que faz a validação se uma URL
 * é válida
 * 
 * @author Fernando Santiago
 * @version %I%, %G%
 * @since 1.0
 * */
public class Validates {

	public static String url;

	/**
	 * validateURL
	 * 
	 * @return boolean
	 * @author Fernando Santiago
	 * @version %I%, %G%
	 * @since 1.0
	 * */
	public static boolean validateURL() {

		Pattern p = Pattern.compile("^(https?|ftp|file)://.+$");
		Matcher m = p.matcher(url);
		return (m.matches());
	}
}