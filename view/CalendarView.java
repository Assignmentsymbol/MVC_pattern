package de.tum.in.ise.view;

import de.tum.in.ise.controller.Controller;
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


public class CalendarView extends Stage implements Observer {
    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 400;
    // GUI Objects
    private ListView<Entry> calendarListView;
    // Controller
    private Controller controller;
    private final ObservableList<Entry> calendarEntries;

    public CalendarView(CourseListView courseListView, Controller controller, List<Entry> entries) {
        for(Entry entry:entries){
            entry.addObserver(this);
        }

        //TODO Part 3: Update constructor.
        this.controller = controller;
        this.calendarEntries = FXCollections.observableArrayList(entries);
        courseListView.setCalendarView(this);
        this.calendarListView = new ListView<>(calendarEntries);

        VBox vbox = new VBox();

        Button back = new Button("Back");
        back.setOnAction(event -> {
            courseListView.show();
            close();
        });
        Button createButton = new Button("Create new Entry");
        createButton.setOnAction(event -> createCalendarEntry());

        Label calendarLabel = new Label("Personal Calendar");
        calendarListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        calendarListView.setOnMouseClicked(
                event -> selectCalendarEntry(calendarListView.getSelectionModel().getSelectedItem()));
        vbox.getChildren().addAll(calendarLabel, calendarListView, back, createButton);

        Scene scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);
        setScene(scene);
        setTitle("Personal Calendar");
        displayCalendar();
        controller.setCalendarView(this);
    }

    private void createCalendarEntry() {
        controller.selectCalendarEntry(new Entry());
        //TODO Part 3: Implement method createCalendarEntry().
    }


    private void selectCalendarEntry(Entry entry) {
        if(entry==null){return;}
        //Note: Could be a NotNull annotation. This method is triggered even the clicked point is blank.
        //      In that case this method is called with a null parameter. So we deal with it.

        entry.notifyObservers();
        controller.selectCalendarEntry(entry);
        //TODO Part 3: Implement method selectCalendarEntry(...).
    }

    public void addCalendarEntry(Entry entry) {
        if(calendarEntries.contains(entry))
            return;
        //Note: I don't know what is exactly counted as duplicate entry, by this implementation
        //      I use the default contain/equals instead of my own judgement.

        calendarEntries.add(entry);
        entry.addObserver(this);
        //Note: tells the view to take care of the entry."Don't forget to rerender if
        //      they changed their name."

        //TODO Part 2: Implement method addCalendarEntry(...).
    }

    @Override
    public void update() {
        displayCalendar();
    }

    private void displayCalendar() {
        calendarListView.refresh();
    }

}
