public class TestThread {
    public static void main(String[] args) {
        // 创建三个 MyThread 对象
        MyThread t1 = new MyThread("线程 A", 1000);
        MyThread t2 = new MyThread("线程 B", 2000);
        MyThread t3 = new MyThread("线程 C", 3000);

        // 启动线程
        t1.start();
        t2.start();
        t3.start();

        // 输出当前活动线程的数目
        System.out.println("当前活动线程的数目: " + Thread.activeCount());
    }
}
