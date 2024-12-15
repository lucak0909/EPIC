package org.epiccalculator;

public class Geometry {

    public double Pi = 3.14159265359;

    public interface Shape2D {
        double calculatePerimeter();
        double calculateArea();
    }

    public interface Shape3D {
        double calculateVolume();
        double calculateSurfaceArea();
    }

    public class Rectangle implements Shape2D {
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

    public class Square extends Rectangle {
        public Square(double side) {
            super(side, side);
        }
    }

    public class Triangle implements Shape2D {
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

    public class Circle implements Shape2D {
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

    public class Cube implements Shape3D {
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

    public class rectangularPrism implements Shape3D {
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

    public class Cylinder implements Shape3D {
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

    public class Sphere implements Shape3D {
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

    public class Cone implements Shape3D {
        double radius;
        double height;
        double baseArea = Pi * radius * radius;

        public Cone(double baseArea, double radius, double height) {
            this.baseArea = baseArea;
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

}
