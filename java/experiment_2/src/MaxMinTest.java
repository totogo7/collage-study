public class MaxMinTest {
    //最大值
    public static int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static long max(long a, long b, long c) {
        return Math.max(Math.max(a, b), c);
    }


    public static float max(float a, float b, float c) {
        return Math.max(Math.max(a, b), c);
    }

    //最小值
    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }


    public static long min(long a, long b, long c) {
        return Math.min(Math.min(a, b), c);
    }

    public static float min(float a, float b, float c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) {
        // 使用重载的max和min方法
        System.out.println("Max of 3 int numbers: " + max(10, 20, 30));
        System.out.println("Min of 3 int numbers: " + min(10, 20, 30));

        System.out.println("Max of 3 long numbers: " + max(100L, 200L, 300L));
        System.out.println("Min of 3 long numbers: " + min(100L, 200L, 300L));

        System.out.println("Max of 3 float numbers: " + max(1.1f, 2.2f, 3.3f));
        System.out.println("Min of 3 float numbers: " + min(1.1f, 2.2f, 3.3f));
    }
}
