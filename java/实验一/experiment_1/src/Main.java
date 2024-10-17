public class Main {
    // 线程类，用于计算偶数或奇数的累加和
    static class SumThread extends Thread {
        private boolean isEven;  // 标志，决定计算偶数还是奇数
        private int sum = 0;     // 用于存储计算结果

        // 构造函数，设置偶数或奇数的标志
        public SumThread(boolean isEven) {
            this.isEven = isEven;
        }

        // 线程的执行逻辑
        public void run() {
            for (int i = 0; i <= 100; i++) {
                if (isEven && i % 2 == 0) {
                    sum += i;  // 累加偶数
                } else if (!isEven && i % 2 != 0) {
                    sum += i;  // 累加奇数
                }
            }
        }

        // 获取计算的结果
        public int getSum() {
            return sum;
        }
    }

    public static void main(String[] args) {
        // 创建用于计算偶数和奇数和的线程
        SumThread evenThread = new SumThread(true);
        SumThread oddThread = new SumThread(false);

        // 启动两个线程
        evenThread.start();
        oddThread.start();

        try {
            // 等待两个线程完成
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 使用两个线程的结果计算总和
        int totalSum = evenThread.getSum() + oddThread.getSum();

        // 输出结果
        System.out.println("0到100的偶数累加和: " + evenThread.getSum());
        System.out.println("0到100的奇数累加和: " + oddThread.getSum());
        System.out.println("0到100的总和: " + totalSum);
    }
}
