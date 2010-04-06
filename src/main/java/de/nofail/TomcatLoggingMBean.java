package de.nofail;

import java.util.List;

/**
 * Managed Bean for log4j stuff.
 * 
 * @author nofail
 */
public interface TomcatLoggingMBean {

	/**
	 * @return all loggers
	 */
	List<String> allLoggers();

	/**
	 * @param name the name of the logger
	 * @return the level of the logger
	 */
	String levelOfLogger(String name);

	/**
	 * @param name the name of the logger
	 * @param level the level to set to
	 */
	void loggerTolevel(String name, String level);

}
