package com.oop.informationsystem.GUI;

import com.oop.informationsystem.Class;
import com.oop.informationsystem.Professor;
import com.oop.informationsystem.Student;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudentController {

    public AnchorPane anchorPane;
    public Label dateAndTime;
    public Label absenteeism;
    public ChoiceBox<Class> registerChoiceBox;
    public ListView<String> myNotes;
    public Label nameLabel;
    public TableView<Class> todaysClasses;
    public TableColumn classroomColumn;
    public TableColumn hourColumn;
    public TableColumn classCodeColumn;
    Stage stage;
    Student student;

    public void setStage() {
        stage = (Stage) anchorPane.getScene().getWindow();
        student = (Student) stage.getUserData();
        populateChoiceBox();
        populateMyNotes();
        populateTable();
    }

    private void populateTable() {
        classroomColumn.setCellValueFactory(new PropertyValueFactory<Class, String>("classRoom"));
        hourColumn.setCellValueFactory(new PropertyValueFactory<Class, String>("hour"));
        classCodeColumn.setCellValueFactory(new PropertyValueFactory<Class, String>("classCode"));
        nameLabel.setText("Welcome " + student.getName());
        //National holidays list.
        absenteeism.setText("Absenteeism:" + student.getAbsent());
        String[] holidays = {"29/10", "23/04", "30/08"};
        List<String> holidaysList = Arrays.asList(holidays);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<String> registeredClasses = student.getRegisteredClasses();
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        if (holidaysList.contains(formatter.format(date))) {
            //Holiday TODO:Add holiday note
        } else if (i == 1 || i == 7) {
            //Weekend TODO: Add weekend note
        } else {
            //go go power rangers
            Class c;
            List<Class> classList = new ArrayList<>();
            File f = new File("database/classes/");

            FilenameFilter textFilter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".txt");
                }
            };

            File[] files = f.listFiles(textFilter);
            assert files != null;
            for (File file : files) {
                if (!file.isDirectory()) {
                    try {
                        FileInputStream fileInputStream
                                = new FileInputStream(file);
                        ObjectInputStream objectInputStream
                                = new ObjectInputStream(fileInputStream);
                        c = (Class) objectInputStream.readObject();
                        if (c.getDaysOfWeek()[i - 2] == 1 && registeredClasses.contains(c.getClassCode())) {
                            classList.add(c);
                        }
                        objectInputStream.close();
                    } catch (Exception ignored) {
                    }
                }
            }
            todaysClasses.getItems().addAll(classList);
        }
    }

    private void populateChoiceBox() {
        Class c = null;
        List<Class> classList = new ArrayList<>();
        File f = new File("database/classes/");
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File[] files = f.listFiles(textFilter);
        assert files != null;
        for (File file : files) {
            if (!file.isDirectory()) {
                try {
                    FileInputStream fileInputStream
                            = new FileInputStream(file);
                    ObjectInputStream objectInputStream
                            = new ObjectInputStream(fileInputStream);
                    c = (Class) objectInputStream.readObject();
                    if (!student.getRegisteredClasses().contains(c.getClassCode())) {
                        classList.add(c);
                    }
                    objectInputStream.close();
                } catch (Exception ignored) {
                }
            }
        }
        List<String> studentsclasses = student.getRegisteredClasses();
        registerChoiceBox.getItems().addAll(classList);
    }

    private void populateMyNotes() {
        List<String> notes = student.getNotes();
        myNotes.getItems().addAll(notes);
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

    public void registerClass(ActionEvent event) {
        //Register for a class from choice box
        if (registerChoiceBox.getSelectionModel().getSelectedItem().getClassCode().isBlank() || registerChoiceBox.getSelectionModel().getSelectedItem().getClassCode().isEmpty()) {
            //TODO: Add warning here...
            return;
        }
        //TODO: Add Success status UI here.
        Class registeredClass = registerChoiceBox.getSelectionModel().getSelectedItem();
        List<String> classList = student.getRegisteredClasses();
        classList.add(registeredClass.getClassCode());
        student.setRegisteredClasses(classList);
        List<Student> studList = registeredClass.getStudents();
        studList.add(student);
        registeredClass.setStudents(studList);
        registeredClass.updateTextFile();
        registerChoiceBox.getItems().remove(registeredClass);
        registerChoiceBox.setValue(null);
        populateTable();

    }

    public void logOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login-view.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setUserData(null);
        stage.setTitle("Log-in");
        stage.setScene(scene);
        stage.show();
    }

    public void addNote(ActionEvent event) {
        //POP UP
        TextField newNoteText = new TextField();
        Label noteAddedLabel = new Label("Successfully Added new Note!");
        noteAddedLabel.setTextFill(Color.LIGHTGREEN);
        noteAddedLabel.setDisable(true);
        noteAddedLabel.setVisible(false);
        newNoteText.setOnKeyTyped(event1 -> {
            noteAddedLabel.setDisable(true);
            noteAddedLabel.setVisible(false);
        });
        Button okBtn = new Button("Add");
        okBtn.setStyle("--fxbackground-color: lightgreen");
        okBtn.setOnAction(e -> {
            List<String> notes = student.getNotes();
            notes.add(newNoteText.getText());
            student.setNotes(notes);
            noteAddedLabel.setDisable(false);
            noteAddedLabel.setVisible(true);
            myNotes.getItems().add(newNoteText.getText());
        });
        VBox popupPane = new VBox();
        popupPane.setStyle("-fx-padding: 15px;-fx-alignment: center");
        popupPane.setSpacing(15);
        popupPane.getChildren().addAll(newNoteText, noteAddedLabel, okBtn);

        Stage popUp = new Stage();
        Scene popUpScene = new Scene(popupPane, 250, 150);
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("New Note");
        popUp.setScene(popUpScene);
        popUp.showAndWait();

    }

    public void removeNote(ActionEvent event) {
        String removeNote = myNotes.getSelectionModel().getSelectedItem();
        List<String> notes = student.getNotes();
        notes.remove(removeNote);
        student.setNotes(notes);
        myNotes.getItems().remove(removeNote);
    }
}
