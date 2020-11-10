public class InterfaceTest {
    public static void main(String[] args) {
        MyMath myMath = new InfoProcessTest();
        System.out.println(myMath.add(1,2));
        MyDigitalProcess myDigitalProcess = new InfoProcessTest();
        System.out.println() ;
    }
}


interface MyMath{
 double PI=3.1415926;
 int add(int a,int b);
}

interface MyDigitalProcess{
    double sigmma=1.101;
    double multiSigmma(double a);

}
class InfoProcessTest implements MyMath,MyDigitalProcess{
    //实现接口中的方法
    public int add(int a,int b){
        return a+b;
    }

    public double multiSigmma(double a){
        return a*sigmma;
    }
}
