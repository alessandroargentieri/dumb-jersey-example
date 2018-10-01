package com.quicktutorialz.jax;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;

public class EmbeddedJetty {

    private int serverPort = 8080;
    private String packageName = "";

    public EmbeddedJetty setServerPort(int serverPort){
        this.serverPort = serverPort;
        return this;
    }

    public EmbeddedJetty setPackageToScan(String packageName){
        this.packageName = packageName;
        return this;
    }

    public EmbeddedJetty start() throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(this.serverPort);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES, this.packageName);
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
        return this;
    }
}
