package org.example.Day02;

import java.util.Objects;

//两者都重写的"正确示范
class PersonEqHash {
    private int id;
    private String name;
    public PersonEqHash(int id, String name) //构造器，两个参数
    {
        this.id = id; //把参数值存进成员变量
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {   // 参数必须是Object方法，重写父类方法，equals是父类方法
        if (this == o) return true;     // 自己和自己比，直接返回true
        if (!(o instanceof PersonEqHash)) return false;  // 类型不对直接false，o 不是 PersonEqOnly 类型的对象
        return this.id == ((PersonEqHash) o).id;  // L38：强转和取 id 合并成一句，效果与 L19–20 完全相同
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);//Objects.hash(id) 内部会调用 Integer.valueOf(id).hashCode()。这一行保证了 JDK 约定"equals 相等 → hashCode 相等"成立：只要 equals 判相等，参与判等的字段（id）相同，哈希值就相同。
    }
}
