package org.example.Day02;

import java.util.HashSet;
import java.util.Set;

public class EqualsHashCodeDemo {
    //面试题：重写 equals() 时为什么必须重写 hashCode()？
    //Object 的通用约定规定——equals 相等的两个对象，hashCode 必须相等。
    //只重写 equals 会破坏这条约定，导致 HashMap / HashSet 把"逻辑相等"的对象散列到不同桶里，
    //出现「存进去取不出来」「去重失效」等诡异 bug。本实验用 HashSet 把这条约定跑给你看。

    //equals()判断两个对象在逻辑上是否相同
    //Java 里每个类都从 Object 继承了默认的 equals，它的实现就是 this == o——比较内存地址。
    // 所以两次 new PersonEqOnly(1, "A") 出来的两个对象，在默认 equals 眼里永远是"不相等"的
    // ，哪怕字段一模一样。你在实验里重写它，等于告诉 Java："别看地址，看 id——id 相同就算同一个人"。
    //
    //hashCode：把对象压缩成一个 int 编号（"站哪个桶"）
    //它默认也是基于内存地址算的。哈希表类集合（HashMap / HashSet）存东西时不挨个比较，而是先用 hashCode
    // 快速定位到一个"桶"（bucket），再在桶内用 equals 精确比对。可以把它理解成图书馆按编号分区放书：
    // hashCode 决定书进哪个区，equals 决定这个区里哪本是"同一本"。
    //
    //为什么成对重写？
    //JDK 的通用约定是：equals 相等的两个对象，hashCode 必须相等（反过来的"hashCode 相等则 equals 相等"不要求）。
    // 你的案例 1 恰好破坏了这条约定——equals 说"都是 id=1 的同一个人"，但 hashCode 还是按内存地址算，
    // 两个对象各拿一个不同的哈希值：
    public static void main(String[] args)
    {
        Set<PersonEqOnly> set1 = new HashSet<>();  // L51：左边声明为 Set 接口，右边 <> 钻石操作符让编译器自动推断泛型为 PersonEqOnly
        set1.add(new PersonEqOnly(1, "A"));  // L52：第一次 add，HashSet 算哈希、选桶、存入，返回 true
        set1.add(new PersonEqOnly(1, "A"));  // L53：第二次 add，两个对象逻辑相等但哈希不同 → 进了另一个桶 → HashSet 不认为重复 → 又存了一份
        System.out.println("案例1 只重写equals不重写hashCode -> set.size = " + set1.size() + "  (期望1，实际2=BUG)");


        Set<PersonEqHash> set2 = new HashSet<>();  // L56：第二组实验，换正确的类
        set2.add(new PersonEqHash(1, "A"));        // L57：第一个对象入桶
        set2.add(new PersonEqHash(1, "A"));        // L58：第二个对象算出相同哈希 → 同一个桶 → 桶内 equals 命中 → add 拒收并返回 false
        System.out.println("案例2 equals+hashCode都重写   -> set.size = " + set2.size() + "  (期望1，正确)");
    }

}
