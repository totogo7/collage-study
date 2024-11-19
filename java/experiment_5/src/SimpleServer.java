import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        System.out.println("======server========");
        ServerSocket ss = null;
        Socket socket = null;
        try {
            ss = new ServerSocket(8002); // 创建服务器套接字
            socket = ss.accept(); // 等待客户端连接

            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));

            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(os));

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String str;
            while (true) {
                str = dis.readUTF(); // 接收客户端消息
                System.out.println("对方说：" + str);
                if (str.equals("bye")) {
                    System.out.println("对方说再见，聊天结束！");
                    break;
                }

                str = br.readLine(); // 从控制台读取输入
                dos.writeUTF(str); // 发送消息给客户端
                dos.flush();
                if (str.equals("bye")) {
                    System.out.println("自己说再见，聊天结束！");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("closing...");
            try {
                if (socket != null) {
                    socket.close(); // 关闭连接
                }
                if (ss != null) {
                    ss.close(); // 关闭服务器套接字
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
