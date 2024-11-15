import java.io.*;
import java.net.*;

public class Test03 {
    public static void main(String[] args) throws IOException {
        test01();
        test02();
    }

    // 读取网页资源
    public static void test01() throws IOException {
        URL url = new URL("http://www.ahjzu.edu.cn");
        InputStream is = url.openStream(); // 获取 URL 对象所表示的资源的输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); // 将字节输入流转换为字符输入流
        String str = br.readLine();
        while (str != null) {
            System.out.println(str);
            str = br.readLine();
        }
        br.close();
        is.close();
    }

    // 读取 FTP 上的文件资源
    public static void test02() throws IOException {
        URL url = new URL("ftp://wbs14061:abc@172.16.1.11/资料/exe2.txt");
        System.out.println("协议：" + url.getProtocol());
        System.out.println("主机：" + url.getHost());
        System.out.println("端口：" + url.getPort());
        System.out.println("文件路径：" + url.getPath());
        System.out.println("文件名：" + url.getFile());
        System.out.println("相对路径：" + url.getRef());
        System.out.println("查询：" + url.getQuery());
        InputStream is = url.openStream(); // 获取 URL 对象所表示的资源的输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); // 将字节输入流转换为字符输入流
        String str = br.readLine();
        while (str != null) {
            System.out.println(str);
            str = br.readLine();
        }
        br.close();
        is.close();
    }
}
