import java.util.Scanner;
public class FourNumber {
    public static void main(String[] args){
        //使用 Scanner 类从控制台读取用户输入的四位十进制数
        Scanner scanner =new Scanner(System.in);

        System.out.println("请输入一个四位十进制数：");
        int number=scanner.nextInt();

        if(number<100 || number >9999){
            System.out.println("输入错误！");
            return;
        }

        //提取各位数字
        int q = number/1000;
        int b = (number/100)%10;
        int s = (number/10)%10;
        int g = number%10;

        //输出每位数字
        System.out.println("千位："+q);
        System.out.println("百位："+b);
        System.out.println("十位："+s);
        System.out.println("个位："+g);

        //输出逆序数
        int reverse=g*1000+s*100+b*10+q;
        System.out.println("逆序数："+reverse);

        //输出各数字平方和相加的和
        int sumSquare = q*q+b*b+s*s+g*g;
        System.out.println("各位数字平方后相加的和：" + sumSquare);
    }
}
