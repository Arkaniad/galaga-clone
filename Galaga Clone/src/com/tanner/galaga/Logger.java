package com.tanner.galaga;

import java.io.PrintStream;

/**
 * This class is for logging of information.
 * 
 * @author Tanner Danzey < arkaniad AT gmail DOT com >
 */
public class Logger {

	// ------------------
	// Private Variables
	// ------------------
	private final PrintStream output;

	private String tag = "";
	private final String warnTag = "[-!-]";
	private final String errTag = "[!!!]";
	private final String infoTag = "[---]";
	private final String calloutTag = ">>>>>";

	/**
	 * Instantiate the logger.
	 * 
	 * @param newTag
	 *            The desired log tag for more information.
	 * @param out
	 *            The PrintStream object to write logs to.
	 */
	public Logger(String newTag, PrintStream out) {
		tag = newTag;
		output = out;
		dbg("Logging subsystem initialized.");
	}

	public void dbg(String message) {
		output.println(tag + " " + message + "\n");
	}

	public void info(String message) {
		output.println(tag + infoTag + " " + message + "\n");
	}

	public void warn(String message) {
		output.println(tag + warnTag + " " + message + "\n");
	}

	public void error(String message) {
		output.println(tag + errTag + " " + message + "\n");
	}

}
