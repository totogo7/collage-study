import java.io.*;
import java.net.*;

public class ServerWithPortInfo {
    public static void main(String[] args) {
        System.out.println("====== Server ======");
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(0); // 监听随机端口
            System.out.println("服务器监听的端口为：" + serverSocket.getLocalPort());

            clientSocket = serverSocket.accept(); // 等待客户端连接
            System.out.println("客户端的端口为：" + clientSocket.getPort());

            // 简单通信示例
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);

            String message = br.readLine();
            System.out.println("收到客户端消息：" + message);
            pw.println("服务端收到：" + message);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientSocket != null) clientSocket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
