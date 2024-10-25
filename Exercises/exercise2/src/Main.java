abstract class Shape{
    protected  static String color = "Green";

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public  static  void setColor(String color){
        Shape.color = color;
    }

    public static String getColor(){
        return  color;
    }

}

class Triangle extends Shape{
    private double a,b,c; //the three side lengths of the triangle
    private double s ; //semi-perimeter

    public Triangle(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;

        s = 0.5 * (a + b + c);
    }

    @Override
    public double calculateArea(){
        // Heron's formula
        return Math.sqrt(s * (s-a) * (s-b) * (s-c));
    }

    @Override
    public double calculatePerimeter(){
        return a + b + c;
    }

}

class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea(){
        return width * height;
    }

    @Override
    public double calculatePerimeter(){
        return 2 * (width + height);
    }
}

class Circle extends Shape{

    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public double calculateArea(){
        return Math.PI * radius * radius ;
    }

    @Override
    public double calculatePerimeter(){
        return 2 * radius * Math.PI;
    }

}

class Square extends Rectangle{
    public Square(double sideLength){
        super(sideLength,sideLength);
    }
}


public class Main {
    public static void main(String[] args){
        Shape[] shapes = {
                new Triangle(3, 4, 5),
                new Rectangle(5,6),
                new Circle(3),
                new Square(5)

        };

        for (Shape shape : shapes){
            System.out.println(shape.getClass().getSimpleName()+":");
            System.out.println("The area is " + shape.calculateArea());
            System.out.println("The perimeter is " +  shape.calculatePerimeter());
            System.out.println("The color is " + Shape.getColor());
            System.out.println();
        }

    }
}