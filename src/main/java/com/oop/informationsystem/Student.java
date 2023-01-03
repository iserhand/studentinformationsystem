package com.oop.informationsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person implements Serializable {
    int absent;
    List<Class> registeredClasses;

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void addNote(String note) {
        notes.add(note);
    }

    public Student(String id, String password, String name, String surname) {
        super(id, password, name, surname);
        registeredClasses = new ArrayList<>();
        absent = 0;
    }

    public void setRegisteredClasses(List<Class> registeredClasses) {
        this.registeredClasses = registeredClasses;
    }

    public List<Class> getRegisteredClasses() {
        return registeredClasses;
    }
}
