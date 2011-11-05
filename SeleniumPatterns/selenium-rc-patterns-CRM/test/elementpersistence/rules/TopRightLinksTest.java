package elementpersistence.rules;

import org.junit.Test;

import bizdomain.common.BaseWebTest;
import bizdomain.common.MultiPageTraverser;
import bizdomain.rule.persistence.TopRightLoggedInLinksSetRule;

public class TopRightLinksTest extends BaseWebTest {
	
	@Test
	public void topRightNavLinks_ArePresentOnAllPages() throws Exception {
		MultiPageTraverser traverser = new MultiPageTraverser();
		traverser.verifyForAllPages(new TopRightLoggedInLinksSetRule());
	}
}
