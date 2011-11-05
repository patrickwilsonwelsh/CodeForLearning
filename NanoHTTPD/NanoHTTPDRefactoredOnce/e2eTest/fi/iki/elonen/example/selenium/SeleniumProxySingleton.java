package fi.iki.elonen.example.selenium;

import java.io.IOException;
import java.net.Socket;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

public abstract class SeleniumProxySingleton {
	private static SeleniumServer jettyProxyInstance;

	protected static final String SELENIUM_SERVER_HOST = "localhost";
	private static boolean jettyProxyWasStartedByATest = false;

	private SeleniumProxySingleton() {
		throw new RuntimeException("Don't instantiate me");
	}

	public static void makeSureWeHaveAJettyProxyRunning(int seleniumServerPort) throws Exception {
		jettyProxyInstance = getInstance(seleniumServerPort);
	}

	private static SeleniumServer getInstance(int seleniumServerPort) throws Exception, IOException {
		Socket socket = null;

		try {
			socket = new Socket(SELENIUM_SERVER_HOST, seleniumServerPort);

		} catch (Exception e) {
			System.out
					.println("Nothing is listening on port " + seleniumServerPort);
			System.out.println("Launching SeleniumServer on port "
					+ seleniumServerPort);
			jettyProxyInstance = startJettyProxy(seleniumServerPort);
			jettyProxyWasStartedByATest = true;
		}

		finally {
			if (socket != null)
				socket.close();
		}

		return jettyProxyInstance;

	}

	protected static SeleniumServer startJettyProxy(int seleniumServerPort) throws Exception {
		RemoteControlConfiguration config = new RemoteControlConfiguration();
		config.setPort(seleniumServerPort);
		jettyProxyInstance = new SeleniumServer(config);
		jettyProxyInstance.start();

		return jettyProxyInstance;
	}

	public static void stopJettyProxy() {
		if (jettyProxyWasStartedByATest) {
			jettyProxyInstance.stop();
		}
	}

}
