package pages;

import helper.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;


public class HomePage {

    WebDriver driver;
    Actions actions = new Actions();

    @FindBy(css = "div[class='c-teaser--large is-background-dark-mix is-hero'] div[class='hero-article'] h3[class='teaser__heading'] a[href]")
    WebElement newsHeadline;

    @FindBy(css = "[name='filter-input-4']")
    WebElement edition;

    By HamburgerMenu = By.cssSelector("[class='button-main-nav__button-open-text']");

    String optionLocator = "//*[text()='{replace}'][@class='nav-primary__link']";



    //Constructor that will be automatically called as soon as the object of the class is created
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyNewsHeadline(String expectedHeadline) {
        Assert.assertEquals(newsHeadline.getText(), expectedHeadline);
        actions.log("Verified Headline on Hero Banner as " + expectedHeadline);
    }

    public void clickOnNewsHeadline() {
        newsHeadline.click();
        actions.log("Clicked on news Headline on Hero Banner");
    }

    public void selectEdition(String option) {
        Select sel = new Select(edition);
        String value = option.equalsIgnoreCase("Singapore Edition") ? "sg" : "default";
        sel.selectByValue(value);
        actions.log("Selected Edition as " + option);
    }

    public void selectHamburgerMenu(String optionToSelect) throws Exception {
        try {
            optionLocator = optionLocator.replace("{replace}", optionToSelect);
            List<WebElement> ele = driver.findElements(HamburgerMenu);
            for (int i = 0; i < ele.size(); i++) {
                try {
                    ele.get(i).click();
                } catch (Exception e) {
                    if (i == ele.size())
                        throw new Exception("Menu cant be clicked");
                }
            }
            By option = By.xpath(optionLocator);
            driver.findElement(option).click();
        }catch (Exception e){
            throw new Exception("Error while menu click for "+optionToSelect+" "+e);
        }
    }
}
