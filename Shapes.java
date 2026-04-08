class Shapes {
    double radius, length, width, base, height;

    Shapes(double radius) {
        this.radius = radius;
    }

    Shapes(double length, double width) {
        this.length = length;
        this.width = width;
    }

    Shapes(double base, double height, boolean isTriangle) {
        this.base = base;
        this.height = height;
    }

    double area() {
        return 3.14 * radius * radius;
    }

    double area(double length, double width) {
        return length * width;
    }

    double area(double base, double height, boolean isTriangle) {
        return 0.5 * base * height;
    }

    public static void main(String[] args) {
        Shapes s1 = new Shapes(7);
        System.out.println("Circle Area: " + s1.area());

        Shapes s2 = new Shapes(4, 5);
        System.out.println("Rectangle Area: " + s2.area(4, 5));

        Shapes s3 = new Shapes(6, 8, true);
        System.out.println("Triangle Area: " + s3.area(6, 8, true));
    }
}
