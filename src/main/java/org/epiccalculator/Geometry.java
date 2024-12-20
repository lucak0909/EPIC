package org.epiccalculator;

// Interface representing a 2D shape
interface Shape2D {
    double Pi = 3.14159265359;  // Constant value for Pi

    // Method to calculate the perimeter of the shape
    double calculatePerimeter();

    // Method to calculate the area of the shape
    double calculateArea();
}

// Interface representing a 3D shape
interface Shape3D {
    double Pi = 3.14159265359;

    // Method to calculate the volume of the shape
    double calculateVolume();

    // Method to calculate the surface area of the shape
    double calculateSurfaceArea();
}

// Class representing a Rectangle, implements Shape2D
class Rectangle implements Shape2D {
    double length;
    double width;

    // Constructor to initialize length and width
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    // Calculate perimeter of the rectangle
    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }

    // Calculate area of the rectangle
    @Override
    public double calculateArea() {
        return length * width;
    }
}

// Class representing a Square, extends Rectangle
class Square extends Rectangle {
    // Constructor to initialize the side of the square
    public Square(double side) {
        super(side, side);
    }

    // Inherit perimeter calculation from Rectangle
    @Override
    public double calculatePerimeter() {
        return super.calculatePerimeter();
    }

    // Inherit area calculation from Rectangle
    @Override
    public double calculateArea() {
        return super.calculateArea();
    }
}


class Triangle implements Shape2D {
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

class Circle implements Shape2D {
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

class Cube implements Shape3D {
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

class rectangularPrism implements Shape3D {
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

class Cylinder implements Shape3D {
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

class Sphere implements Shape3D {
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

class Cone implements Shape3D {
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

public class Geometry extends Main {

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nWhat type of geometry?");
            System.out.println("1. 2D Geometry");
            System.out.println("2. 3D Geometry");
            System.out.println("3. Exit");
            System.out.print(">>> ");

            int mode = input.nextInt();

            switch (mode) {
                case 1:
                    System.out.println("\nWhat shape are you calculating?");
                    System.out.println("1. Rectangle");
                    System.out.println("2. Square");
                    System.out.println("3. Triangle");
                    System.out.println("4. Circle");
                    System.out.print(">>> ");

                    int shape = input.nextInt();

                    switch (shape) {
                        case 1:
                            System.out.println("\nEnter the length and width of the rectangle:");
                            System.out.print("Length: ");
                            double length = input.nextDouble();
                            System.out.print("Width: ");
                            double width = input.nextDouble();

                            Rectangle rectangle = new Rectangle(length, width);
                            System.out.println("Perimeter: " + rectangle.calculatePerimeter());
                            System.out.println("Area: " + rectangle.calculateArea());
                            break;

                        case 2:
                            System.out.println("\nEnter the length of a side of the square:");
                            System.out.print("Length: ");
                            double side = input.nextDouble();

                            Square square = new Square(side);
                            System.out.println("Perimeter: " + square.calculatePerimeter());
                            System.out.println("Area: " + square.calculateArea());
                            break;

                        case 3:
                            System.out.println("\nEnter the lengths of the three sides of the triangle:");
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
                            System.out.println("\nEnter the radius of the circle:");
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
                    System.out.println("\nWhat shape are you calculating?");
                    System.out.println("1. Cube");
                    System.out.println("2. Rectangular Prism");
                    System.out.println("3. Cylinder");
                    System.out.println("4. Sphere");
                    System.out.println("5. Cone");
                    System.out.print(">>> ");

                    int shape3D = input.nextInt();

                    switch (shape3D) {
                        case 1:
                            System.out.println("\nEnter the side length of the cube:");
                            System.out.print("Side: ");
                            double sideCube = input.nextDouble();

                            Cube cube = new Cube(sideCube);
                            System.out.println("Volume: " + cube.calculateVolume());
                            System.out.println("Surface Area: " + cube.calculateSurfaceArea());
                            break;

                        case 2:
                            System.out.println("\nEnter the length, width, and height of the rectangular prism:");
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
                            System.out.println("\nEnter the radius and height of the cylinder:");
                            System.out.print("Radius: ");
                            double radiusCylinder = input.nextDouble();
                            System.out.print("Height: ");
                            double heightCylinder = input.nextDouble();

                            Cylinder cylinder = new Cylinder(radiusCylinder, heightCylinder);
                            System.out.println("Volume: " + cylinder.calculateVolume());
                            System.out.println("Surface Area: " + cylinder.calculateSurfaceArea());
                            break;

                        case 4:
                            System.out.println("\nEnter the radius of the sphere:");
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
                    break;

                case 3:
                    System.out.println("Exiting the program.");
                    System.exit(0);
            }
        }
    }

}
