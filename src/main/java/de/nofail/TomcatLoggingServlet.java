package de.nofail;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.liftweb.textile.TextileParser;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Simple example of a servlet implementation.
 * 
 * @author nofail
 */
@SuppressWarnings("serial")
public class TomcatLoggingServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(TomcatLoggingServlet.class);

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		log.debug("responding to request=" + request);

		String readme = FileUtils.readFileToString(new File("readme.textile"));
		String fragment = "<html><head><title>tomcat-logging</title></head><body>You requested=[%s?%s] <hr> %s</body></html>";
		String html = String.format(fragment, request.getRequestURL(), request.getQueryString(), TextileParser.toHtml(readme));

		response.setContentType("text/html");
		response.getOutputStream().println(html);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
