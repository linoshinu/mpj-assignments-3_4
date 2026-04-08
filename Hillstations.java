class Hillstations {
    void famousfood() {
        System.out.println("General hill station food");
    }
    void famousfor() {
        System.out.println("General hill station attraction");
    }

    public static void main(String[] args) {
        Hillstations h;

        h = new Manali();
        h.famousfood();
        h.famousfor();

        h = new Darjeeling();
        h.famousfood();
        h.famousfor();

        h = new Ooty();
        h.famousfood();
        h.famousfor();
    }
}

class Manali extends Hillstations {
    void famousfood() {
        System.out.println("Manali: Siddu and Babru");
    }
    void famousfor() {
        System.out.println("Manali: Snow and adventure sports");
    }
}

class Darjeeling extends Hillstations {
    void famousfood() {
        System.out.println("Darjeeling: Momos and Thukpa");
    }
    void famousfor() {
        System.out.println("Darjeeling: Tea gardens and sunrise");
    }
}

class Ooty extends Hillstations {
    void famousfood() {
        System.out.println("Ooty: Varkey and homemade chocolate");
    }
    void famousfor() {
        System.out.println("Ooty: Nilgiri hills and toy train");
    }
}
