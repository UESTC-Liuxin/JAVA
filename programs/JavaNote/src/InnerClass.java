public class InnerClass {


    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        innerClass.mySum(
                //定义一个匿名内部类实现接口
                new Sum() {
                    public int sum(int a, int b) {
                        return a + b;
                    }
                },
                100,
                200
        );
    }

    public int mySum(Sum s, int a, int b) {
        return s.sum(a, b);
    }

}

interface Sum {
    public int sum(int a, int b);
}



