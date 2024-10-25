public class Car extends Thread implements Moveable {
    private String name;

    public Car(String name) {
        this.name = name; // 通过构造函数传入汽车名称
    }

    @Override
    public void move() {
        System.out.println(name + " 正在公路上行驶。");
    }

    @Override
    public void run() {
        move(); // 调用 move 方法
    }
}
