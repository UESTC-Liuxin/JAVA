package uestc.javase.myenum;

public class EnumTest {
    public static void main(String[] args) {
        System.out.println(Sex.MEN);

        switch (Season.SPRING){
            case SPRING:
                System.out.println(Season.SPRING);
                break;
            case SUMMER:
                System.out.println(Season.SUMMER);
                break;
            case AUTUMN:
                System.out.println(Season.AUTUMN);
                break;
            case WINTER:
                System.out.println(Season.WINTER);
                break;
            default:
                break;
        }
    }

}
enum Sex{
    MEN,WOMEN
}
enum Season{
    SPRING,SUMMER,AUTUMN,WINTER
}