class Car {
    private String name; //品牌
    private String color;  //颜色
    private double weight;  //自重
    private int passenger;  //搭乘人数

    // 构造方法
    public Car(String name, String color, double weight, int passenger) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.passenger = passenger;
    }

    // 显示信息方法
    public void displayInfo() {
        System.out.println("品牌: " + name);
        System.out.println("颜色: " + color);
        System.out.println("自重: " + weight + " 公斤");
        System.out.println("搭乘人数: " + passenger + " 人");
    }

    public static void main(String[] args) {
        Car honda = new Car("本田", "黑色", 1500, 5);
        honda.displayInfo();
    }
}

