package de.nofail;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TomcatLogging implements TomcatLoggingMBean {

	private static final Logger log = Logger.getLogger(TomcatLogging.class);

	public List<String> appenders() {
		List<String> appenderNames = new ArrayList<String>();
		Enumeration<?> appenders = Logger.getRootLogger().getAllAppenders();
		while (appenders.hasMoreElements()) {
			Appender appender = (Appender) appenders.nextElement();
			appenderNames.add(appender.getName());
		}
		log.debug("appenders=" + appenderNames);
		return appenderNames;
	}

	public List<String> loggers() {
		List<String> loggerNames = new ArrayList<String>();
		Enumeration<?> loggers = Logger.getRootLogger().getLoggerRepository().getCurrentLoggers();
		while (loggers.hasMoreElements()) {
			Logger appender = (Logger) loggers.nextElement();
			loggerNames.add(appender.getName());
		}
		log.debug("loggers=" + loggerNames);
		return loggerNames;
	}

	public String loggerLevel(String name) {
		Logger logger = LogManager.exists(name);
		if (logger == null) {
			return "Could not find logger with name=" + name;
		} else {
			return logger.getLevel().toString();
		}
	}

	public void loggerTolevel(String name, String level) {
		Logger logger = LogManager.exists(name);
		if (logger == null) {
			log.warn("Could not find logger with name=" + name);
		} else {
			Level l = Level.toLevel(level);
			logger.setLevel(l);
			logger.log(l, String.format("Set logger=%s to level=%s", name, level));
		}
	}
}
