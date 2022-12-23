package com.oop.informationsystem;

import java.io.Serializable;

public class Student extends Person implements Serializable {
    public Student(String id, String password) {
        super(id, password);
    }
}
