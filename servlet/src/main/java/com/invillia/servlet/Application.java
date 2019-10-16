package com.invillia.servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Application {

    public static void main(String[] args) throws Exception {
        final Server server = new Server(8080);

        final ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setContextPath("/");

        servletContextHandler.addServlet(HelloServlet.class, "/");
        servletContextHandler.addServlet(ContaServlet.class, "/conta");
        servletContextHandler.addServlet(StatusServlet.class, "/status");

        final FilterHolder filterHolder = new FilterHolder(new DefaultFilter());
        servletContextHandler.addFilter(filterHolder, "/*", null);

        server.setHandler(servletContextHandler);

        server.start();
    }

}
