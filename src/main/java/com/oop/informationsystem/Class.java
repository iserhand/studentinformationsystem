package com.oop.informationsystem;

import java.io.Serializable;
import java.util.List;

public class Class implements Serializable {
    private Professor teacher;
    private String className;
    private int[] daysOfWeek;
    private String classNumber;
    private String hour;
    private List<Student> students;
    
}
