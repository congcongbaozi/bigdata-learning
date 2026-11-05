package org.example.Day02;

public class SyntaxDemo {
    //Java 语法细节验证
    public static void main(String[] args) {

        System.out.println("----------实验1：String / StringBuilder / StringBuffer---------");
        String s = "a";//String 是不可变
        s += "b";//"a" 没有变，程序新建了一个内容为 "ab" 的字符串对象，然后让变量 s 改指向这个新对象。原来那个 "a" 就成了没人用的"垃圾"，等着垃圾回收器清理。

        StringBuilder sb = new StringBuilder("a");//new 关键字在堆内存里创建了一个 StringBuilder 对象，是可变的
        sb.append("b");//这行把 "b" 追加到 sb 现有内容后面，sb 的内容变成 "ab"，但还是同一个对象，内存地址没有变

        StringBuffer sbf = new StringBuffer("a");//StringBuffer 的方法是加了 synchronized（同步锁）的。同步锁的含义是：同一时刻只允许一个线程执行这个方法，其他线程必须排队。这让它成为线程安全的
        sbf.append("b");

        System.out.println(s + " | " + sb + " | " + sbf);
        //单线程场景用 StringBuilder，多线程共享同一个拼接对象时才需要 StringBuffer。String 拼接在循环里会疯狂产生垃圾对象，禁用。

        System.out.println("----------实验2：实验2：== 与 equals---------");
        String x = "hello";  // 字面量 → 常量池，"hello" 这种直接写在代码里的字符串叫字面量。Java 会把字面量放进一个叫"字符串常量池"的缓存区。如果池子里已经有 "hello"，就直接复用它，不再新建。
        String y = "hello";//JVM 去常量池找 "hello"——发现已经有了（就是 x 指的那个），于是让 y 指向同一个对象。此时 x 和 y 是两个不同的变量名，但它们指向内存里同一个东西。
        String z = new String("hello");// 强制堆上新对象
        System.out.println(x == y);     // true：引用同一对象
        System.out.println(x == z);     // false：两个不同对象
        System.out.println(x.equals(z));// true：内容相等
        //自定义类不重写 equals 时，equals 默认等价于 ==。这是因为所有类都继承自 Object 基类，Object 的 equals 方法实现就是直接用 == 比地址；String 之所以能比内容，是因为它自己重写了这个方法。你的 equals-hashcode 目录里的实验练的正是这件事。
//== 与 equals：== 比内存地址，equals 比内容（前提是类重写了它，没重写就退化成比地址）。


        System.out.println("----------实验3：Integer 缓存 -128~127---------");
        Integer i1 = 127, i2 = 127;//声明了两个变量。Integer 是 int 的包装类——int 是基本类型（就是一个数字本体），而 Integer 是把数字包成对象的"盒子"。把基本类型赋给包装类变量时，Java 会自动做一次装箱（autoboxing），实际执行的是 Integer.valueOf(127)。
        //关键来了：Integer.valueOf 这个方法对 -128 到 127 范围内的数字做了缓存——池子里预先放好了这 256 个 Integer 对象，谁要就复用同一个。所以 i1 和 i2 装箱后拿到的是同一个对象。
        Integer i3 = 128, i4 = 128;//128 超出了缓存范围，valueOf 没有现成对象可给，于是 i3 和 i4 各自 new 出一个全新的 Integer 对象。两个对象地址不同。
        System.out.println(i1 == i2); // true：127 在缓存范围内，返回同一对象，i1 和 i2 装箱后指向缓存池里同一个 Integer 对象，地址相同，== 返回 true。
        System.out.println(i3 == i4); // false：128 超出缓存，各自 new 新对象
//这个实验的坑在于：同样的写法，数字换了结果就变。如果两段代码分别用 127 和 128 测试，你会得出完全矛盾的结论。所以正确做法只有一个——比较包装类型一律用 .equals()（它比的是数值内容），
// 或者先调用 intValue() 拆箱成基本类型 int，再用 == 直接比数字。笔记里提到的缓存上限默认是 127，这个默认值写在 Integer 类的 valueOf 方法实现里；
// JDK 也允许通过系统参数在特定范围内调整上限，但日常编码不应该依赖这一点。
//包装类比较：Integer 缓存 -128~127，范围内 == 碰巧为 true，范围外为 false，所以比较数值必须用 .equals()

        System.out.println(i1.equals(i2));// true
        System.out.println(i3.equals(i4));// true
        System.out.println(i1.equals(i3));//false
        System.out.println(i1.intValue() == i3.intValue());//false（拆箱后比数字本体）
    }
}
