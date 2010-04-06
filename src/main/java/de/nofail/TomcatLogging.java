package de.nofail;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Implementation of {@link TomcatLoggingMBean}.
 * 
 * @author nofail
 */
public class TomcatLogging implements TomcatLoggingMBean {

	private static final Logger log = Logger.getLogger(TomcatLogging.class);

	@Override
	public List<String> allLoggers() {
		List<String> loggerNames = new ArrayList<String>();
		Enumeration<?> loggers = Logger.getRootLogger().getLoggerRepository().getCurrentLoggers();
		while (loggers.hasMoreElements()) {
			Logger appender = (Logger) loggers.nextElement();
			loggerNames.add(appender.getName());
		}
		log.debug("loggers=" + loggerNames);
		return loggerNames;
	}

	@Override
	public String levelOfLogger(final String name) {
		Logger logger = LogManager.exists(name);
		if (logger == null) {
			return "Could not find logger with name=" + name;
		} else {
			return logger.getLevel().toString();
		}
	}

	@Override
	public void loggerTolevel(final String name, final String level) {
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
