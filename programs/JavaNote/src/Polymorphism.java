public class Polymorphism {
    public static void main(String[] args) {

//向上转型
        Animal a =new Bird();
        if(a instanceof Bird){
            Bird b=(Bird)a;
            System.out.println(b.category);
        }


    }
}


class Animal{
    public void move(){
        System.out.println("animal is moving");
    }

}


class Bird extends Animal{
    public String category="Bird";
    @Override
    public void move() {
        System.out.println("Bird is flying!");//方法覆盖
    }
}