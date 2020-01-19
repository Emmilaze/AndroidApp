package app;

import app.fileManager.FileManager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Browser browser = new Browser();
        FileManager fileManager = new FileManager();
        browser.setDriver();
        String date = browser.getDate("https://minfin.com.ua/deposits/rates/");

        if(fileManager.needUpdate(date)){
            browser.openPage("https://minfin.com.ua/deposits/rates/");
            ArrayList<Deposit> uahDeposit = browser.getDeposits("UAH");

            browser.openPage("https://minfin.com.ua/deposits/rates/?currency-type=usd");
            ArrayList<Deposit> usdDeposit = browser.getDeposits("USD");

            browser.openPage("https://minfin.com.ua/deposits/rates/?currency-type=eur");
            ArrayList<Deposit> euroDeposit = browser.getDeposits("Euro");

            fileManager.updateFiles(date, uahDeposit, usdDeposit, euroDeposit);
        } else {
            System.out.println("dont need update");
        }
    }
}
