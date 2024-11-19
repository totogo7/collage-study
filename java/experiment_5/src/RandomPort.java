import java.net.ServerSocket;

public class RandomPort {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(0);
        System.out.println("监听的端口为：" + serverSocket.getLocalPort());
    }
}