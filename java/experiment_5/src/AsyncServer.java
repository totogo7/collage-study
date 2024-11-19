import java.io.*;
import java.net.*;

public class AsyncServer {
    public static void main(String[] args) {
        System.out.println("Server starting...");
        try (ServerSocket serverSocket = new ServerSocket(8003)) {
            System.out.println("Server started on port 8003...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected...");

            // 标志位
            final boolean[] isRunning = {true};

            // 接收线程
            Thread receiver = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter fileOut = new BufferedWriter(new FileWriter("server_received.txt", true))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Client: " + line);
                        fileOut.write("Client: " + line); // 写入到文件
                        fileOut.newLine();
                        fileOut.flush(); // 确保内容被写入文件
                        if ("bye".equalsIgnoreCase(line)) {
                            System.out.println("Client requested to close connection.");
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
                     BufferedWriter fileOut = new BufferedWriter(new FileWriter("server_sent.txt", true))) {
                    String line;
                    while (isRunning[0] && (line = consoleIn.readLine()) != null) {
                        out.write(line);
                        out.newLine();
                        out.flush();
                        fileOut.write("Server: " + line); // 写入到文件
                        fileOut.newLine();
                        fileOut.flush(); // 确保内容被写入文件
                        if ("bye".equalsIgnoreCase(line)) {
                            System.out.println("Server closing connection...");
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

            System.out.println("Server stopped.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
