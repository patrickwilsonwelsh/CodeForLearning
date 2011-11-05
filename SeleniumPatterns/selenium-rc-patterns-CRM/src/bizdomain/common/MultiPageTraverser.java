package bizdomain.common;


import util.Verifiable;
import util.elements.PageLink;
import bizdomain.pages.AccountsPage;
import bizdomain.pages.CampaignsPage;
import bizdomain.pages.ContactsPage;
import bizdomain.pages.LeadsPage;
import bizdomain.pages.OpportunitiesPage;
import bizdomain.pages.TasksPage;


public class MultiPageTraverser {
	private PageLink<TasksPage> tasksTab;
	private PageLink<CampaignsPage> campaignsTab;
	private PageLink<LeadsPage> leadsTab;
	private PageLink<AccountsPage> accountsTab;
	private PageLink<ContactsPage> contactsTab;
	private PageLink<OpportunitiesPage> opportunitiesTab;

	public void verifyForAllPages(Verifiable rule) throws Exception {
		initializeAllTabs();
		verifyRuleComplianceOnLotsOfPages(rule);
	}

	private void initializeAllTabs() {
		tasksTab = CommonComponents.mainNavTabsSet.tasks;
		campaignsTab = CommonComponents.mainNavTabsSet.campaigns;
		leadsTab = CommonComponents.mainNavTabsSet.leads;
		accountsTab = CommonComponents.mainNavTabsSet.accounts;
		contactsTab = CommonComponents.mainNavTabsSet.contacts;
		opportunitiesTab = CommonComponents.mainNavTabsSet.opportunities;
	}
	
	@SuppressWarnings("unused")
	private void verifyRuleComplianceOnLotsOfPages(Verifiable rule) throws Exception {
		TasksPage tasksPage = tasksTab.clickToNewPage();
		rule.verify();
		
		CampaignsPage campaignsPage = campaignsTab.clickToNewPage();
		rule.verify();
		
		LeadsPage leadsPage = leadsTab.clickToNewPage();
		rule.verify();
		
		AccountsPage accountsPage = accountsTab.clickToNewPage();
		rule.verify();
		
		ContactsPage contactsPage = contactsTab.clickToNewPage();
		rule.verify();
		
		OpportunitiesPage opportunitiesPage = opportunitiesTab.clickToNewPage();
		rule.verify();
		
	}
	
}
