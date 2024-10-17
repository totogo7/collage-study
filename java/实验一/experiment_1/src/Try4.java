public class Try4 {
    public void run(int k) { //求 k 的阶乘
        int y = 1;
        int i;

        for (i = 1; i <= k; i++)
            y = y * i;
        System.out.println(k + "!=" + y);
    }
    public static void main (String[] args){
        Try4 a = new Try4();
        for (int i=1;i<10;i++)
            a.run(i);
    }
}
