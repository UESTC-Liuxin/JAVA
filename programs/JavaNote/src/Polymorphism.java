public class Polymorphism {
    public static void main(String[] args) {

        Animal a =new Bird();
        Animal b =new Cat();
        Observer observer=new Observer();
        observer.observe(a);
        observer.observe(a);


    }
}


class Animal{
    public void move(){
        System.out.println("animal is moving");
    }

    public static void eat(){
        System.out.println("animal is eating!");
    }

}


class Bird extends Animal{
    public String category="Bird";

    public void move() {
        System.out.println("Bird is flying!");//方法覆盖
    }
    public static void eat(){
        System.out.println("Bird is eating!");
    }
}
class Cat extends Animal{
    public String category="Cat";

    public void move() {
        System.out.println("Cat is flying!");//方法覆盖
    }
    public static void eat(){
        System.out.println("Cat is eating!");
    }
}

class Observer{
    public void observe(Animal animal){
        animal.move();
        animal.eat();
    }
}