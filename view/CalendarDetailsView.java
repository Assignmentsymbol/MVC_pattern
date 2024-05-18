package de.tum.in.ise.view;

import de.tum.in.ise.controller.Controller;
import de.tum.in.ise.model.Course;
import de.tum.in.ise.model.Entry;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalendarDetailsView extends Stage implements Observer {
    private static final int PADDING = 10;
    private static final int SCENE_HEIGHT = 300;
    private static final int SCENE_WIDTH = 300;
    private static final int GRID_VGAP = 8;
    private static final int GRID_HGAP = 10;
    // GUI Objects
    private final TextField idTextField;
    private final TextField nameTextField;
    private final TextField dateTextField;
    // Controller
    private final Controller controller;
    // Model
    private final Entry entry;

    public CalendarDetailsView(Controller controller, Entry entry) {
        //TODO Part 3: Update constructor.
        this.controller = controller;
        this.entry = entry;

        entry.addObserver(this);
        Course course = entry.getCourse();
        if (course != null) {
            course.addObserver(this);
        }

        this.idTextField = new TextField(course == null ? "" : course.getId());
        this.nameTextField = new TextField(course == null ? "" : course.getName());
        this.dateTextField = new TextField(entry.getDate());

        generateUserInterface();
        //Note: forgot what happened here...
    }

    private void save() {
        if(entry.getCourse()!=null){
            entry.setDate(dateTextField.getText());
            entry.getCourse().setName(nameTextField.getText());
            entry.getCourse().setId(idTextField.getText());
            controller.saveCalendarEntry(entry);
            return;
        }

            entry.setCourse(new Course(idTextField.getText(),nameTextField.getText()));
            if(dateTextField.getText()==null){
                dateTextField.setText("");
            }
            entry.setDate(dateTextField.getText());
            controller.saveCalendarEntry(entry);


        // TODO Part 3: Implement save() method.
    }

    @Override
    public void update() {
        dateTextField.setText(entry.getDate());
        Course course = entry.getCourse();
        if (course != null) {
            idTextField.setText(course.getId());
            nameTextField.setText(course.getName());
        }
        //Note: what will happen if we don't update the attribute dateTextField?
        //      (outcode the statements above)seems the GUI still update automatically.


        setTitle(entry.toString());


        // TODO Part 3: Implement update() method.
    }

    private void generateUserInterface() {
        VBox vbox = new VBox();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(PADDING));
        grid.setVgap(GRID_VGAP);
        grid.setHgap(GRID_HGAP);

        Label idLabel = new Label("Course ID: ");
        GridPane.setConstraints(idLabel, 0, 0);
        GridPane.setConstraints(idTextField, 1, 0);
        Label nameLabel = new Label("Course Name: ");
        GridPane.setConstraints(nameLabel, 0, 1);
        GridPane.setConstraints(nameTextField, 1, 1);
        Label dateLabel = new Label("Entry Date: ");
        GridPane.setConstraints(dateLabel, 0, 2);
        GridPane.setConstraints(dateTextField, 1, 2);

        Button saveButton = new Button("Save");
        GridPane.setConstraints(saveButton, 0, 3);
        saveButton.setOnAction(event -> save());

        grid.getChildren().addAll(idLabel, nameLabel, dateLabel);
        grid.getChildren().addAll(idTextField, nameTextField, dateTextField);
        grid.getChildren().add(saveButton);
        vbox.getChildren().add(grid);

        Scene scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);
        setScene(scene);
        Course course = this.entry.getCourse();
        if (course != null) {
            setTitle(course.getId() + " " + course.getName() + " Entry");
        } else {
            setTitle("Create a new calendar entry");
        }
    }
}
