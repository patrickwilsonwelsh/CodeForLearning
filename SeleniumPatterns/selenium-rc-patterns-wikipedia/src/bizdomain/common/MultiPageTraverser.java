package bizdomain.common;


import util.Verifiable;
import util.elements.PageLink;


public class MultiPageTraverser {
	private PageLink tasksTab;
	private PageLink campaignsTab;
	private PageLink leadsTab;
	private PageLink accountsTab;
	private PageLink contactsTab;
	private PageLink opportunitiesTab;

	public void verifyForAllPages(Verifiable rule) throws Exception {
		initializeAllTabs();
		verifyRuleComplianceOnLotsOfPages(rule);
	}

	private void initializeAllTabs() {
//		tasksTab = CommonComponents.mainNavTabsSet.tasks;
//		campaignsTab = CommonComponents.mainNavTabsSet.campaigns;
//		leadsTab = CommonComponents.mainNavTabsSet.leads;
//		accountsTab = CommonComponents.mainNavTabsSet.accounts;
//		contactsTab = CommonComponents.mainNavTabsSet.contacts;
//		opportunitiesTab = CommonComponents.mainNavTabsSet.opportunities;
	}
	
	@SuppressWarnings("unused")
	private void verifyRuleComplianceOnLotsOfPages(Verifiable rule) throws Exception {
//		TasksPage tasksPage = (TasksPage) tasksTab.clickToNewPage();
//		rule.verify();
//		
//		CampaignsPage campaignsPage = (CampaignsPage) campaignsTab.clickToNewPage();
//		rule.verify();
//		
//		LeadsPage leadsPage = (LeadsPage) leadsTab.clickToNewPage();
//		rule.verify();
//		
//		AccountsPage accountsPage = (AccountsPage) accountsTab.clickToNewPage();
//		rule.verify();
//		
//		ContactsPage contactsPage = (ContactsPage) contactsTab.clickToNewPage();
//		rule.verify();
//		
//		OpportunitiesPage opportunitiesPage = (OpportunitiesPage) opportunitiesTab.clickToNewPage();
//		rule.verify();
		
	}
	
}
