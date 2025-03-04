package com.company.jmixpmflowbase.integration.ui;


import com.company.jmixpmflowbase.JmixpmFlowBaseApplication;
import com.company.jmixpmflowbase.entity.Project;
import com.company.jmixpmflowbase.entity.Task;
import com.company.jmixpmflowbase.entity.User;
import com.company.jmixpmflowbase.view.main.MainView;
import com.company.jmixpmflowbase.view.task.TaskDetailView;
import com.company.jmixpmflowbase.view.task.TaskListView;
import com.vaadin.flow.component.textfield.TextField;
import io.jmix.core.DataManager;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.EntityPickerComponent;
import io.jmix.flowui.component.UiComponentUtils;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.testassist.FlowuiTestAssistConfiguration;
import io.jmix.flowui.testassist.UiTest;
import io.jmix.flowui.testassist.UiTestUtils;
import io.jmix.flowui.view.View;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@UiTest
@SpringBootTest(classes = {JmixpmFlowBaseApplication.class, FlowuiTestAssistConfiguration.class})
public class TaskUiTest {

    @Autowired
    DataManager dataManager;

    @Autowired
    ViewNavigators viewNavigators;

    @Test
    void test_to_list_navigation() {
        viewNavigators.view(UiTestUtils.getCurrentView(), TaskListView.class)
                .navigate();
        TaskListView currentView = UiTestUtils.getCurrentView();

        DataGrid<Task> taskDataGrid = UiTestUtils.getComponent(currentView, "tasksDataGrid");

        Assertions.assertNotNull(taskDataGrid);
    }

    @Test
    void givenNoData_whenSaveClocked_thenNoBackToList() {

        viewNavigators.detailView(UiTestUtils.getCurrentView(), Task.class)
                .newEntity().navigate();

        View currentView;

        currentView = UiTestUtils.getCurrentView();

        JmixButton saveButton = UiTestUtils.getComponent(currentView, "saveAndCloseBtn");

        currentView = UiTestUtils.getCurrentView();

        Assertions.assertInstanceOf(TaskDetailView.class, currentView);
    }

    @Test
    void givenAllNeeded_whenSaveClicked_thenBackToListNavigated(){
        //expected that we go to new task creation from main view
        viewNavigators.detailView(UiComponentUtils.getCurrentView(), Task.class).newEntity().navigate();

        View currentView;

        currentView = UiTestUtils.getCurrentView();

        User userToAssign = dataManager.load(User.class).all().one();
        Project project = dataManager.load(Project.class).all().one();
        String name = "test-task";

        EntityPickerComponent<User> userPicker = UiTestUtils.getComponent(currentView, "assigneeField");
        userPicker.setValueFromClient(userToAssign);

        EntityPickerComponent<Project> projectPicker = UiTestUtils.getComponent(currentView, "projectField");
        projectPicker.setValueFromClient(project);

        TextField nameField = UiTestUtils.getComponent(currentView, "nameField");
        nameField.setValue(name);

        JmixButton saveBtn =  UiTestUtils.getComponent(currentView, "saveAndCloseBtn");
        saveBtn.click();

        currentView = UiTestUtils.getCurrentView();

        //after valid inputs we navigated to main view, in this case
        Assertions.assertInstanceOf(MainView.class, currentView);
    }

    @AfterEach
    void tearDown() {
        dataManager.load(Task.class)
                .query("e.name like ?1", "test-task")
                .list()
                .forEach(t -> dataManager.remove(t));
    }
}
