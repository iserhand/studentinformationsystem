package com.oop.informationsystem.GUI;

import com.oop.informationsystem.Class;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentController {

    public ListView todaysClassesList;

    @FXML
    public void initialize() {
        //National holidays list.
        String[] holidays = {"29/10", "23/04", "30/08"};
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
                        if (c.getDaysOfWeek()[i - 2] == 1) {
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
}
