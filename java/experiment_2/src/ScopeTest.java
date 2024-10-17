class ScopeTest {
    int x;
    static int m=0;
    void show( ) {
        int x=8, y;
        for (y=0; y<2; y++) {
            int z=5;
            System.out.println("z = " + z);
            z = 10;
            System.out.println("z = " + z);
            System.out.println("方法域中的 X = " + x + "在块内显示");
        }

        // z = 20;
        System.out.println("方法域中的 X = " + x + "在块外显示");
        System.out.println("类域中的 X = " + this.x + "在块外显示");
        System.out.println("类域中的 m = " + this.m + "在块外显示");
    }

    void setx(int x){
        this.x = x;
    }

    static void setm(int m) {
        ScopeTest.m = m;
    }
    public static void main(String[] args) {
        ScopeTest application = new ScopeTest( );
        application.setx(2);
        application.setm(3);
        application.show( );
    }
}