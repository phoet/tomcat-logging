package de.nofail;

import java.util.List;

public interface TomcatLoggingMBean {

	public List<String> appenders();

	public List<String> loggers();

	public String loggerLevel(String name);

	public void loggerTolevel(String name, String level);

}
