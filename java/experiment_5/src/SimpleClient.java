import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        System.out.println("=========client===============");
        Socket socket = null; // 声明 Socket 对象
        try {
            socket = new Socket("127.0.0.1", 8002); // 连接到服务器

            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));

            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(os));

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String str;
            while (true) {
                str = br.readLine(); // 从控制台读取输入
                dos.writeUTF(str); // 发送消息给服务器
                dos.flush();
                if (str.equals("bye")) {
                    System.out.println("自己说再见，聊天结束！");
                    break;
                }

                str = dis.readUTF(); // 接收服务器消息
                System.out.println("对方说：" + str);
                if (str.equals("bye")) {
                    System.out.println("对方说再见，聊天结束！");
                    break;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("closing...");
            try {
                if (socket != null) {
                    socket.close(); // 关闭连接
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
