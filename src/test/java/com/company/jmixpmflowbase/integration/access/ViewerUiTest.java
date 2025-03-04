package com.company.jmixpmflowbase.integration.access;


import com.company.jmixpmflowbase.JmixpmFlowBaseApplication;
import com.company.jmixpmflowbase.test_support.AuthenticatedAsTaskViewer;
import com.company.jmixpmflowbase.view.task.TaskListView;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.UiComponentUtils;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.testassist.FlowuiTestAssistConfiguration;
import io.jmix.flowui.testassist.UiTest;
import io.jmix.flowui.testassist.UiTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@UiTest(authenticator = AuthenticatedAsTaskViewer.class)
@SpringBootTest(classes = {JmixpmFlowBaseApplication.class, FlowuiTestAssistConfiguration.class})
public class ViewerUiTest {

    @Autowired
    ViewNavigators viewNavigators;

    @Test
    void create_button_unavailable(){
        viewNavigators.view(UiComponentUtils.getCurrentView(), TaskListView.class).navigate();
        TaskListView currentView = UiTestUtils.getCurrentView();

        JmixButton createBtn = UiTestUtils.getComponent(currentView, "createBtn");

        Assertions.assertFalse(createBtn.isEnabled());
    }

    @Test
    void edit_button_changed(){
        viewNavigators.view(UiComponentUtils.getCurrentView(), TaskListView.class).navigate();
        TaskListView currentView = UiTestUtils.getCurrentView();

        JmixButton readBtn = UiTestUtils.getComponent(currentView, "editBtn");
        String expectedCaption = "Read";
        Assertions.assertEquals(expectedCaption, readBtn.getText());

    }
}
