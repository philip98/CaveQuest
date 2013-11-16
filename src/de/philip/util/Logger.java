package de.philip.util;

import java.util.Calendar;

public class Logger {

	public static void log(Object o) {
		String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		System.out.println("[" + mydate + "] : " + o.toString());
	}

	public static void err(Object o) {
		String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		System.err.println("[" + mydate + "] : " + o.toString());
	}

}