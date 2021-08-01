import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class TestScenario1 extends BaseTest {


    @Test
    public void verifyNews() {
        try {
            launchApplicationUnderTest();
            String news = "Bogor volunteers bury those who died at home, as COVID-19 stretches Indonesia's healthcare system";
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
