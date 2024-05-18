package de.tum.in.ise.model;

public class Entry extends Observable {
    private Course course;
    private String date;

    public Entry(Course course, String date) {
        this.course = course;
        this.date = date;
    }

    public Entry() {
        // Default
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Entry{" + course + ", Date: " + date + "}";
    }
}
