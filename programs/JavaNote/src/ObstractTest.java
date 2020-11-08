public class ObstractTest {
    public static void main(String[] args) {
        new CreditAccount();
    }
}
abstract class Account{
    public void test(){

    }

}
class CreditAccount extends Account{
    public void test(){
        super.test();
    }
}
