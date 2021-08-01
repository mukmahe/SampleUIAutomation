package pages;

import helper.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class WeatherPage {

    WebDriver driver;
    Actions actions = new Actions();

    public WeatherPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By numberOfTables = By.cssSelector("[class='block']");
    String table = "[id='map02'] [class='color-style0{replace}']";


    public void verifyWeather(String city) throws Exception {
        try {
            for (int i = 2; i < driver.findElements(numberOfTables).size(); i++) {
                table = actions.replacePlaceHolder(table, String.valueOf(i));
                String firstColumn = table + " tbody tr td:nth-child(1)";
                List<WebElement> column1 = driver.findElements(By.cssSelector(firstColumn));
                for (int j = 0; j < column1.size(); j++) {
                    if (column1.get(j).getAttribute("innerText").equalsIgnoreCase(city)) {
                        int matchingRow = j + 1;
                        actions.log("Temprature of City " + city);
                        actions.log(driver.findElement(By.cssSelector(table + " tbody tr:nth-child(" + matchingRow + ") td:nth-child(2)")).getText());
                        actions.log("Weather Condtion of City " + city);
                        actions.log(driver.findElement(By.cssSelector(table + " tbody tr:nth-child(" + matchingRow + ") td:nth-child(4)")).getText());
                        break;
                    }
                }
            }

        } catch (Exception e) {
            throw new Exception("Failed while selecting Weather News " + e);
        }
    }
}



