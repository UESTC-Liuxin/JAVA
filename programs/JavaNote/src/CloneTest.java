public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        ClassA a=new ClassA();
        ClassA b=(ClassA) a.clone();

    }

}

class ClassA implements Cloneable{
    String name;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        ClassA obj = (ClassA) super.clone();
        return obj;
    }
}
