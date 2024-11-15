import java.io.*;
import java.net.*;

public class Test02 {
    public static void main(String[] args) throws MalformedURLException {
        URL baidu = new URL("http://www.baidu.com");
        URL url = new URL(baidu, "/index.html?username=tom&password=123#test");
        System.out.println("协议：" + url.getProtocol());
        System.out.println("主机：" + url.getHost());
        System.out.println("端口：" + url.getPort());
        System.out.println("文件路径：" + url.getPath());
        System.out.println("文件名：" + url.getFile());
        System.out.println("相对路径：" + url.getRef());
        System.out.println("查询：" + url.getQuery());
    }
}