import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.WeatherPage;

public class TestScenario3 extends BaseTest {

    @Test
    public void verifyWeather() {
        try {
            WeatherPage weatherPage=new WeatherPage(driver);
            driver.navigate().to(prop.getProperty("urlInternational"));
            homePage.selectHamburgerMenu("Weather");
            weatherPage.verifyWeather("Singapore");

        }catch (Exception e){
            Assert.fail(e.toString());
            System.out.println("Failed due to Exception "+e);
            Reporter.log("Failed due to Exception "+e);
        }
    }

}
