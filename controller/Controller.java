package de.tum.in.ise.controller;

import de.tum.in.ise.model.Course;
import de.tum.in.ise.model.Entry;
import de.tum.in.ise.view.*;


public class Controller {
    private CourseListView courseListView;
    private CalendarView calendarView;
    //TODO Part 2: Instantiate and use this CourseDetailView in the selectCourse(...), do NOT make this a local variable.
    private CourseDetailView courseDetailView;
    //TODO Part 2: Instantiate and use this CalendarDetailsView in the selectCalendarEntry(...), do NOT make this a local variable.
    private CalendarDetailsView calendarDetailsView;

    public void saveCourse(Course course) {
        courseListView.addCourse(course);
        //note: data is saved, but normally a method called "addCourse"
        //      can't do much more than that.

        course.notifyObservers();
        //note: Since the *data-entity/observable is changed*, Notice the
        //      corresponding observers to take a glance such that they can
        //      do the corresponding change/update e.g. GUI display stuffs.


        //TODO Part 2: Implement saveCourse(...).
    }

    public void selectCourse(Course course) {
        courseDetailView = new CourseDetailView(this,course);
        //Note: JavaFx thing.
        courseDetailView.show();
        //Note: Do the javafx thing to show a view.


        //TODO Part 2: Implement selectCourse(...).
    }

    public void saveCalendarEntry(Entry entry) {
        calendarView.addCalendarEntry(entry);
        entry.notifyObservers();
        //Note: Similar to saveCourse().


        //TODO Part 2: Implement saveCalendarEntry(...).
    }

    public void selectCalendarEntry(Entry entry) {
        calendarDetailsView = new CalendarDetailsView(this,entry);
        //Note: new?
        calendarDetailsView.show();
        //Note: similar to selectCourse, call some javaFx methods to render/build-up and pop the view.


        //TODO Part 2: Implement selectCalendarEntry(...).
    }

    public void setCourseListView(CourseListView courseListView) {
        this.courseListView = courseListView;
    }

    public void setCalendarView(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

}
