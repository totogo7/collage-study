public class Aircraft extends Thread implements Moveable {
    private String name;

    public Aircraft(String name) {
        this.name = name; // 通过构造函数传入飞机名称
    }

    @Override
    public void move() {
        System.out.println(name + " 正在天空中飞行。");
    }

    @Override
    public void run() {
        move(); // 调用 move 方法
    }
}
