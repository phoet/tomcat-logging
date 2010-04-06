package de.nofail;

import java.io.File;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Context listener for initializing the log4j watchdog.
 * <p>
 * This implementation depends on defaults:
 * <ul>
 * <li>a log4j configuration file with the name SERVLET_CONTEXT-log4j.xml must
 * exist
 * <li>the configuration file must be in the root folder of the classpath
 * <li>the watchdog-thread looks for changes to the file every second
 * </ul>
 * <p>
 * Configuration points are possible by adding servlet init-params.
 * 
 * @author nofail
 */
public class TomcatLoggingListener implements ServletContextListener {

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		System.out.println("Initializing the servet context!");
		ServletContext servletContext = sce.getServletContext();
		try {
			String log4jFile = String.format("%s-log4j.xml", servletContext.getContextPath());
			System.out.println("Trying to read log4jFile=" + log4jFile);
			String configFilename = new File(getClass().getResource(log4jFile).toURI()).getAbsolutePath();
			long delay = 1000;
			System.out.println(String.format("Configuring watchdog on log4jFile=%s with delay=%s", configFilename, delay));
			DOMConfigurator.configureAndWatch(configFilename, delay);
		} catch (URISyntaxException e) {
			throw new IllegalStateException("Error resolving log4j configuration file for context=" + servletContext, e);
		}
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		System.out.println("Destroying the servet context!");
		// this should not be necessary
		Logger.getRootLogger().getLoggerRepository().shutdown();
	}
}
