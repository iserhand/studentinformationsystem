package com.oop.informationsystem;

import java.io.Serializable;
import java.util.List;

public class Class implements Serializable {
    private Professor teacher;
    private String className;
    private int[] daysOfWeek;
    private String classRoom;
    private String hour;

    public Class(String className) {
        this.className = className;
    }

    private List<Student> students;

}
