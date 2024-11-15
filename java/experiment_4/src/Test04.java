import java.io.*;
import java.net.*;
import java.util.*;

public class Test04 {
    public static void main(String[] args) {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                System.out.println("网卡：" + ni.getDisplayName());
                System.out.println("名称: " + ni.getName());
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    System.out.println("ip: " + ips.nextElement().getHostAddress());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
