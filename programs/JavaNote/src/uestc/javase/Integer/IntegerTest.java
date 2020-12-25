package uestc.javase.Integer;

class MyInt{
    int value;

    public MyInt(int value) {
        this.value=value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}


public class IntegerTest {
    public static void main(String[] args) {
        MyInt myInt = new MyInt(100);
        doSome(100);
        Integer integer = 100;
        int new_integer = integer;
        System.out.println(integer.floatValue());
        System.out.println(new_integer);

    }

    public static void doSome(Object obj){
        System.out.println(obj);
    }
}
