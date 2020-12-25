import java.util.Objects;

public class ObstractTest {

    int a;
    int b;
    public static void main(String[] args) {
        new CreditAccount();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObstractTest)) return false;
        ObstractTest that = (ObstractTest) o;
        return a == that.a &&
                b == that.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
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
