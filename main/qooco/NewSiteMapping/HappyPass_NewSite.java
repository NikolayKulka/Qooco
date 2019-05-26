package qooco.NewSiteMapping;

import qooco.ConstantData;
import Core.Driver.Drivers;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.testng.Assert;

public class HappyPass_NewSite extends Drivers {


    private static final By world =By.xpath("//android.widget.TextView[@text = 'WORLD']");
    private static final By username =By.xpath("//android.widget.EditText[@text = 'Email or username']");
    private static final By password =By.xpath("//android.widget.EditText[@text = 'Password']");
    private static final By login =By.xpath("//android.widget.TextView[@text = 'CONTINUE']");
    private static final By error =By.xpath("//android.widget.TextView[@text = 'This username has not been signed up']");
    private static final By notError =By.xpath("//android.widget.TextView[@text = 'Take a test']");
    private static final By cancel =By.xpath("//android.widget.TextView[@text = 'CANCEL']");


    public void Login(JSONObject data, boolean PositiveNegative) {
        JSONObject main=data.getJSONObject("personal_information");
        waitClickable(world).click();
        if (PositiveNegative==true)
        {
            ConstantData creds = new ConstantData();
            waitClickable(username).sendKeys(creds.getLogin());
            waitClickable(password).sendKeys(creds.getPassword());
            waitClickable(login).click();
            try{
            waitClickable3(cancel).click();}
            catch (NoSuchElementException e){}
            catch (TimeoutException t){}
            try{
                textPresentShort(notError,"Take a test");}
            catch (TimeoutException t){
                waitClickable3(cancel).click();
                textPresentShort(notError,"Take a test");
            }
            Assert.assertTrue(getText(notError).contains("Take a test"));}

        else
        {
            String name= main.getString("first_name");
            String pass= main.getString("password");
            waitClickable(username).sendKeys(name);
            waitClickable(password).sendKeys(pass);
            waitClickable3(login).click();
            textPresentLong(error,"This username has not been signed up");
            Assert.assertTrue(getText(error).contains("This username has not been signed up"));}

    }

}