import java.util.Calendar;
//导入 Calendar 类：用于获取当前系统年份。

public class LeapYear {
    public static void main(String[] args){
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if((year % 4 ==0 && year % 100 !=0)||(year%400==0))
            System.out.println(year+"是闰年");
        else
            System.out.println("year"+"不是闰年");
    }
}
