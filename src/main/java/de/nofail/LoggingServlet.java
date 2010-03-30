package de.nofail;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author nofail
 */
public class LoggingServlet extends HttpServlet{

	private static final Logger log = Logger.getLogger(LoggingServlet.class);
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String html = String.format("<html><head><title>logging-servlet</title></head><body>You requested=[%s?%s]</body></html>",  request.getRequestURL(), request.getQueryString());
		log.debug(html);
		response.getOutputStream().println(html);
	}

}
