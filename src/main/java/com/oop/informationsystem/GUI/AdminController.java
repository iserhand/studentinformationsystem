package com.oop.informationsystem.GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AdminController {
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
        
    }

    public void onClickCreateFaculty(ActionEvent event) {

    }

    public void onClickCreateStudent(ActionEvent event) {

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
