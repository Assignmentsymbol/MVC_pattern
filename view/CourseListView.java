package de.tum.in.ise.view;

import de.tum.in.ise.controller.Controller;
import de.tum.in.ise.model.Course;
import de.tum.in.ise.model.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CourseListView extends Stage implements Observer {
    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 400;
    // GUI Objects
    private final ListView<Course> courseListView;
    private CalendarView calendarView;
    // Controller
    private Controller controller;
    // Model
    private final ObservableList<Course> courses;

    public CourseListView(Controller controller, List<Course> courseList, List<Entry> entries) {
        // TODO Part 3: Update constructor.
        this.controller = controller;
        this.courses = FXCollections.observableArrayList(courseList);
        this.courseListView = new ListView<>(courses);
        for(Course course:courseList){
            course.addObserver(this);
        }
        generateUserInterface(entries);
        this.controller.setCourseListView(this);
    }

    private void selectCourse(Course course) {
        if(course!=null&&controller!=null)
        controller.selectCourse(course);
        // TODO Part 3: Implement method selectCourse(...).
    }

    private void createCourse() {
        controller.selectCourse(new Course());
        // TODO Part 3: Implement method createCourse().
    }

    public void addCourse(Course course) {
        if(courses.contains(course))
            return;

        courses.add(course);
        course.addObserver(this);
        // TODO Part 2: Implement method addCourse(...).
    }

    @Override
    public void update() {
        displayCourses();
    }

    private void displayCourses() {
        courseListView.refresh();
    }

    private void generateUserInterface(List<Entry> entries) {
        VBox vbox = new VBox();

        Button createButton = new Button("Create a course");
        createButton.setOnAction(event -> createCourse());

        Button personalCalendar = new Button("Personal Calendar");
        personalCalendar.setOnAction(event -> {
            if (calendarView == null) {
                new CalendarView(this, controller, entries).show();
                close();
            } else {
                calendarView.show();
                close();
            }
        });

        Label courseListLabel = new Label("Course List");
        courseListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        courseListView.setOnMouseClicked(event -> selectCourse(courseListView.getSelectionModel().getSelectedItem()));
        vbox.getChildren().addAll(courseListLabel, courseListView, createButton, personalCalendar);

        Scene scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);
        setScene(scene);
        setTitle("Course List");
        displayCourses();
    }

    public void setCalendarView(CalendarView calendarView) {
        this.calendarView = calendarView;
    }
}
