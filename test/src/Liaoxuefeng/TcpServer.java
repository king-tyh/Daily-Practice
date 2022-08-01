package Liaoxuefeng;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9999); // 监听指定端口
        System.out.println("server is running...");
        //循环 每次有一个客户端请求连接就创建一个Socket用来和客户端通信
        for (;;) {
            Socket sock = ss.accept();
            //输出请求连接的客户端地址
            System.out.println("connected from " + sock.getRemoteSocketAddress());
            Thread t = new Handler(sock);
            t.start();
        }
    }
}

class Handler extends Thread {
    Socket sock;

    public Handler(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try (InputStream input = this.sock.getInputStream()) {
            try (OutputStream output = this.sock.getOutputStream()) {
                handle(input, output);
            }
        } catch (Exception e) {
            try {
                this.sock.close();
            } catch (IOException ioe) {
            }
            System.out.println("client connect failed");
        }finally {
            //最后输出断开连接的客户端地址
            System.out.println("disconnected with " + sock.getRemoteSocketAddress());
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        //往客户端发送 hello
        writer.write("hello\n");
        writer.flush();

        //循环读取客户端发送的一行 发送 ok:获取到的内容 直到接收到的内容为 bye
        for (;;) {
            String s = reader.readLine();
            if (s.equals("bye")) {
                writer.write("bye\n");
                writer.flush();
                break;
            }
            writer.write("ok: " + s + "\n");
            writer.flush();
        }
    }
}
