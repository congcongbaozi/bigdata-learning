package org.example.Day02;

//只重写 equals 的"错误示范"
class PersonEqOnly {//没写 public，包内可见；这是刻意做出错的对照组

    private int id;
    private String name;
    public PersonEqOnly(int id, String name) //构造器，两个参数
    {
        this.id = id; //把参数值存进成员变量
        this.name = name;
    }

    @Override //注解
    public boolean equals(Object o) //参数必须是Object方法，重写父类方法，equals是父类方法
    {
        if (this == o) return true;//自己和自己比，直接返回true
        if (!(o instanceof PersonEqOnly)) return false;//类型不对直接false，o 不是 PersonEqOnly 类型的对象
        PersonEqOnly p = (PersonEqOnly) o;//能够强制类型转换
        return this.id == p.id;//只按id判别相等
    }
    // L22：注意：没有重写 hashCode() —— 这就是 bug 的源头
}
