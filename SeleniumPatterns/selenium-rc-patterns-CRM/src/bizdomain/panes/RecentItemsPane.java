package bizdomain.panes;

import util.BasePane;

public class RecentItemsPane extends BasePane {
	private static final String PAGE_IS_LOADED_CSS = "div[id=recently][class=panel]";
	
//	public TextLabel account;
//	public TextLabel campaign;
//	public TextLabel contact;
//	public TextLabel opportunity;
//	public TextLabel lead;
//	
//	
//	public RecentItemsPane() {
//		super();
//		account = new TextLabel();
//		campaign = new TextLabel();
//		contact = new TextLabel();
//		opportunity = new TextLabel();
//		lead = new TextLabel();
//	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

}
