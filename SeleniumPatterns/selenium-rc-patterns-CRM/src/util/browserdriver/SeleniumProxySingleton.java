package util.browserdriver;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.Socket;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

public abstract class SeleniumProxySingleton {
	private static SeleniumServer jettyProxyInstance;
	
    protected static final String SELENIUM_SERVER_HOST = "localhost";
    protected static final int SELENIUM_SERVER_PORT = 4444;
    private static boolean jettyProxyWasStartedByATest = false;
    
    private SeleniumProxySingleton() {
    	throw new RuntimeException("Don't instantiate me");
    }

    public static void makeSureWeHaveAJettyProxyRunning() throws Exception {
        jettyProxyInstance = getInstance();
    }

	private static SeleniumServer getInstance() throws Exception, IOException {
		Socket socket = null;

        try {
            socket = checkWhetherPortIsInUse();
        } catch(Exception e) {
            launchJettyProxy();
        }

        finally {
            if (socket != null) socket.close();
        }
        
        return jettyProxyInstance;
        
	}

	private static void launchJettyProxy() throws Exception {
		System.out.println("Nothing is listening on port " + SELENIUM_SERVER_PORT);
		System.out.println("Launching SeleniumServer on port " + SELENIUM_SERVER_PORT);
		jettyProxyInstance = startJettyProxy();
		jettyProxyWasStartedByATest = true;
	}

	private static Socket checkWhetherPortIsInUse() throws Exception {
		Socket socket;
		socket = new Socket(SELENIUM_SERVER_HOST, SELENIUM_SERVER_PORT);
		fail("Port " + SELENIUM_SERVER_PORT + " is in use and cannot be used by SeleniumProxySingleton; please use another port.");
		return socket;
	}

    protected static SeleniumServer startJettyProxy() throws Exception {
        RemoteControlConfiguration config = new RemoteControlConfiguration();
        config.setPort(SELENIUM_SERVER_PORT);
        config.setEnsureCleanSession(true);
        jettyProxyInstance = new SeleniumServer(config);
        jettyProxyInstance.start();
        
        return jettyProxyInstance;
    }


    public static void stopJettyProxy() {
        if(jettyProxyWasStartedByATest) {
            jettyProxyInstance.stop();
        }
    }

}

