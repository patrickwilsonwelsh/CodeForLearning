package com.qait.gold.testframework.selenium.util;

import org.openqa.selenium.server.SeleniumServer;
import org.openqa.selenium.server.RemoteControlConfiguration;
import java.net.Socket;

public abstract class SeleniumProxySingleton {
    protected static final String SELENIUM_SERVER_HOST = "" + "";
    protected static final int SELENIUM_SERVER_PORT = 4432;

    protected static SeleniumServer jettyProxy;
    protected static boolean jettyProxyWasStartedByATest = false;

    public static void makeSureWeHaveAJettyProxyRunning() throws Exception {
        Socket socket = null;
        try {
            socket = new Socket(SELENIUM_SERVER_HOST, SELENIUM_SERVER_PORT);
        } catch(Exception e) {
            System.out.println("Nothing is running on port " + SELENIUM_SERVER_PORT);
            System.out.println("Launching SeleniumServer on port " + SELENIUM_SERVER_PORT);
            startJettyProxy();

            jettyProxyWasStartedByATest = true;
        }
        finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    protected static void startJettyProxy() throws Exception {
        RemoteControlConfiguration config = new RemoteControlConfiguration();
        config.setPort(SELENIUM_SERVER_PORT);
        jettyProxy = new SeleniumServer(config);
        jettyProxy.start();
    }

    public static void stopJettyProxy() {
        if(jettyProxyWasStartedByATest) {
            jettyProxy.stop();
        }
    }
}
