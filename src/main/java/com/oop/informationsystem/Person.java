package com.oop.informationsystem;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    String id;
    String password;

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

    public Person(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public void logOut() {

    }

    public boolean addNote(String note) {
        //Return true on success
        boolean result = true;
        return result;
    }

    public LocalDate getDateAndTime() {
        LocalDate currentDate = LocalDate.now();
        return currentDate;
    }

    public List<Class> getTodaysClasses() {
        List<Class> todaysClasses = new ArrayList<Class>();
        return todaysClasses;
    }
}
