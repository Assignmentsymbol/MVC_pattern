package de.tum.in.ise.view;

import de.tum.in.ise.controller.Controller;
import de.tum.in.ise.model.Course;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourseDetailView extends Stage implements Observer {
    private static final int PADDING = 10;
    private static final int SCENE_HEIGHT = 300;
    private static final int SCENE_WIDTH = 300;
    private static final int GRID_VGAP = 8;
    private static final int GRID_HGAP = 10;
    // GUI Objects
    private final TextField idTextField;
    private final TextField nameTextField;
    // Controller
    private final Controller controller;
    // Model
    private final Course course;

    public CourseDetailView(Controller controller, Course course) {

        this.controller = controller;
        this.course = course;
        this.idTextField = new TextField(course.getId());
        this.nameTextField = new TextField(course.getName());
        course.addObserver(this);


        generateUserInterface();
        // TODO Part 3: Update constructor.
    }

    private void save() {
        course.setId(idTextField.getText());
        course.setName(nameTextField.getText());
        controller.saveCourse(course);

        // TODO Part 3: Implement save() method.
    }

    @Override
    public void update() {
        course.setName(nameTextField.getText());
        course.setId(idTextField.getText());

        setTitle(course.toString());

        // TODO Part 3: Implement update() method.
    }

    private void generateUserInterface() {
        VBox vbox = new VBox();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(PADDING));
        grid.setVgap(GRID_VGAP);
        grid.setHgap(GRID_HGAP);

        Label idLabel = new Label("ID: ");
        GridPane.setConstraints(idLabel, 0, 0);
        GridPane.setConstraints(idTextField, 1, 0);
        Label nameLabel = new Label("Name ");
        GridPane.setConstraints(nameLabel, 0, 1);
        GridPane.setConstraints(nameTextField, 1, 1);

        Button saveButton = new Button("save changes");
        GridPane.setConstraints(saveButton, 0, 2);
        saveButton.setOnAction(event -> save());

        grid.getChildren().addAll(idLabel, nameLabel);
        grid.getChildren().addAll(idTextField, nameTextField);
        grid.getChildren().add(saveButton);
        vbox.getChildren().add(grid);

        Scene scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);
        setScene(scene);
        if (this.course.getName() != null) {
            setTitle(this.course.getId() + " " + this.course.getName());
        } else {
            setTitle("Create a new course");
        }
    }
}
