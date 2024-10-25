import java.io.*;

public class FileReadWriteExample {
    public static void main(String[] args) {
        // 定义要写入的文件名
        String fileName = "data.txt";

        // 写入数据到文件
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fileName))) {
            // 写入 int 类型数据
            dataOutputStream.writeInt(42);
            // 写入 double 类型数据
            dataOutputStream.writeDouble(3.14159);
            // 写入字符串
            dataOutputStream.writeUTF("Hello, World!");

            System.out.println("数据已成功写入文件 " + fileName);
        } catch (IOException e) {
            System.err.println("写入文件时发生错误: " + e.getMessage());
        }

        // 读取数据从文件
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(fileName))) {
            // 读取 int 类型数据
            int intValue = dataInputStream.readInt();
            // 读取 double 类型数据
            double doubleValue = dataInputStream.readDouble();
            // 读取字符串
            String stringValue = dataInputStream.readUTF();

            // 显示读取的数据
            System.out.println("读取的数据：");
            System.out.println("int: " + intValue);
            System.out.println("double: " + doubleValue);
            System.out.println("String: " + stringValue);
        } catch (IOException e) {
            System.err.println("读取文件时发生错误: " + e.getMessage());
        }
    }
}
