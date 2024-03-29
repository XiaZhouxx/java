package cn.xz.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author xz
 * @ClassName MyClietnSocket
 * @Description BIO 客户端
 * @date 2019/7/8 0008 11:45
 **/
public class MyBIOClientSocket {
    public static void main(String[] args) {
        try {
            // 绑定服务端
            Socket socket = new Socket("127.0.0.1",8080);
            Scanner sc = new Scanner(System.in);
            // 发送一个文件
            /*File file = new File("D:/test.txt");
            FileInputStream inputStream = new FileInputStream(file);
            byte[] b = new byte[1024];
            inputStream.read(b);
            inputStream.close();
            socket.getOutputStream().write(b);*/
            // 获取输出输入流
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            // 接收消息
            new Thread(() -> {
                while(true){
                    byte[] b = new byte[1024];
                    try {
                        inputStream.read(b); // 阻塞 一直等待服务器发来消息
                        System.out.println("服务端回发的消息:" + new String(b,0,b.length));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            // 发送消息
            new Thread(() -> {
                while (true){
                    try {
                        String msg = sc.nextLine();
                        outputStream.write(msg.getBytes()); // 写到服务器中
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
