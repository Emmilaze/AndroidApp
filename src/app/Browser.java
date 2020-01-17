package app;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser {

    private WebDriver driver;

    public void setDriver(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void openPage(String page){
        driver.get(page);
        WebElement element;
        do {
            try {
                element = driver.findElement(By.xpath("//*[@class='sc-282uzu-1 iJFHdl']"));
                element.click();
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                element = null;
            }
        }
        while (element != null);
    }

    public String getDate(String page){
        driver.get(page);
        return driver.findElement(By.xpath("/html/body/main/div/div/section/div/div/div[2]/div/div[1]/div[2]/span[2]/time")).getText();
    }

    public ArrayList<Deposit> getDeposits(String currency){
        List<WebElement> bankNames;
        bankNames = driver.findElements(By.xpath("//*[@id=\"root\"]/div/section/div/div/div[2]/div/div[2]/table/tbody/tr/td[1]/a/span"));
        List<WebElement> depositNames;
        depositNames = driver.findElements(By.xpath("//*[@id=\"root\"]/div/section/div/div/div[2]/div/div[2]/table/tbody/tr/td[2]/a"));
        List<WebElement> percents;
        percents = driver.findElements(By.xpath("//*[@id=\"root\"]/div/section/div/div/div[2]/div/div[2]/table/tbody/tr/td[3]/strong[1]"));
        List<WebElement> durations;
        durations = driver.findElements(By.xpath("//*[@id=\"root\"]/div/section/div/div/div[2]/div/div[2]/table/tbody/tr/td[3]/span"));
        List<WebElement> payout;
        payout = driver.findElements(By.xpath("//*[@id=\"root\"]/div/section/div/div/div[2]/div/div[2]/table/tbody/tr/td[4]"));

        ArrayList<Deposit> deposits = new ArrayList();
        for(int i = 0; i < bankNames.size(); i++){
            deposits.add(new Deposit(bankNames.get(i).getText(), depositNames.get(i).getText(), currency,
                    Float.parseFloat(percents.get(i).getText().replaceAll("% ", "")),
                    durations.get(i).getText(), payout.get(i).getText()));
        }
        return deposits;
    }
}
