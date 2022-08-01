package Liaoxuefeng;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        for(int i=0;i<3;i++){
            Socket sock = new Socket("localhost", 9999); // 连接指定服务器和端口
            Client c = new Client(sock);
            c.start();
        }
        Socket sock = new Socket("localhost", 9999); // 连接指定服务器和端口
        try (InputStream input = sock.getInputStream()) {
            try (OutputStream output = sock.getOutputStream()) {
                handle(input, output);
            }
        }
        sock.close();
        System.out.println("disconnected.");
    }

    private static void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        System.out.println("[server] " + reader.readLine());
        for (;;) {
            System.out.print(">>> "); // 打印提示
            String s = scanner.nextLine(); // 读取一行输入
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            if (resp.equals("bye")) {
                break;
            }
        }
    }
}

//默认创建客户端 发送 1,2,3,bye
class Client extends Thread{
    Socket sock;
    public Client(Socket sock){
        this.sock = sock;
    }

    @Override
    public void run() {
        try{
            InputStream input = this.sock.getInputStream();
            OutputStream output = this.sock.getOutputStream();
            handle(input,output);
            input.close();
            output.close();
            sock.close();

        }catch (IOException e){
            System.out.println("IOException in thread " + sock.getRemoteSocketAddress());
        }
        System.out.println("disconnected.");
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        String[] s = {"1","2","3","bye"};
        System.out.println("[server] " + reader.readLine());
        for (int i=0;i<4;i++) {
            System.out.println(">>>" + s[i]);
            writer.write( s[i]);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            if (resp.equals("bye")) {
                break;
            }
        }
    }

}
