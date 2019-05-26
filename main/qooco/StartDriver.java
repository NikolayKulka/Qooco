package qooco;

import Core.Email.SendMail;
import Core.TestMethods.StatusResult;
import io.appium.java_client.*;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class StartDriver extends  StatusResult {

    private static String path = System.getProperty("user.dir");

    public static final String aPPlocation = path + "\\resources\\Qooco Talk.apk"; ;
    public static AndroidDriver mobiledriver;
    public static String sessionID;
    public static DesiredCapabilities capabilities;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        DesiredCapabilities serverCapabilities = new DesiredCapabilities();
        serverCapabilities.setCapability("deviceName","Android");
        AppiumServiceBuilder builder = new AppiumServiceBuilder().
                withCapabilities(serverCapabilities);
        AppiumDriverLocalService service = builder.build();
        service.start();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        sessionID = RandomStringUtils.randomNumeric(10);

        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.8.0"); 
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP, aPPlocation);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("newCommandTimeout", 1000);
        capabilities.setCapability("unlockType", "password");
        capabilities.setCapability("unlockKey", "1111");
        /*capabilities.setCapability("language", "ua");*/
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        /*capabilities.setCapability("eventTimings", true);*/
        /*capabilities.setCapability("ignoreUnimportantViews", true);*/
        capabilities.setCapability("noSign", true);
        capabilities.setCapability("fullReset", true); //раскомментить
        /*capabilities.setCapability("autoGrantPermissions", true);*/
        /*capabilities.setCapability("automationName", "uiautomator2");*/
        /*capabilities.setCapability("clearSystemFiles", true);*/
        capabilities.setCapability("disableWindowAnimation", true);
        /*capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");*/
        /*capabilities.setCapability("skipUnlock", true);*/
        /*capabilities.setCapability("ignoreUnimportantViews", true);
        capabilities.setCapability("disableAndroidWatchers", true);*/

        capabilities.setCapability("appPackage", "com.qooco.qoocotalk1001");
        /*capabilities.setCapability("appWaitActivity", "com.qooco.WIGActivity");*/
        capabilities.setCapability("appActivity", "com.qooco.StartUpActivity");
        /*capabilities.setCapability("appActivity", "com.qooco.WIGActivity");*/


        try {

            mobiledriver  = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            /*mobiledriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
            mobiledriver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 50);
            mobiledriver.setSetting(Setting.IGNORE_UNIMPORTANT_VIEWS, true);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }
    @AfterMethod(alwaysRun = true)
    public void afterTest(ITestResult result, Method method) throws Exception {
        mobiledriver.quit();
        new StatusResult().TestStatus(result, method);
    }

    @AfterSuite
    public void send()
    {
        new SendMail().sendMail(mailBody);
    }
}