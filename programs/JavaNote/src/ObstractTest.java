public class ObstractTest {
    public static void main(String[] args) {
        new CreditAccount();
    }
}
abstract class Account{
    public void test(){

    }

    public abstract void move();

}
class CreditAccount extends Account{
    public void test(){
        super.test();
    }

    @Override
    public void move() {
        System.out.println("override");
    }
}
