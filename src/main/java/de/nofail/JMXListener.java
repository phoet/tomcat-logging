package de.nofail;

import java.lang.management.ManagementFactory;

import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener for JMX management.
 * 
 * @author nofail
 */
public class JMXListener implements ServletContextListener {

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		try {
			TomcatLogging mbean = new TomcatLogging();
			ObjectName clutter = new ObjectName("de.nofail:type=TomcatLogging");
			ManagementFactory.getPlatformMBeanServer().registerMBean(mbean, clutter);
		} catch (Exception e) {
			throw new IllegalStateException("Could not create JMX context: " + e.getMessage(), e);
		}
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		// noop
	}
}
