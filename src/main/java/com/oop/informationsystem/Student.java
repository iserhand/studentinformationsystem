package com.oop.informationsystem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person implements Serializable {
    int absent;
    List<String> registeredClasses;
    List<String> notes;

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
        updateTextFile();
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
        updateTextFile();
    }

    public Student(String id, String password, String name, String surname) {
        super(id, password, name, surname);
        registeredClasses = new ArrayList<>();
        notes = new ArrayList<>();
        absent = 0;
        updateTextFile();
    }

    public void setRegisteredClasses(List<String> registeredClasses) {
        this.registeredClasses = registeredClasses;
        updateTextFile();
    }

    public List<String> getRegisteredClasses() {
        return registeredClasses;
    }

    public void updateTextFile() {
        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream("database/users/" + this.getId() + ".txt");
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.getId();
    }
}
