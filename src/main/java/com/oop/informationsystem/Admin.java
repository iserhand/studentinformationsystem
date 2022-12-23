package com.oop.informationsystem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Admin extends Person {
    public Admin(String id, String password) {
        super(id, password);
    }

    public boolean createNewStudent(Student student) {
        Person person = student;
        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream("database/" + person.getId() + ".txt");
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
                    = new FileOutputStream("database/" + person.getId() + ".txt");
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void adminSpecialOp() {
        System.out.println("IM AN ADMIN");
    }
}
