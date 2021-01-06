package com.javase.interfaceTest;

public class InterfaceTest2 {

    public static void main(String[] args) {
        ChineseCooker chineseCooker =new ChineseCooker();
        Customer customer =new Customer(chineseCooker);
        customer.order();

    }
}


//
class WestCooker implements FoodMenu{
    public void doSomeNoodles(){
        System.out.println("do some noodles by com.javase.interfaceTest.WestCooker!");
    }
    public void doSomeRices(){

    }
}

class ChineseCooker implements FoodMenu{
    public void doSomeNoodles(){
        System.out.println("do some noodles by com.javase.interfaceTest.ChineseCooker!");
    }

    public void doSomeRices(){
        
    }
}

interface FoodMenu{
    void doSomeNoodles();
    void doSomeRices();
}


class Customer{

    private FoodMenu foodMenu;

    public Customer(){

    }
    public Customer(FoodMenu foodMenu) {
        this.foodMenu = foodMenu;
    }

    public void setFoodMenu(FoodMenu foodMenu) {
        this.foodMenu = foodMenu;
    }

    public FoodMenu getFoodMenu() {
        return foodMenu;
    }

    public void order(){
        foodMenu.doSomeNoodles();
    }
}