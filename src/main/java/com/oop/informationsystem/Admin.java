package com.oop.informationsystem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Admin extends Person {
    public Admin(String id, String password, String name, String surname) {
        super(id, password, name, surname);
    }


    public boolean createNewStudent(Student student) {
        Person person = student;
        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream("database/users/" + person.getId() + ".txt");
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createNewProfessor(Professor professor) {
        Person person = professor;
        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream("database/users/" + person.getId() + ".txt");
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createNewAdmin(Admin admin) {
        Person person = admin;
        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream("database/users/" + person.getId() + ".txt");
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
