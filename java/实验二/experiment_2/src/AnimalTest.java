// 定义接口 Jumpping
interface Jumpping {
    void jump();
}

// 定义抽象类 Animal
abstract class Animal {
    private String name;
    private int age;

    //无参构造方法
    public Animal() {}

    //构造方法
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //获取name的值，返回值是string
    public String getName() {
        return name;
    }

    //设置name的值，传参然后赋值
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 抽象方法
    public abstract void eat();
}

// 具体类 Cat 实现
class Cat extends Animal implements Jumpping {
    public Cat() {}

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " 吃鱼");
    }

    @Override
    public void jump() {
        System.out.println(getName() + " 跳得很高");
    }
}

// 具体类 Dog 实现
class Dog extends Animal implements Jumpping {
    public Dog() {}

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " 吃骨头");
    }

    @Override
    public void jump() {
        System.out.println(getName() + " 跳得不高");
    }
}

public class AnimalTest {
    public static void main(String[] args) {
        // 创建一个包含50只动物的数组
        Animal[] animals = new Animal[50];

        // 将猫和狗的对象添加到数组中
        for (int i = 0; i < 25; i++) {
            animals[i] = new Cat("小猫" + (i+1), i + 2);
        }

        for (int i = 25; i < 50; i++) {
            animals[i] = new Dog("小狗" + (i-24), i + 3);
        }

        // 遍历数组，分别投喂食物并表演跳高
        for (Animal animal : animals) {
            animal.eat();
            if (animal instanceof Jumpping) {
                ((Jumpping) animal).jump();
            }
            System.out.println("----------------------------");
        }
    }
}