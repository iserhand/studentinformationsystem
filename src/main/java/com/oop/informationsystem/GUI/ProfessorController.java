package com.oop.informationsystem.GUI;

import com.oop.informationsystem.Admin;
import com.oop.informationsystem.Class;
import com.oop.informationsystem.Professor;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProfessorController {
    public ListView todaysClassesList;
    public AnchorPane anchorPane;
    public Label dateAndTime;
    Professor professor;
    Stage stage;

    public void setStage() {
        stage = (Stage) anchorPane.getScene().getWindow();
        professor = (Professor) stage.getUserData();
        populateTable();
    }

    private void populateTable() {
        //TODO:Add a menu to submit  absenteeism for a student.
        //TODO:Add a menu to show the notes and a + button to add a new note or - to remove one.
        String[] holidays = {"29/10", "23/04", "30/08"};
        //National holidays list.
        List<String> holidaysList = Arrays.asList(holidays);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        if (holidaysList.contains(formatter.format(date))) {
            //Holiday TODO:Add holiday note
        } else if (i == 1 || i == 7) {
            //Weekend TODO: Add weekend note
        } else {
            //gogo power rangers
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
                        if (c.getDaysOfWeek()[i - 2] == 1 && c.getTeacher().getId().equals(professor.getId())) {
                            classList.add(c);
                        }
                        objectInputStream.close();
                    } catch (Exception ignored) {
                    }
                }
            }
            todaysClassesList.getItems().addAll(classList);
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

    public void submitAbsenteeism(ActionEvent event) {


    }
}
