package org.example.Day03;
//第二个实现类矩形
public class Rectangle implements Shape{
    private double width;
    private double length;

    public Rectangle(double width, double length) throws IllegalAccessException {
        if(width <= 0 || length <= 0)
        {
            throw new IllegalAccessException("宽和高必须高于0");
        }
        this.width = width;
        this.length = length;
    }


    @Override
    public double area() {
        return width * length;
    }

    @Override
    public String name() {
        return "矩形（" + width + " x " + length +" ）";
    }
}
