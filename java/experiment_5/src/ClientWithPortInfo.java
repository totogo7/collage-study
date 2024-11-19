import java.io.*;
import java.net.*;

public class ClientWithPortInfo {
    public static void main(String[] args) {
        System.out.println("====== Client ======");
        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1", 64825); // 替换为服务器运行时的端口号
            System.out.println("客户端连接到服务器端口：" + socket.getPort());
            System.out.println("客户端本地端口：" + socket.getLocalPort());

            // 简单通信示例
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverResponse = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.print("输入消息：");
            String message = br.readLine();
            pw.println(message);

            String response = serverResponse.readLine();
            System.out.println("收到服务器消息：" + response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
