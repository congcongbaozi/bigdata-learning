package org.example.Day03;
//第一个实现类圆形
public class Circle implements Shape{//你用 implements 承诺「本类遵守 Shape 契约」。编译器随即检查：接口里的 area() 和 name() 你有没有都实现

    private double radius;//半径字段设为 private，外部代码直接改不到它；final 要求它只能在构造方法里赋值一次。这两个修饰符合起来叫封装。

    public Circle(double radius) throws IllegalAccessException {//定义构造方法，new Circle(2.0) 时 JVM 执行它。

        if (radius <= 0)
        {
            // IllegalArgumentException 属于运行时异常，编译器不强制你处理
            //主动抛出一个异常对象，阻止非法对象被创建。IllegalArgumentException 继承自 RuntimeException，属于非受检异常，编译器不强制调用方写 catch。这一行正好把今天的接口与异常两个主题接上了。
            throw new IllegalAccessException("半径必须大于0，收到：" + radius);
        }
        this.radius = radius;//你把参数值存进字段。this.radius 指字段，右侧 radius 指参数，同名时 this 用来区分。
    }

    @Override
    public double area() {//你实现接口方法。访问修饰符必须是 public，因为接口里的方法默认公开，实现类不能把权限收窄。
        return Math.PI * radius * radius;
    }

    @Override
    public String name() {//你实现第二个接口方法，返回带半径的描述字符串。
        return "圆形（r = " + radius + " ）";
    }
    // 字段用 private 是封装，外部只能靠 area() 拿结果。
    //describe() 你在这两个类里都没有重写，但它照样能用。这就是接口默认方法的价值。
}
