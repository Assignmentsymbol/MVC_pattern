package de.tum.in.ise.model;

public class Course extends Observable {
    private String id;
    private String name;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {
        // Default
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Course{ID: " + this.getId() + ", Name: " + this.getName() + "}";
    }
}
