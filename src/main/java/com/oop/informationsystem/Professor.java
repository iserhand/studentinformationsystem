package com.oop.informationsystem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Person implements Serializable {
    List<Class> teaches;
    List<String> notes;

    public Professor(String id, String password, String name, String surname) {
        super(id, password, name, surname);
        notes = new ArrayList<>();
        teaches = new ArrayList<>();
        updateTextFile();
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
        updateTextFile();
    }

    public void setTeaches(List<Class> teaches) {
        this.teaches = teaches;
        updateTextFile();
    }

    public List<Class> getTeaches() {
        return teaches;
    }

    public List<Class> getTodaysClasses(int dayOfWeek) {
        List<Class> todaysClasses = new ArrayList<>();
        for (Class c : teaches) {
            if (c.getDaysOfWeek()[dayOfWeek] == 1) {
                todaysClasses.add(c);
            }
        }
        return todaysClasses;
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
