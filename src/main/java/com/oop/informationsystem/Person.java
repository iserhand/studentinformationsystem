package com.oop.informationsystem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    String id;
    String name;
    String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    String password;
    List<String> notes;
    List<Class> todaysClasses;


    public Person(String id, String password, String name, String surname) {
        this.id = id;
        this.password = password;
        notes = new ArrayList<>();
        todaysClasses = new ArrayList<Class>();
    }

    public void logOut() {

    }

    public void addNote(String note) {
        //Return true on success
        notes.add(note);

    }

    public LocalDate getDateAndTime() {
        LocalDate currentDate = LocalDate.now();
        return currentDate;
    }

    public List<Class> getTodaysClasses() {
        return todaysClasses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
