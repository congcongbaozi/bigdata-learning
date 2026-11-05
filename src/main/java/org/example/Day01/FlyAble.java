package org.example.Day01;
//接口（能力契约）
public interface FlyAble {
    // 默认 public abstract
    void fly();
    // JDK8+ 默认方法，给通用实现
    default void glide()
    {
        System.out.println("借助气流滑翔");
    }
}
