public class Inherit {
    public static void main(String[] args) {
        B b=new B();
    }

}


class A{
//    public A(){
//        System.out.println("A is called");
//    }
    public A(int i){
        System.out.println("A is called");
    }
    public void test(){
        System.out.println("A is tested");
    }
}

class B extends A{
    public B(){
        super(9);
        System.out.println("B is called");
        super.test();
    }
    public void test(){
        System.out.println("B is tested");
    }
}