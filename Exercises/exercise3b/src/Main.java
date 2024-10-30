import java.io.*;

abstract class Shape implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
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

    @Override
    public String toString() {
        return "Triangle : Area = " + calculateArea() + ", Perimeter = " + calculatePerimeter() + ", Color = " + Shape.getColor() ;
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
    @Override
    public String toString() {
        return "Rectangle : Area = " + calculateArea() + ", Perimeter = " + calculatePerimeter() + ", Color = " + Shape.getColor() ;
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
    @Override
    public String toString() {
        return "Circle : Area = " + calculateArea() + ", Perimeter = " + calculatePerimeter() + ", Color = " + Shape.getColor() ;
    }

}

class Square extends Rectangle{
    public Square(double sideLength){
        super(sideLength,sideLength);
    }
    @Override
    public String toString() {
        return "Square : Area = " + calculateArea() + ", Perimeter = " + calculatePerimeter() + ", Color = " + Shape.getColor() ;
    }

}


public class Main {
    public static void main(String[] args){
        Shape[] shapes = {
                new Triangle(3,4, 5),
                new Rectangle(5,6),
                new Circle(3),
                new Square(4)

        };

        //Serialize the objects
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("shapes.ser"))){
            for(Shape shape : shapes){
                out.writeObject(shape);
            }
            System.out.printf("Serialized data is saved in shapes.ser%n");
        }catch (IOException e){
            e.printStackTrace();
        }

        //Deserialize the objects
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("shapes.ser"))) {
            for (int i =0; i< shapes.length; i++) {
                shapes[i] = (Shape) in.readObject();
            }

            for (Shape shape : shapes) {
                System.out.println(shape);
            }


        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }

    }
}