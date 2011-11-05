package bizdomain.rule.persistence;

import static org.junit.Assert.assertTrue;
import util.Verifiable;
import bizdomain.common.CommonComponents;
import bizdomain.panes.TopRightLoggedInLinksSet;


public class TopRightLoggedInLinksSetRule implements Verifiable {
	private TopRightLoggedInLinksSet topRightLinksSet;
	
	public void verify() throws Exception {
		verifyNavBoxIsPresent();
	}

	@SuppressWarnings("static-access")
	private void verifyNavBoxIsPresent() throws Exception {
		if (null == topRightLinksSet) topRightLinksSet = CommonComponents.getInstance().topRightLinks;
		assertTrue(topRightLinksSet.isCompletelyLoaded());
	}
	


}
