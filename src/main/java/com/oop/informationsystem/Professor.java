package com.oop.informationsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Person implements Serializable {
    List<Class> teaches = new ArrayList<>();

    public Professor(String id, String password, String name, String surname) {
        super(id, password, name, surname);
    }

    public void setTeaches(List<Class> teaches) {
        this.teaches = teaches;
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

    @Override
    public String toString() {
        return super.getId();
    }
}
