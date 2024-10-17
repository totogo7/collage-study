public class Sum{
    public static void main(String[] args){
        int sumwhile=0;
        int i=100;
        while(i<=500)
        {
            sumwhile+=i;
            i++;
        }
        System.out.println("using while: Sum= " + sumwhile);

        int sumdowhile=0;
        int j=100;
        do{
            sumdowhile+=j;
            j++;
        }while(j<=500);
        System.out.println("using dowhile:sum=" + sumdowhile);

        int sumfor=0;
        for(int k=100;k<=500;k++)
            sumfor+=k;
        System.out.println("usng for:sum="+sumfor);
    }
}