package org.example.Day03;

import java.util.Arrays;
import java.util.List;
//你在这里写 <T extends Shape> 泛型方法，并运行主程序。
public class InterfaceDemo {//你声明主类。文件名必须叫 InterfaceDemo.java，且与类名一致，否则 javac 报错。
    //<T extend Shape> 读作 ：T 可以是 Shape、也可以是任何implements Shape的类
    public static <T extends Shape> double calcAndPrint(T shape)
    //- public static 表示这是公开的类方法，你不用先 new InterfaceDemo() 就能调用。
    //声明一个类型参数 T，并用 extends 给它加上界：T 只能是 Shape 本身或 Shape 的实现类。尖括号写在返回类型之前，这是泛型方法的固定语法。
    //T shape是形参，它的具体类型由你调用时传入的实参决定。你传 Circle，T 就是 Circle；你传 Rectangle，T 就是 Rectangle。
    {
        double area = shape.area();//你能在这里调用 area()，全靠 <T extends Shape> 那个上界。如果你把上界去掉写成 <T>，编译器只知道 T 是 Object，它会拒绝这一行。
        System.out.println(shape.describe() + "【真实类型" + shape.getClass().getSimpleName() + "】");
        //你打印默认方法 describe() 的结果，再拼上 getClass().getSimpleName()。后者返回对象运行时的真实类名，用来证明「同一个方法这次处理的是不同实现类」。
        return area;//你把面积返回给调用方
    }

    public static <T extends Shape> double sumArea(List<T> shapes)//1. 你把同一个 T 用在 List<T> 上。这里有个关键约束：一次调用里 T 只能是一种类型，所以这个列表不能混放 Circle 和 Rectangle。
    {
        double sum = 0.0;//初始化累加器，为0.0
        for (T s:shapes)//你用增强 for 循环遍历列表，循环变量类型就是 T
        {
            sum += s.area();//你累加每个图形的面积。
        }
        return sum;
    }


    public static void main(String[] args) throws IllegalAccessException {
        Circle c = new Circle(2.0);//造一个半径为 2 的圆
        Rectangle r = new Rectangle(3.0, 4.0);//造一个 3×4 的矩形
        calcAndPrint(c);// 你第一次调用，编译器推断 T = Circle。
        calcAndPrint(r);//你第二次调用，编译器推断 T = Rectangle。同一个方法，两种类型，这就是泛型方法省代码的地方。

        List<Circle> circles = Arrays.asList(new Circle(1.0), new Circle(2.0));//你造一组全部是 Circle 的列表。
        List<Rectangle> rectangles = Arrays.asList(new Rectangle(2.0, 3.0), new Rectangle(1.0, 1.0));//你再造一组全部是 Rectangle 的列表
        System.out.println("一组圆的面积合计" + sumArea(circles));//你调用 sumArea，这次 T = Circle
        System.out.println("一组矩形的面积合计" + sumArea(rectangles));//你调用 sumArea，这次 T = Rectangle。

        List<Shape> mixed = Arrays.asList(c, r);//你想混放不同图形，就把泛型直接写成接口类型 Shape。此时列表里两个元素都被当作 Shape 看待。
        System.out.println("混合图形的面积合计" + sumArea(mixed));//你调用 sumArea 时 T = Shape。Shape 自身满足 T extends Shape 这个上界，所以我实测这一行编译通过。
        //calcAndPrint("我不是图形"); //—— 你保留这行注释作为反面教材。取消注释后 javac 会报错，因为 String 没有实现 Shape，它不满足上界。
        //你需要分清两个概念：T extends Shape 里的 extends 是给类型参数设上界；class Circle implements Shape 里的 implements 是类去实现接口。两个关键字长得像，作用对象完全不同。
    }
}
