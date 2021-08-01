package helper;

import org.testng.Reporter;

public class Actions {

    public void log(String log) {
        System.out.println(log);
        Reporter.log(log);
    }

    public String replacePlaceHolder(String locator, String text){
        locator=locator.replace("{replace}",text);
        return locator;
    }

}
