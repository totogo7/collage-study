public class Ship extends Thread implements Moveable {
    private String name;

    public Ship(String name) {
        this.name = name;
    }

    @Override
    public void move() {
        System.out.println(name + " is sailing on the sea.");
    }

    @Override
    public void run() {
        move();
    }
}

