package bizdomain.panes;

import util.BasePane;
import util.elements.PageLink;
import bizdomain.pages.AccountsPage;
import bizdomain.pages.CampaignsPage;
import bizdomain.pages.ContactsPage;
import bizdomain.pages.DashBoard;
import bizdomain.pages.LeadsPage;
import bizdomain.pages.OpportunitiesPage;
import bizdomain.pages.TasksPage;


public class MainNavigationTabsSet  extends BasePane {
	public static final String PANE_IS_LOADED_CSS = "div[id=tabs]";
	
	public PageLink<DashBoard> dashBoard;
	public PageLink<TasksPage> tasks;
	public PageLink<CampaignsPage> campaigns;
	public PageLink<LeadsPage> leads;
	public PageLink<AccountsPage> accounts;
	public PageLink<ContactsPage> contacts;
	public PageLink<OpportunitiesPage> opportunities;

	public MainNavigationTabsSet() { 
		dashBoard = new PageLink<DashBoard>(PANE_IS_LOADED_CSS + " a:contains('Dashboard')", DashBoard.class);
		tasks = new PageLink<TasksPage>(PANE_IS_LOADED_CSS + " a:contains('Tasks')", TasksPage.class);
		campaigns = new PageLink<CampaignsPage>(PANE_IS_LOADED_CSS + " a:contains('Campaigns')", CampaignsPage.class);
		leads = new PageLink<LeadsPage>(PANE_IS_LOADED_CSS + " a:contains('Leads')", LeadsPage.class);
		accounts = new PageLink<AccountsPage>(PANE_IS_LOADED_CSS + " a:contains('Accounts')", AccountsPage.class);
		contacts = new PageLink<ContactsPage>(PANE_IS_LOADED_CSS + " a:contains('Contacts')", ContactsPage.class);
		opportunities = new PageLink<OpportunitiesPage>(PANE_IS_LOADED_CSS + " a:contains('Opportunities')", OpportunitiesPage.class);
	}


	@Override
	public String getPageLoadedCssLocator() {
		return PANE_IS_LOADED_CSS;
	}
}
