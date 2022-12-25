package com.oop.informationsystem.GUI;

import com.oop.informationsystem.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.*;

public class AdminController {
    public AnchorPane classAdd;
    public CheckBox mondayCheck;
    public CheckBox tuesdayCheck;
    public CheckBox wednesdayCheck;
    public CheckBox thursdayCheck;
    public CheckBox fridayCheck;
    public TextField hourTxt;
    public ChoiceBox<Professor> profChoice;
    public TextField classCodeTxt;
    public TextField classNameTxt;
    Admin admin = new Admin("abc", "abc", "abc", "abc");
    String dummyName = "Dummy Name";
    String dummySurname = "Dummy surname";
    //TODO: Add text fields for name and surname.
    public AnchorPane studentAdd;
    public TextField studentID;
    public TextField studentPW;
    public AnchorPane professorAdd;
    public TextField professorID;
    public TextField professorPW;
    public AnchorPane mainPage;

    public void onClickCreateProf(ActionEvent event) {
        if (!new File(professorID.getText()).exists()) {
            Professor prof = new Professor(professorID.getText(), professorPW.getText(), dummyName, dummySurname);
            admin.createNewProfessor(prof);
        }
    }


    public void onClickCreateStudent(ActionEvent event) {
        if (!new File(studentID.getText()).exists()) {
            Student student = new Student(studentID.getText(), studentPW.getText(), dummyName, dummySurname);
            admin.createNewStudent(student);
        }

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

    public void onClickCreateClassPage(ActionEvent event) {
        mainPage.setDisable(true);
        mainPage.setVisible(false);
        //Class adding screen
        classAdd.setDisable(false);
        classAdd.setVisible(true);
        Professor p2 = null;
        List<Professor> professorList = new ArrayList<>();
        File f = new File("database/users/");

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File[] files = f.listFiles(textFilter);
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.print("directory:");
            } else {
                try {
                    FileInputStream fileInputStream
                            = new FileInputStream(file);
                    ObjectInputStream objectInputStream
                            = new ObjectInputStream(fileInputStream);
                    p2 = (Professor) objectInputStream.readObject();
                    professorList.add(p2);
                    objectInputStream.close();
                } catch (ClassNotFoundException e) {
                    System.out.println("classnotfound");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println(professorList.size());
        profChoice.getItems().setAll(professorList);


    }


}
