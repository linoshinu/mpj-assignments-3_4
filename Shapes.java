class Shape {
    double side, length, breadth, base, height;

    // Square
    Shape(double side) {
        this.side = side;
    }

    // Rectangle
    Shape(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    // Triangle
    Shape(double base, double height, int x) {
        this.base = base;
        this.height = height;
    }

    double area() {
        return side * side;
    }

    double area(double l, double b) {
        return l * b;
    }

    double area(double b, double h, int x) {
        return 0.5 * b * h;
    }

    public static void main(String[] args) {
        Shape s1 = new Shape(4);
        System.out.println("Square Area: " + s1.area());

        Shape s2 = new Shape(5, 6);
        System.out.println("Rectangle Area: " + s2.area(5, 6));

        Shape s3 = new Shape(3, 8, 1);
        System.out.println("Triangle Area: " + s3.area(3, 8, 1));
    }
}
