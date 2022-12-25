package com.oop.informationsystem;

import java.io.Serializable;

public class Professor extends Person implements Serializable {
    public Professor(String id, String password, String name, String surname) {
        super(id, password, name, surname);
    }

    @Override
    public String toString() {
        return super.getId();
    }
}
