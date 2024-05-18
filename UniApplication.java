package de.tum.in.ise;

import de.tum.in.ise.controller.Controller;
import de.tum.in.ise.model.Course;
import de.tum.in.ise.model.Entry;
import de.tum.in.ise.view.CourseListView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public final class UniApplication extends Application {

    /**
     * This method is setting up everything for the local testing.
     */
    @Override
    public void start(Stage primaryStage) {
        List<Course> courses = new ArrayList<>();
        Course designPatterns = new Course("IN2081", "Design Patterns");
        courses.add(designPatterns);
        Course ise = new Course("IN0006", "ISE");
        courses.add(ise);
        Course interactiveLearning = new Course("IN0012", "Interactive Learning");
        courses.add(interactiveLearning);
        Course agileProjectManagement = new Course("IN0014", "Agile Project Management");
        courses.add(agileProjectManagement);

        Entry designPatternEntry = new Entry(designPatterns, "Friday | 13:00-14:30");
        Entry eistEntry = new Entry(ise, "Monday | 14:00-16:00");
        Entry interactiveLearningEntry = new Entry(interactiveLearning, "Thursday | 10:00-12:00");
        Entry agileProjectManagementEntry = new Entry(agileProjectManagement, "Wednesday | 09:00-11:30");

        Controller controller = new Controller();

        CourseListView courseListView = new CourseListView(controller, courses, List.of(designPatternEntry, eistEntry,
                                                                                        interactiveLearningEntry,
                                                                                        agileProjectManagementEntry));
        courseListView.show();
    }

}
