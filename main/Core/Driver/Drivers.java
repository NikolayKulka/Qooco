package Core.Driver;

import qooco.StartDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static Core.TestMethods.XmlReader.readXML;


public abstract class Drivers extends StartDriver {

    public static final int waitTimeConst = Integer.parseInt(readXML("baseData","waiterTime"));



    protected  WebElement waitClickable(By selector) {
        return new WebDriverWait(mobiledriver,waitTimeConst).until(ExpectedConditions.elementToBeClickable(selector));
    }


    protected  WebElement waitClickable3(By selector) {
        return new WebDriverWait(mobiledriver,waitTimeConst/8).until(ExpectedConditions.elementToBeClickable(selector));
    }


    protected  String getText(By selector) {

        return new WebDriverWait(mobiledriver, waitTimeConst).until(ExpectedConditions.presenceOfElementLocated(selector)).getText();
    }

    protected  void textPresentLong(By selector, String text)
    {
        WebDriverWait wait= new WebDriverWait(mobiledriver,waitTimeConst*5);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(selector,text));
    }

    protected  void textPresentShort(By selector, String text)
    {
        WebDriverWait wait= new WebDriverWait(mobiledriver,waitTimeConst/10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(selector,text));
    }




}