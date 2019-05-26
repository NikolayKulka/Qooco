package qooco.GenerateData;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;



public class TestData {


    public  JSONObject dataForTest = new JSONObject();

    public  JSONObject CreateAllDate() {
        JSONObject mainInformation=new JSONObject();
        mainInformation.put("first_name", "Test" + RandomStringUtils.randomNumeric(5));
        mainInformation.put("password", RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(5));
        dataForTest.put("personal_information", mainInformation);
        return dataForTest;
    }
}
