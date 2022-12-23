package com.oop.informationsystem;

import java.io.Serializable;

public class Professor extends Person implements Serializable {
    public Professor(String id, String password) {
        super(id, password);
    }
}
