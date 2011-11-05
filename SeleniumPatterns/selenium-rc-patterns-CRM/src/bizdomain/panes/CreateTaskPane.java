package bizdomain.panes;

import util.BasePane;
import util.elements.DhtmlLink;
import util.elements.SelectField;
import util.elements.TextField;

public class CreateTaskPane extends BasePane{
	public static final String PANE_IS_LOADED_CSS = "input[id=task_name]";
	public TextField nameField;
	public SelectField dueSelector;
	public SelectField categorySelector;
	public DhtmlLink<DueTodayTaskStrip> createButton;
	
	public CreateTaskPane() throws Exception {
		super();
		nameField = new TextField("input[id=task_name]");
		dueSelector = new SelectField("select[id=task_bucket]");
		categorySelector = new SelectField("select[id=task_category]");
		createButton = new DhtmlLink<DueTodayTaskStrip>("input[id=task_submit]", DueTodayTaskStrip.class);
	}
	
	public DueTodayTaskStrip createTask(String dueSelectorEntry, String categorySelectorEntry, String taskName) {
		nameField.enter(taskName);
		dueSelector.select(dueSelectorEntry);
		categorySelector.select(categorySelectorEntry);
		return (DueTodayTaskStrip) createButton.clickToNewContainer();
	}
	

	
	@Override
	public String getPageLoadedCssLocator() {
		return PANE_IS_LOADED_CSS;
	}

}
