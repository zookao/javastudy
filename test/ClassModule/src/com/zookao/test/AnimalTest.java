package com.zookao.test;

/**
 * 多态性的使用 （依赖注入？）
 */
public class AnimalTest {

    public static void main(String[] args) {

        AnimalTest test = new AnimalTest();
        test.func(new Dog());
        test.func(new Cat());
    }

    public void func(Animal animal){//Animal animal = new Dog();
        animal.eat();
        animal.shout();

        if(animal instanceof Dog){
            Dog d = (Dog)animal;
            d.watchDoor();
        }
    }

	/*public void func(Dog dog){
		dog.eat();
		dog.shout();
	}
	public void func(Cat cat){
		cat.eat();
		cat.shout();
	}*/
}

class Animal{
    public void eat(){
        System.out.println("动物：进食");
    }
    public void shout(){
        System.out.println("动物：叫");
    }
}

class Dog extends Animal{
    public void eat(){
        System.out.println("狗吃骨头");
    }

    public void shout(){
        System.out.println("汪！汪！汪！");
    }

    public void watchDoor(){
        System.out.println("看门");
    }
}
class Cat extends Animal{
    public void eat(){
        System.out.println("猫吃鱼");
    }

    public void shout(){
        System.out.println("喵！喵！喵！");
    }
}