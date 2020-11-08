public class FinalTest {


}

class FinalTestA{
    final int id;

    public FinalTestA() {
        id=0;
    }

    public void doSome(){
        final int m;
        System.out.println("do some thing ....");
    }

}
class FinalTestB extends FinalTestA{
    public static void main(String[] args) {

    }
}


