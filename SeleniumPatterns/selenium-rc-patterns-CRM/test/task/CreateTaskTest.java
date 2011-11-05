package task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import bizdomain.common.BaseWebTest;
import bizdomain.common.CommonComponents;
import bizdomain.pages.TasksPage;
import bizdomain.panes.CreateTaskPane;
import bizdomain.panes.DueTodayTaskStrip;

public class CreateTaskTest extends BaseWebTest {
	private TasksPage tasksPage;
	private String taskName;
	
	@Before
	public void setup() {
		tasksPage = CommonComponents.mainNavTabsSet.tasks.clickToNewPage();
		taskName = createRandomTaskName();
	}

	@Test
	public void canCreateAndCompleteLunchTodayTask() throws Exception {
		assertTrue(tasksPage.noPendingTasksLabel.isVisible());
		CreateTaskPane createTaskPane = tasksPage.createTaskLink.clickToNewContainer();

		DueTodayTaskStrip taskStrip = createTaskPane.createTask("Today", "Lunch", taskName);
		assertFalse(tasksPage.noPendingTasksLabel.isVisible());
		
		String expectedCategory = "Lunch";
		assertTrue(taskStrip.taskNameLabel.getText().contains(taskName));
		assertEquals(taskStrip.whenLabel.getText(), expectedCategory);
		
		taskStrip.clickCompleteBox();
		assertFalse(taskStrip.isVisible());
	}
	
	
	private String createRandomTaskName() {
		Random intGenerator = new Random(System.currentTimeMillis());
		int randomInt = intGenerator.nextInt();
		return "testTask" + randomInt;
	}

}
