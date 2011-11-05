package quickfind;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bizdomain.common.BaseWebTest;
import bizdomain.common.CommonComponents;
import bizdomain.pages.LordAccountPage;
import bizdomain.panes.QuickFindBox;

public class FindAccountTest extends BaseWebTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void canFindExistingAccount() throws Exception {
		QuickFindBox<LordAccountPage> quickFind = CommonComponents.topRightLinks.quickFind.clickToNewContainer();
		
		LordAccountPage smithamAndSonsPage = quickFind.searchForAccount("Lord", LordAccountPage.class);
		assertTrue(smithamAndSonsPage.title.reads(LordAccountPage.TITLE));
	}
}
