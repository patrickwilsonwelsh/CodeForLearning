package bizdomain.common;

import bizdomain.panes.MainNavigationTabsSet;
import bizdomain.panes.TopRightLoggedInLinksSet;

public class CommonComponents {
	private static CommonComponents instance = null;

	public static TopRightLoggedInLinksSet topRightLinks;
	public static MainNavigationTabsSet mainNavTabsSet;
	
	private CommonComponents()  {
		topRightLinks = new TopRightLoggedInLinksSet();
		mainNavTabsSet = new MainNavigationTabsSet();
	}
	
	public static CommonComponents getInstance() {
		if (instance == null) instance = new CommonComponents();
		return instance;
	}
	
}
