public class MaxMin {
    public static void main(String[] args){
        int a = 40;
        int b = 20;
        int c = 5;
        int d = 15;

        int max = (a > b ? a : b) > (c > d ? c : d) ? (a > b ? a : b) : (c > d ? c : d);
        int min = (a < b ? a : b) < (c < d ? c : d) ? (a < b ? a : b) : (c < d ? c : d);

        System.out.println("最大值："+max);
        System.out.println("最小值："+min);
    }
}
