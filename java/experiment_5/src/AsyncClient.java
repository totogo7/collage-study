import java.io.*;
import java.net.*;

public class AsyncClient {
    public static void main(String[] args) {
        System.out.println("Client starting...");
        try (Socket socket = new Socket("127.0.0.1", 8003)) {
            System.out.println("Connected to server...");

            // 标志位
            final boolean[] isRunning = {true};

            // 接收线程
            Thread receiver = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter fileOut = new BufferedWriter(new FileWriter("client_received.txt", true))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Server: " + line);
                        fileOut.write("Server: " + line); // 写入到文件
                        fileOut.newLine();
                        fileOut.flush(); // 确保内容被写入文件
                        if ("bye".equalsIgnoreCase(line)) {
                            System.out.println("Server closed the connection.");
                            isRunning[0] = false; // 通知退出
                            break;
                        }
                    }
                } catch (IOException e) {
                    if (isRunning[0]) e.printStackTrace();
                }
            });

            // 发送线程
            Thread sender = new Thread(() -> {
                try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                     BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
                     BufferedWriter fileOut = new BufferedWriter(new FileWriter("client_sent.txt", true))) {
                    String line;
                    while (isRunning[0] && (line = consoleIn.readLine()) != null) {
                        out.write(line);
                        out.newLine();
                        out.flush();
                        fileOut.write("Client: " + line); // 写入到文件
                        fileOut.newLine();
                        fileOut.flush(); // 确保内容被写入文件
                        if ("bye".equalsIgnoreCase(line)) {
                            System.out.println("Client closing connection...");
                            isRunning[0] = false; // 通知退出
                            break;
                        }
                    }
                } catch (IOException e) {
                    if (isRunning[0]) e.printStackTrace();
                }
            });

            receiver.start();
            sender.start();

            receiver.join(); // 等待接收线程结束
            sender.join();   // 等待发送线程结束

            System.out.println("Client stopped.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
