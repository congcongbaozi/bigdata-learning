package org.example;

//抽象父类，封装+抽象方法
public abstract class Animal {

    private String name;
    private int age;

    public Animal(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public abstract void makeSound();//抽象方法，没有通用实现，强制子类给

    public void eat()
    {
        System.out.println(name + "在吃东西");
    }

    public void showInfo()
    {
        System.out.println("我叫" + name + ",今年" + age + "岁");
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }
}
