// Student.java
abstract class Student {
    protected String name;
    protected String course;
    protected double score;

    public Student(String name, String course, double score) {
        this.name = name;
        this.course = course;
        this.score = score;
    }

    public abstract String getGrade();

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public double getScore() {
        return score;
    }
}

// Undergraduate.java
class Undergraduate extends Student {
    public Undergraduate(String name, String course, double score) {
        super(name, course, score);
    }

    @Override
    public String getGrade() {
        if (score >= 80) return "优秀";
        else if (score >= 70) return "良好";
        else if (score >= 60) return "一般";
        else if (score >= 50) return "及格";
        else return "不及格";
    }
}

// Postgraduate.java
class Postgraduate extends Student {
    public Postgraduate(String name, String course, double score) {
        super(name, course, score);
    }

    @Override
    public String getGrade() {
        if (score >= 90) return "优秀";
        else if (score >= 80) return "良好";
        else if (score >= 70) return "一般";
        else if (score >= 60) return "及格";
        else return "不及格";
    }
}

// HighSchooler.java
class HighSchooler extends Student {
    public HighSchooler(String name, String course, double score) {
        super(name, course, score);
    }

    @Override
    public String getGrade() {
        if (score >= 80) return "好";
        else if (score >= 60) return "中";
        else return "差";
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[5];
        students[0] = new Undergraduate("Alice", "Math", 85);
        students[1] = new Undergraduate("Bob", "Science", 72);
        students[2] = new Postgraduate("Charlie", "Math", 92);
        students[3] = new Postgraduate("Daisy", "Science", 65);
        students[4] = new HighSchooler("Eve", "English", 75);

        System.out.println("全班学生成绩等级：");
        for (Student student : students) {
            System.out.println(student.getName() + " (" + student.getCourse() + "): " + student.getGrade());
        }
    }
}
