package app.fileManager;

import app.Deposit;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    static Gson gson = new Gson();
    private UAHCurrencyFile uahCurrencyFile;
    private EuroCurrencyFile euroCurrencyFile;
    private USDCurrencyFile usdCurrencyFile;
    private DateFile dateFile;

    public FileManager() {
        uahCurrencyFile = new UAHCurrencyFile();
        euroCurrencyFile = new EuroCurrencyFile();
        usdCurrencyFile = new USDCurrencyFile();
        dateFile = new DateFile();
    }

    public boolean needUpdate(String date) {
        if (!isExistFile(uahCurrencyFile.file)) {
            createFile(uahCurrencyFile.file);
            return true;
        }
        if (!isExistFile(euroCurrencyFile.file)) {
            createFile(euroCurrencyFile.file);
            return true;
        }
        if (!isExistFile(usdCurrencyFile.file)) {
            createFile(usdCurrencyFile.file);
            return true;
        }
        if (!isExistFile(dateFile.file)) {
            createFile(dateFile.file);
            return true;
        } else {
            if (dateFile.compareDate(date)) {
                return false;
            } else {
                return true;
            }
        }
    }

    private void createFile(File file) {
        try {
            if (file.createNewFile())
                System.out.println("File has been created");
            else
                System.out.println("File already exist");
        } catch (IOException e) {
            System.out.println("File has not been created");
        }
    }

    private boolean isExistFile(File file) {
        return file.isFile();
    }

    public void updateFiles(String date, ArrayList<Deposit> uah, ArrayList<Deposit> usd, ArrayList<Deposit> euro){
        dateFile.overwriteFile(date);
        uahCurrencyFile.saveFile(uah);
        usdCurrencyFile.saveFile(usd);
        euroCurrencyFile.saveFile(euro);
    }
}
