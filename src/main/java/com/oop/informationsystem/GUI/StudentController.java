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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudentController {

    public ListView todaysClassesList;
    public AnchorPane anchorPane;
    public Label dateAndTime;
    public Label absenteeism;
    public ChoiceBox<Class> registerChoiceBox;
    public ListView<String> myNotes;
    public Label nameLabel;
    Stage stage;
    Student student;

    public void setStage() {
        stage = (Stage) anchorPane.getScene().getWindow();
        student = (Student) stage.getUserData();
        populateTable();
    }

    private void populateTable() {
        populateChoiceBox();
        populateMyNotes();
        nameLabel.setText("Welcome " + student.getName());
        //National holidays list.
        absenteeism.setText("Absenteeism:" + student.getAbsent());
        String[] holidays = {"29/10", "23/04", "30/08"};
        List<String> holidaysList = Arrays.asList(holidays);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Class> registeredClasses = student.getRegisteredClasses();
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
                        if (c.getDaysOfWeek()[i - 2] == 1 && registeredClasses.contains(c)) {
                            classList.add(c);
                        }
                        objectInputStream.close();
                    } catch (Exception ignored) {
                    }
                }
            }
            todaysClassesList.getItems().addAll(classList);
        }
        //TODO: Add a menu to check for absenteeism
        //TODO:Add a menu to show the notes and a + button to add a new note or - to remove one.
    }

    private void populateChoiceBox() {
        //TODO: Fix circular reference error
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
                    if (!student.getRegisteredClasses().contains(c)) {
                        classList.add(c);
                    }
                    objectInputStream.close();
                } catch (Exception ignored) {
                }
            }
        }
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
        List<Class> classList = student.getRegisteredClasses();
        classList.add(registeredClass);
        student.setRegisteredClasses(classList);
        List<Student> studList = registeredClass.getStudents();
        studList.add(student);
        registeredClass.setStudents(studList);
        student.updateTextFile();
        registeredClass.updateTextFile();
        System.out.println("studentlist:");
        for (Student s : studList) {
            System.out.println(s);
        }
        System.out.println("classlist");
        for (Class c : classList) {
            System.out.println(c);
        }


        System.out.println("OK?" + registeredClass + student);
        registerChoiceBox.getItems().remove(registeredClass);
        registerChoiceBox.setValue(null);

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
        //TODO:Add new note pop up

    }

    public void removeNote(ActionEvent event) {
        //TODO:Remove selected note
    }
}
