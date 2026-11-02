package org.example;

public class Dog extends Animal {

    public Dog(String name, int age) {
        // ① 必须第一行用 super 调父类构造
        super(name, age);
    }

    @Override
    public void makeSound() { // ② 重写抽象方法
        System.out.println(getName() + "汪汪汪");
    }

    @Override
    public void eat(){ // ③ super 调父类版本，复用 + 增强
        super.eat();
        System.out.println(getName() + "吃的是骨头");
    }

    public void fetch() //子类独有方法
    {
        System.out.println(getName() + "叼回了飞盘");
    }
}
