package bizdomain.pages;

import util.BasePage;
import util.elements.DhtmlLink;
import util.elements.TextLabel;
import bizdomain.panes.CreateTaskPane;

public class TasksPage extends BasePage {
	private static final String PAGE_IS_LOADED_CSS = "div:contains('Tasks')";
	public DhtmlLink<CreateTaskPane> createTaskLink;
	public TextLabel todayLabel;
	public TextLabel noPendingTasksLabel;
	
	public TasksPage() {
		super();
		createTaskLink = new DhtmlLink<CreateTaskPane>("div[class=title_tools] a[href=#]", CreateTaskPane.class);
		todayLabel = new TextLabel(PAGE_IS_LOADED_CSS + " div[id=list_due_today]");
		noPendingTasksLabel = new TextLabel("div[id=empty]");
	}

	@Override
	public String getPageLoadedCssLocator() {
		return PAGE_IS_LOADED_CSS;
	}

}
