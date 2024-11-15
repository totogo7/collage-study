import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Test01 {
    public static void main(String[] args) throws UnknownHostException {
        // 获取本机的 InetAddress 实例
        System.out.println("*******获取本机的 InetAddress 实例**********");
        InetAddress address1 = InetAddress.getLocalHost();
        System.out.println("主机名：" + address1.getHostName());
        System.out.println("IP 地址：" + address1.getHostAddress());
        byte[] bytes = address1.getAddress();
        System.out.println("字节数组形式的 IP 地址：" + Arrays.toString(bytes)); // 超过 127 则为负数，加 256
        System.out.println(address1);

        // 根据主机名获取指定主机的 InetAddress 实例
        System.out.println("*******根据主机名获取指定主机的 InetAddress 实例**********");
        InetAddress address2 = InetAddress.getByName("5.8.6.45");
        System.out.println(address2.getHostName());
        System.out.println(address2);

        // 根据字节数组形式的 IP 地址来获取 InetAddress 实例
        System.out.println("**********根据字节数组形式的 IP 地址来获取 InetAddress 实例***********");
        byte[] bytes2 = {(byte) 192, (byte) 168, 6, 8};
        InetAddress address3 = InetAddress.getByAddress(bytes2);
        System.out.println(address3.getHostName());
        System.out.println(address3);
    }
}