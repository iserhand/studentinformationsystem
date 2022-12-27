package com.oop.informationsystem.GUI;

import com.oop.informationsystem.*;
import com.oop.informationsystem.Class;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class AdminController {
    public HBox hboxDaySelect;
    public Label lblWarningDaySelect;
    public Label dateAndTime;
    private boolean dontCreate = false;
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
    public Label clockCheckLabel;
    public TextField classroomTxt;
    public Button goBackClassBtn;
    public Button goBackProfBtn;
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
    public AnchorPane mainPage;

    public void onClickCreateProf(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage getInfo = (Stage) node.getScene().getWindow();
        admin = (Admin) getInfo.getUserData();
        if (!new File("database/users/" + professorID.getText() + ".txt").exists()) {
            Professor prof = new Professor(professorID.getText(), professorPW.getText(), dummyName, dummySurname);
            admin.createNewProfessor(prof);
        }
    }


    public void onClickCreateStudent(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage getInfo = (Stage) node.getScene().getWindow();
        admin = (Admin) getInfo.getUserData();
        if (!new File("database/users/" + studentID.getText() + ".txt").exists()) {
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
                } catch (IOException ex) {
                } catch (Exception e) {
                }
            }
        }
        profChoice.getItems().setAll(professorList);

    }


    public void onClickCreateClass(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage getInfo = (Stage) node.getScene().getWindow();
        admin = (Admin) getInfo.getUserData();
        Professor selectedProf = profChoice.getSelectionModel().getSelectedItem();
        int[] daysOfWeek = {0, 0, 0, 0, 0, 0, 0};
        if (mondayCheck.isSelected() || tuesdayCheck.isSelected() || wednesdayCheck.isSelected() || thursdayCheck.isSelected() || fridayCheck.isSelected()) {
            //keep going
            hboxDaySelect.setStyle("-fx-border-color: default");
            lblWarningDaySelect.setVisible(false);
        } else {
            hboxDaySelect.setStyle("-fx-border-color: red");
            lblWarningDaySelect.setVisible(true);
            return;
        }

        if (mondayCheck.isSelected())
            daysOfWeek[0] = 1;
        if (tuesdayCheck.isSelected())
            daysOfWeek[1] = 1;
        if (wednesdayCheck.isSelected())
            daysOfWeek[2] = 1;
        if (thursdayCheck.isSelected())
            daysOfWeek[3] = 1;
        if (fridayCheck.isSelected())
            daysOfWeek[4] = 1;
        Class c = new Class(classNameTxt.getText(), classCodeTxt.getText(), selectedProf, daysOfWeek, classroomTxt.getText());
        List<Class> teaches = selectedProf.getTeaches();
        teaches.add(c);
        selectedProf.setTeaches(teaches);
        c.updateTextFile();
        if (dontCreate) {
            //TODO:Add warning label here on top of the button
        } else {
            //POP UP
            Label successLbl = new Label("Successfully Added a new Class:" + c.getClassCode());
            Button okBtn = new Button("OK!");
            okBtn.setStyle("--fxbackground-color: lightgreen");
            okBtn.setOnAction(e -> {
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.close();
            });
            VBox popupPane = new VBox();
            popupPane.setStyle("-fx-alignment: center");
            popupPane.setSpacing(15);
            popupPane.getChildren().addAll(successLbl, okBtn);

            Stage popUp = new Stage();
            Scene popUpScene = new Scene(popupPane, 250, 100);
            popUp.initModality(Modality.APPLICATION_MODAL);
            popUp.setTitle("SUCCESS!");
            popUp.setScene(popUpScene);
            popUp.showAndWait();
        }


    }

    public void keyTyped(KeyEvent keyEvent) {
        try {
            LocalTime.parse(hourTxt.getText());
            clockCheckLabel.setVisible(false);
            hourTxt.setStyle("-fx-text-box-border: green; -fx-focus-color: green;");
            dontCreate = false;
        } catch (DateTimeParseException | NullPointerException e) {
            clockCheckLabel.setVisible(true);
            hourTxt.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
            dontCreate = true;
        }
    }

    public void goBackProfBtn(ActionEvent event) {
        //Go back from the add professor page
        mainPage.setDisable(false);
        mainPage.setVisible(true);
        professorAdd.setDisable(true);
        professorAdd.setVisible(false);

    }

    public void goBackClassBtn(ActionEvent event) {
        //Go back from the add class page
        mainPage.setDisable(false);
        mainPage.setVisible(true);
        classAdd.setDisable(true);
        classAdd.setVisible(false);
    }

    public void checkClassCode(KeyEvent keyEvent) {
        if (classCodeTxt.getText().isBlank() || classCodeTxt.getText().isEmpty()) {
            classCodeTxt.setStyle("-fx-border-color: red");
        } else {
            classCodeTxt.setStyle("-fx-border-color: green");
            if (new File("database/classes/" + classCodeTxt.getText() + ".txt").exists()) {
                classCodeTxt.setStyle("-fx-border-color: red");
                classCodeTxt.setPromptText("THIS CLASS ALREADY EXISTS!");
                dontCreate = true;
            } else {
                classCodeTxt.setStyle("-fx-border-color: green");
                classCodeTxt.setPromptText("");
                dontCreate = false;
            }
        }
    }

    public void checkClassName(KeyEvent keyEvent) {
        if (classNameTxt.getText().isBlank() || classNameTxt.getText().isEmpty()) {
            dontCreate = true;
        } else {
            dontCreate = false;
        }
    }

    public void checkProfSelection(ActionEvent event) {
        if (profChoice.getSelectionModel().getSelectedItem().getId().isEmpty() || profChoice.getSelectionModel().getSelectedItem().getId().isBlank()) {
            dontCreate = true;
        } else {
            dontCreate = false;
        }
    }

    public void dayCheckAction(ActionEvent event) {
        if (mondayCheck.isSelected() || tuesdayCheck.isSelected() || wednesdayCheck.isSelected() || thursdayCheck.isSelected() || fridayCheck.isSelected()) {
            hboxDaySelect.setStyle("-fx-border-color: default");
            lblWarningDaySelect.setVisible(false);
            dontCreate = false;
        } else {
            hboxDaySelect.setStyle("-fx-border-color: red");
            lblWarningDaySelect.setVisible(true);
            dontCreate = true;
        }
    }

    @FXML
    public void initialize() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                dateAndTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            }
        };
        timer.start();
    }


    public void goBackStudentBtn(ActionEvent event) {
        mainPage.setDisable(false);
        mainPage.setVisible(true);
        studentAdd.setDisable(true);
        studentAdd.setVisible(false);
    }
}
