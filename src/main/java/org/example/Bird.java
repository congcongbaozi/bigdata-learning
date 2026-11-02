package org.example;
//Bird is-a Animal（单继承）且 Bird can-do Flyable（多实现）。
// Java 只能 extends 一个父类，但能 implements 多个接口——这就是"既是 X 又能做 Y"的表达方式。
public class Bird extends Animal implements FlyAble{
    public Bird(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " 叽叽喳喳");
    }
    //// 实现接口方法
    @Override
    public void fly() {
        System.out.println(getName() + " 扇动翅膀飞起来了");
    }
}
