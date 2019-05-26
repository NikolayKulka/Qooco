package NewSite;

import qooco.StartDriver;
import qooco.GenerateData.TestData;
import qooco.NewSiteMapping.HappyPass_NewSite;
import org.json.JSONObject;
import org.testng.annotations.Test;


public class NewSite_HappyPath extends StartDriver {



    @Test()
    public void LoginPositive() {

        JSONObject data = new TestData().CreateAllDate();
        new HappyPass_NewSite().Login(data,true);
    }

    @Test()
    public void LoginNegative() {

        JSONObject data = new TestData().CreateAllDate();
        new HappyPass_NewSite().Login(data,false);
    }

}