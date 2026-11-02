package org.example;

public class Cat extends Animal{
    // ① 必须第一行用 super 调父类构造
    public Cat(String name, int age) {
        super(name, age);
    }
    // ② 重写抽象方法
    @Override
    public void makeSound() {
        System.out.println(getName() + "喵喵喵");
    }
    //③ super 调父类版本，复用 + 增强
    @Override
    public  void eat(){
        super.eat();
        System.out.println(getName() + "吃的是鱼");
    }
    //子类独有方法
    public void climb(){
        System.out.println(getName() + "爬上了树");
    }
}
