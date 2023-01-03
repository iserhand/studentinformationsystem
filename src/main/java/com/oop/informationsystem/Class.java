package com.oop.informationsystem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Class implements Serializable {
    private Professor teacher;
    private String className;
    private String classCode;
    private int[] daysOfWeek = {0, 0, 0, 0, 0, 0, 0};
    private String classRoom;
    private String hour;
    private List<String> students;

    public Class(String className, String classCode, Professor teacher, int[] daysOfWeek, String classRoom, String hour) {
        students = new ArrayList<>();
        this.classRoom = classRoom;
        this.className = className;
        this.classCode = classCode;
        this.teacher = teacher;
        this.daysOfWeek = daysOfWeek;
        this.hour = hour;
        updateTextFile();
    }

    public Professor getTeacher() {
        return teacher;
    }

    public void setTeacher(Professor teacher) {
        this.teacher = teacher;
        updateTextFile();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        updateTextFile();
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
        updateTextFile();
    }

    public int[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(int[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
        updateTextFile();
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
        updateTextFile();
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
        updateTextFile();
    }

    public List<String> getStudents() {
        return students;

    }

    public void setStudents(List<String> students) {
        this.students = students;
        updateTextFile();
    }


    public void updateTextFile() {
        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream("database/classes/" + this.getClassCode() + ".txt");
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getClassName() + ":" + getClassCode();
    }
}
