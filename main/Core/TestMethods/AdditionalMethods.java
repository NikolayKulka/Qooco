package Core.TestMethods;

import java.util.Random;

public class AdditionalMethods  {


    public static int randomGenerate(int maxData, int minData) {
        Random random = new Random();
        return random.nextInt(maxData - minData + 1) + minData;
    }

}