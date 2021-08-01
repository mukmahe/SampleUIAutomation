import helper.Actions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.NewsDetails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    DriverManager driverManager;
    WebDriver driver;
    HomePage homePage;
    NewsDetails newsDetails;
    Properties prop;

    @Parameters("browser")
    @BeforeClass
    public void beforeTest(String browser) {
        File file = new File("resources/config.properties");

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop = new Properties();

        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driverManager = DriverManagerFactory.getManager(browser);
        driver = driverManager.getDriver();
        Dimension d = new Dimension(275, 812);
        if(browser.equalsIgnoreCase("mobile")){
            driver.manage().window().setSize(d);
        }
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        newsDetails = new NewsDetails(driver);
    }

    @AfterClass
    public void afterMethod() {
        driverManager.quitDriver();
    }

    @Test
    public void launchApplicationUnderTest() {
        driver.get(prop.getProperty("url"));
        Assert.assertEquals("CNA - Breaking news, latest developments in Singapore, Asia and around the world", driver.getTitle());
    }

}
