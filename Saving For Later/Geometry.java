import java.util.Scanner;

public static double Pi = 3.14159265359;

public interface Shape2D {
    double calculatePerimeter();

    double calculateArea();
}

public interface Shape3D {
    double calculateVolume();

    double calculateSurfaceArea();
}

public static class Rectangle implements Shape2D {
    double length;
    double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}

public static class Square extends Rectangle {
    public Square(double side) {
        super(side, side);
    }

    @Override
    public double calculatePerimeter() {
        return super.calculatePerimeter();
    }

    @Override
    public double calculateArea() {
        return super.calculateArea();
    }
}

public static class Triangle implements Shape2D {
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculatePerimeter() {
        return a + b + c;
    }

    @Override
    public double calculateArea() { // Heron's formula for height
        double s = calculatePerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

public static class Circle implements Shape2D {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Pi * radius;
    }

    @Override
    public double calculateArea() {
        return Pi * radius * radius;
    }
}

public static class Cube implements Shape3D {
    double side;

    public Cube(double side) {
        this.side = side;
    }

    @Override
    public double calculateVolume() {
        return Math.pow(side, 3);
    }

    @Override
    public double calculateSurfaceArea() {
        return 6 * side * side;
    }
}

public static class rectangularPrism implements Shape3D {
    double length;
    double width;
    double height;

    public rectangularPrism(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateVolume() {
        return length * width * height;
    }

    @Override
    public double calculateSurfaceArea() {
        return 2 * ((length * width) + (length * height) + (width * height));
    }
}

public static class Cylinder implements Shape3D {
    double radius;
    double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double calculateVolume() {
        return Pi * Math.pow(radius, 2) * height;
    }

    @Override
    public double calculateSurfaceArea() {
        return 2 * Pi * radius * (radius + height);
    }
}

public static class Sphere implements Shape3D {
    double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateVolume() {
        return (4.0 / 3.0) * Pi * Math.pow(radius, 3);
    }

    @Override
    public double calculateSurfaceArea() {
        return 4 * Pi * Math.pow(radius, 2);
    }
}

public static class Cone implements Shape3D {
    double radius;
    double height;
    double baseArea;

    public Cone(double radius, double height) {
        this.baseArea = Pi * radius * radius;
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double calculateVolume() {
        return (1.0 / 3.0) * baseArea * height;
    }

    @Override
    public double calculateSurfaceArea() {
        return baseArea + Pi * radius * Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));
    }
}
public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    System.out.println("What type of geometry?");
    System.out.println("1. 2D Geometry");
    System.out.println("2. 3D Geometry");
    System.out.println(">>> ");

    int mode = input.nextInt();

    switch (mode) {
        case 1:
            System.out.println("What shape are you calculating?");
            System.out.println("1. Rectangle");
            System.out.println("2. Square");
            System.out.println("3. Triangle");
            System.out.println("4. Circle");
            System.out.println(">>> ");

            int shape = input.nextInt();

            switch (shape) {
                case 1:
                    System.out.println("Enter the length and width of the rectangle:");
                    System.out.print("Length: ");
                    double length = input.nextDouble();
                    System.out.print("Width: ");
                    double width = input.nextDouble();

                    Rectangle rectangle = new Rectangle(length, width);
                    System.out.println("Perimeter: " + rectangle.calculatePerimeter());
                    System.out.println("Area: " + rectangle.calculateArea());
                    break;

                case 2:
                    System.out.println("Enter the length of a side of the square:");
                    System.out.print("Length: ");
                    double side = input.nextDouble();

                    Square square = new Square(side);
                    System.out.println("Perimeter: " + square.calculatePerimeter());
                    System.out.println("Area: " + square.calculateArea());
                    break;

                case 3:
                    System.out.println("Enter the lengths of the three sides of the triangle:");
                    System.out.print("Side A: ");
                    double sideA = input.nextDouble();
                    System.out.print("Side B: ");
                    double sideB = input.nextDouble();
                    System.out.print("Side C: ");
                    double sideC = input.nextDouble();

                    Triangle triangle = new Triangle(sideA, sideB, sideC);
                    System.out.println("Perimeter: " + triangle.calculatePerimeter());
                    System.out.println("Area: " + triangle.calculateArea());
                    break;

                case 4:
                    System.out.println("Enter the radius of the circle:");
                    System.out.print("Radius: ");
                    double radius = input.nextDouble();

                    Circle circle = new Circle(radius);
                    System.out.println("Perimeter: " + circle.calculatePerimeter());
                    System.out.println("Area: " + circle.calculateArea());
                    break;

                default:
                    System.out.println("Invalid shape selection.");
                    break;

            }
            break;

        case 2:
            System.out.println("What shape are you calculating?");
            System.out.println("1. Cube");
            System.out.println("2. Rectangular Prism");
            System.out.println("3. Cylinder");
            System.out.println("4. Sphere");
            System.out.println("5. Cone");
            System.out.println(">>> ");

            int shape3D = input.nextInt();

            switch (shape3D) {
                case 1:
                    System.out.println("Enter the side length of the cube:");
                    System.out.print("Side: ");
                    double sideCube = input.nextDouble();

                    Cube cube = new Cube(sideCube);
                    System.out.println("Volume: " + cube.calculateVolume());
                    System.out.println("Surface Area: " + cube.calculateSurfaceArea());
                    break;

                case 2:
                    System.out.println("Enter the length, width, and height of the rectangular prism:");
                    System.out.print("Length: ");
                    double length = input.nextDouble();
                    System.out.print("Width: ");
                    double width = input.nextDouble();
                    System.out.print("Height: ");
                    double height = input.nextDouble();

                    rectangularPrism rectPrism = new rectangularPrism(length, width, height);
                    System.out.println("Volume: " + rectPrism.calculateVolume());
                    System.out.println("Surface Area: " + rectPrism.calculateSurfaceArea());
                    break;

                case 3:
                    System.out.println("Enter the radius and height of the cylinder:");
                    System.out.print("Radius: ");
                    double radiusCylinder = input.nextDouble();
                    System.out.print("Height: ");
                    double heightCylinder = input.nextDouble();

                    Cylinder cylinder = new Cylinder(radiusCylinder, heightCylinder);
                    System.out.println("Volume: " + cylinder.calculateVolume());
                    System.out.println("Surface Area: " + cylinder.calculateSurfaceArea());
                    break;

                case 4:
                    System.out.println("Enter the radius of the sphere:");
                    System.out.print("Radius: ");
                    double radiusSphere = input.nextDouble();

                    Sphere sphere = new Sphere(radiusSphere);
                    System.out.println("Volume: " + sphere.calculateVolume());
                    System.out.println("Surface Area: " + sphere.calculateSurfaceArea());
                    break;

                case 5:
                    System.out.println("Enter the base-radius and height of the cone:");
                    System.out.print("Radius: ");
                    double radiusCone = input.nextDouble();
                    System.out.print("Height: ");
                    double heightCone = input.nextDouble();

                    Cone cone = new Cone(radiusCone, heightCone);
                    System.out.println("Volume: " + cone.calculateVolume());
                    System.out.println("Surface Area: " + cone.calculateSurfaceArea());
                    break;

                default:
                    System.out.println("Invalid shape selection.");
                    break;
            }
    }
}

