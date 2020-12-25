package uestc.javase.Demical;

import java.text.DecimalFormat;
import java.util.Random;

public class DecimalFormatTest {
    public static void main(String[] args) {

        DecimalFormat df=new DecimalFormat("#####,##.0000");
        System.out.println(df.format(12222.145));//1,22,22.1450

        Random random = new Random();
        double doubleNum=random.nextGaussian();
        System.out.println(doubleNum);

        int intNum=random.nextInt(10);
        System.out.println(intNum);
    }
}
