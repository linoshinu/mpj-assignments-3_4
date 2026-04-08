class HillStation {
    void location() {
        System.out.println("Hill station location");
    }

    void climate() {
        System.out.println("Cool climate");
    }
}

class Shimla extends HillStation {
    void location() {
        System.out.println("Shimla is in Himachal Pradesh");
    }

    void climate() {
        System.out.println("Shimla has cold winters");
    }
}

class Munnar extends HillStation {
    void location() {
        System.out.println("Munnar is in Kerala");
    }

    void climate() {
        System.out.println("Munnar has pleasant climate");
    }
}

class Mussoorie extends HillStation {
    void location() {
        System.out.println("Mussoorie is in Uttarakhand");
    }

    void climate() {
        System.out.println("Mussoorie is known as Queen of Hills");
    }
}

public class TestHill {
    public static void main(String[] args) {
        HillStation h;

        h = new Shimla();
        h.location();
        h.climate();

        h = new Munnar();
        h.location();
        h.climate();

        h = new Mussoorie();
        h.location();
        h.climate();
    }
}
