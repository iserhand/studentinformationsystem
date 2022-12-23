package com.oop.informationsystem.GUI;

import com.oop.informationsystem.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class AdminController {
    Admin admin;
    String dummyName = "Dummy Name";
    String dummySurname = "Dummy surname";
    //TODO: Add text fields for name and surname.
    public AnchorPane studentAdd;
    public TextField studentID;
    public TextField studentPW;
    public AnchorPane professorAdd;
    public TextField professorID;
    public TextField professorPW;
    public AnchorPane facultyAdd;
    public TextField facultyID;
    public TextField facultyPW;
    public AnchorPane mainPage;

    public void onClickCreateProf(ActionEvent event) {
        if (!new File(professorID.getText()).exists()) {
            Professor prof = new Professor(professorID.getText(), professorPW.getText(), dummyName, dummySurname);
            admin.createNewProfessor(prof);
        }
    }

    public void onClickCreateFaculty(ActionEvent event) {
        if (!new File(facultyID.getText()).exists()) {
            Person faculty = new Faculty(facultyID.getText(), facultyPW.getText(), dummyName, dummySurname);
            try {
                FileOutputStream fileOutputStream
                        = new FileOutputStream("database/" + facultyID.getText() + ".txt");
                ObjectOutputStream objectOutputStream
                        = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(faculty);
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void onClickCreateStudent(ActionEvent event) {
        if (!new File(studentID.getText()).exists()) {
            Student student = new Student(studentID.getText(), studentPW.getText(), dummyName, dummySurname);
            admin.createNewStudent(student);
        }

    }

    public void onClickCreateFacultyPage(ActionEvent event) {
        //Opens up page for adding a new faculty member
        mainPage.setDisable(true);
        mainPage.setVisible(false);
        facultyAdd.setDisable(false);
        facultyAdd.setVisible(true);
    }

    public void onClickCreateStudentPage(ActionEvent event) {
        //Opens up page for adding a new student
        mainPage.setDisable(true);
        mainPage.setVisible(false);
        studentAdd.setDisable(false);
        studentAdd.setVisible(true);
    }

    public void onClickCreateProfPage(ActionEvent event) {
        //Opens up page for adding a new professor
        mainPage.setDisable(true);
        mainPage.setVisible(false);
        professorAdd.setDisable(false);
        professorAdd.setVisible(true);
    }
}
