package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("-------1.继承+方法重写--------");
        Dog dog = new Dog("旺财" , 3);
        Cat cat = new Cat("咪咪" , 2);
        dog.makeSound();
        cat.makeSound();
        dog.eat();
        cat.eat();

        System.out.println("--------2.调用父类---------");
        dog.showInfo();

        System.out.println("---------3.向上转型 + 多态-------");
        Animal a1 = new Dog("大黄", 5);//向上转型：子类对象赋给父类使用，自动安全
        Animal a2 = new Cat("雪球" , 1);
        a1.makeSound();// 多态：编译看左(Animal)，运行看右(真实对象)
        a2.makeSound();

        System.out.println("-----------4.接口 vs 继承对比----------");
        Bird bird = new Bird("小燕", 2);
        bird.makeSound();
        FlyAble f = bird;//// 同一对象也能当 Flyable 用
        f.fly();
        f.glide();

        System.out.println("-------5.类型判断与向下转型----------");
        if (a1 instanceof Dog) // 向下转型前先判断类型
        {
            ((Dog) a1).fetch(); // 调子类独有方法必须强转，且先 instanceof
        }
    }
}
