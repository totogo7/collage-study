public class TestMoveable {
    public static void main(String[] args) {
        // 创建汽车、轮船和飞机的实例
        Car car1 = new Car("汽车 1");
        Car car2 = new Car("汽车 2");
        Ship ship1 = new Ship("轮船 1");
        Aircraft aircraft1 = new Aircraft("飞机 1");

        // 启动线程
        car1.start();
        car2.start();
        ship1.start();
        aircraft1.start();
    }
}
