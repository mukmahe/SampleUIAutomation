import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestScenario2 extends BaseTest {

    @Test
    public void verifyNewsSingapore() {
        try {
            driver.navigate().to(prop.getProperty("urlInternational"));
            String news = "As Singapore gets the gardening bug, NParks stresses importance of being considerate to neighbours";
            homePage.selectEdition("Singapore Edition");
            homePage.verifyNewsHeadline(news);
            homePage.clickOnNewsHeadline();
            newsDetails.verifyNewsTitle(news);
            String expectedNewsToExpand=newsDetails.selectSecondNews();
            newsDetails.clickReadMoreAndVerify(expectedNewsToExpand);
        }catch (Exception e){
            Assert.fail(e.toString());
            System.out.println("Failed due to Exception "+e);
            Reporter.log("Failed due to Exception "+e);
        }
    }

}
