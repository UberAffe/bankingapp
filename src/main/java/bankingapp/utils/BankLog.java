package bankingapp.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bankingapp.Main;

public class BankLog {

	private static Logger log = LogManager.getLogger(Main.class);
	
	public static void info(String info) {
		log.info(info);
	}

	public static void warn(String string) {
		log.warn(string);
	}
}
