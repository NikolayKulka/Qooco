package qooco;

import static Core.TestMethods.XmlReader.readXML;


public class ConstantData {

    public static String EMAIL_SENDER=readXML("baseData","mailSend");
    public static String PASS_EMAIL=readXML("baseData","mailPassword");

    public String getLogin() {return "nya"; }
    public String getPassword() {return "nya";}

}