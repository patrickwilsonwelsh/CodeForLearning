package performance.pageload;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import util.browserdriver.BrowserDriver;
import bizdomain.common.CommonComponents;
import bizdomain.pages.DashBoard;
import bizdomain.pages.LoginPage;

public class AverageLoadTimeTest {
	private static final int TOTAL_NUMBER_OF_LOGINS = 5;
	private static final int MINIMUM_ACCEPTABLE_LOAD_TIME_IN_SECONDS = 5;
	private static final int MILLIS_PER_SECOND = 1000;
	
	private LoginPage loginPage;
	private List<Integer> loadTimes;
	
	@Before
	public void baseSetupMethod() throws Exception {
		BrowserDriver.open(DashBoard.HOME_PAGE_URL);
		loadTimes = new ArrayList<Integer>();
	}
	
	@Test
	public void averageLogin_AndHomePageLoadTime_IsUnderAcceptableLimit() throws Exception  {
		for(int i=0; i<TOTAL_NUMBER_OF_LOGINS; i++) {
			addTimedLoginThenLogout();
		}
		
		assertTrue(calculateAverageLoadTime() < MINIMUM_ACCEPTABLE_LOAD_TIME_IN_SECONDS);
	}
	
	
	private void addTimedLoginThenLogout() throws Exception {
		long startTime = System.currentTimeMillis();
		long endTime = timeLoginAndHomePageLoad();
		addLoadTime(startTime, endTime);
		CommonComponents.topRightLinks.logout.click();
	}

	private void addLoadTime(long startTime, long endTime) {
		long loadDurationInMs = endTime - startTime;
		int loadTimeInt = (int) (loadDurationInMs / MILLIS_PER_SECOND);
		loadTimes.add(loadTimeInt);
	}

	private long timeLoginAndHomePageLoad() {
		loginPage = new LoginPage();
		loginPage.login(LoginPage.USERNAME, LoginPage.PASSWORD);
		long endTime = System.currentTimeMillis();
		return endTime;
	}

	private int calculateAverageLoadTime() {
		Integer totalLoadTimes = 0;
		
		for (Iterator<Integer> iterator = loadTimes.iterator(); iterator.hasNext();) {
			totalLoadTimes = totalLoadTimes +  iterator.next();
		}
		
		return totalLoadTimes / loadTimes.size();
	}

	
	@AfterClass
	public static void baseTearDownSuite() throws Exception {
		BrowserDriver.stopEverything();
	}	

}
