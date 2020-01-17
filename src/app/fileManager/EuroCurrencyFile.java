package app.fileManager;

import app.Deposit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static app.fileManager.FileManager.gson;

public class EuroCurrencyFile {
    File file = new File("src/app/resources/fileStorage/EUROcurrency.txt");

    public void saveFile(ArrayList<Deposit> deposits) {
        try(FileWriter fileWriter = new FileWriter(file, false)) {
            gson.toJson(deposits, fileWriter);
        } catch (IOException e) {
            System.out.println("Something was wrong with euro currency file");
        }
    }
}
