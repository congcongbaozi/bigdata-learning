package org.example.Day03;

public interface Shape {
    //接口只规定“必须有什么方法”，不写方法体
    double area(); //接口面积，接口方法默认public abstract
    String name(); //返回形状名，方便打印
    //接口里的方法没有花括号，实现类必须补上方法体。接口不能直接 new Shape()，你不能实例化它。
    default String describe()// 你用 default 给接口写一个带方法体的默认实现。实现类可以不重写就直接用它，这是 Java 8 起支持的能力。
    {
        return name() + "的面积 = " + area();//你在默认方法里调用了前面两个抽象方法。编译期编译器只知道「将来一定有这两个方法」，运行期 JVM 会去执行具体实现类里的版本，这个机制叫多态。
    }

}
