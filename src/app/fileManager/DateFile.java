package app.fileManager;

import static app.fileManager.FileManager.gson;

import java.io.*;

public class DateFile {
    File file = new File("src/app/resources/fileStorage/date.txt");

    public void overwriteFile(String newDate) {
        try(FileWriter fileWriter = new FileWriter(file, false)) {
            gson.toJson(newDate, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readDate() {
        String str = "";
        try(Reader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader)) {
            while (true) {
                try {
                    if (!buffReader.ready()) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    str = buffReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.replaceAll("\"", "");
    }

    public boolean compareDate(String dateFromSite) {
        return dateFromSite.equals(readDate());
    }
}
