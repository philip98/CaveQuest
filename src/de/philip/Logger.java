package de.philip;

import java.util.Calendar;

public class Logger {

	public static void log(String s) {
		String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		System.out.println("[" + mydate + "] : " + s);
	}

	public static void err(String s) {
		String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		System.err.println("[" + mydate + "] : " + s);
	}

}