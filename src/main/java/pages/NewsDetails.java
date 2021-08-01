package pages;

import helper.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class NewsDetails {

    WebDriver driver;
    Actions actions = new Actions();

    public NewsDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[class='article__title']")
    WebElement newsTitle;

    By secondNewsTitleBelow = By.cssSelector("div[class='article-endless-scroll-wrapper article_index_2']");

    @FindBy(css = "div[class='article-endless-scroll-wrapper article_index_1']")
    WebElement secondNewsTitleFirst;

    @FindBy(css = "div[class='article-endless-scroll-wrapper article_index_2'] div[class='article-endless-scroll-wrapper-inner'] div[class='article__read-full-story-wrapper'] button")
    WebElement readMoreButton;

    @FindBy(css = "[class='article-endless-scroll-wrapper article_index_2'] div article footer ul[class='sharelist']")
    WebElement socialIcons;

    //@FindBy(xpath="[@class='article__read-full-story-button']/parent::")

    public void verifyNewsTitle(String expectedTitle) {
        Assert.assertEquals(newsTitle.getText(), expectedTitle);
        actions.log("Verified Title of News on News Details page as " + expectedTitle);
    }

    public String selectSecondNews() throws Exception {
        try {
            WebElement ele = null;
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            for (int i = 1; i <= 20; i++) {
                try {
                    ele = driver.findElement(secondNewsTitleBelow);
                    scrollIntoView(ele);
                    break;
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    Thread.sleep(1000);
                    scrollIntoView(secondNewsTitleFirst);
                    Thread.sleep(1000);
                }
                if (i == 20) {
                    throw new Exception("News titles didnt appear");
                }
            }
            String titleOfSecondSuggestedNews = ele.findElement(By.cssSelector("h1[class='article__title']")).getText();
            actions.log("Selected Second news on news Details page whose title is " + titleOfSecondSuggestedNews);
            return titleOfSecondSuggestedNews;
        } catch (Exception e) {
            throw new Exception("Failed while selecting second News " + e);
        }
    }


    public void clickReadMoreAndVerify(String secondNewsTitle) throws Exception {
        try {
            readMoreButton.click();
            if (!socialIcons.isDisplayed())
                throw new Exception("Click on ReadMore is not working");
            else
                scrollIntoView(socialIcons);
            Assert.assertEquals(driver.findElement(secondNewsTitleBelow).findElement(By.cssSelector("h1[class='article__title']")).getText(), secondNewsTitle);
            actions.log("Clicked on read more button of 2nd Expanded news");
        } catch (Exception e) {
            throw new Exception("Error while clicking on read more on News Details page "+e);
        }
    }

    public void scrollIntoView(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
    }
}
