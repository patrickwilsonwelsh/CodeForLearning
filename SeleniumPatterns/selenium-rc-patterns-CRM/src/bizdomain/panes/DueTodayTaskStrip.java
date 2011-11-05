package bizdomain.panes;

import util.BasePane;
import util.browserdriver.BrowserDriver;
import util.elements.ClickableElement;
import util.elements.TextLabel;

public class DueTodayTaskStrip extends BasePane {
	public static final String PANE_IS_LOADED_CSS = "div[id=list_due_today] div[class=bucket][id=due_today]";
	public TextLabel whenLabel;
	public ClickableElement completeTaskCheckBox;
	public TextLabel taskNameLabel;
	
	public DueTodayTaskStrip() throws Exception {
		super();
		whenLabel = new TextLabel(PANE_IS_LOADED_CSS + " div[class='lunch strip']:contains('Lunch')");
		completeTaskCheckBox = new ClickableElement(PANE_IS_LOADED_CSS + " input[id^='complete_task_']");
		taskNameLabel = new TextLabel(PANE_IS_LOADED_CSS + " div[class=indentwide] label");
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PANE_IS_LOADED_CSS;
	}

	public void clickCompleteBox() throws Exception {
		completeTaskCheckBox.click();
		BrowserDriver.waitForElement_NOT_Visible(PANE_IS_LOADED_CSS);
	}

}
