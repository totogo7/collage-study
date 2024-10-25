public class MyThread extends Thread {
    private long delay; // 延迟时间

    // 构造方法，接收线程名称和延迟时间
    public MyThread(String str, long delay) {
        super(str); // 调用父类 Thread 的构造方法
        this.delay = delay; // 初始化 delay
    }

    @Override
    public void run() {
        // 循环三次
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " 第 " + i + " 次运行");
            try {
                Thread.sleep(delay); // 线程休眠
            } catch (InterruptedException e) {
                System.err.println(getName() + " 被中断: " + e.getMessage());
            }
        }
        System.out.println(getName() + " 结束"); // 线程结束信息
    }
}
