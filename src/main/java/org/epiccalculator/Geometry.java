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
        private double length;
        private double width;

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
        private double a;
        private double b;
        private double c;

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
        private double radius;

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
}
