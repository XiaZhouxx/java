package cn.xz.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author xz
 * @ClassName MyServerSocket
 * @Description BIO 服务端
 * @date 2019/7/8 0008 11:46
 **/
public class MyBIOServerSocket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // 创建服务端监听一个端口
            ServerSocket socket = new ServerSocket(8080);
            // 打开一个连接
            Socket accept = socket.accept();
            // 获取输出输入流
            OutputStream outputStream = accept.getOutputStream();
            InputStream inputStream = accept.getInputStream();
            // 接收消息
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        byte[] b = new byte[1024];
                        try {
                            inputStream.read(b);
                            System.out.println("客户端发送的消息:" + new String(b,0,b.length));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            // 发送消息
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){

                        // 读取客户端发来的文件or消息
                /*DataInputStream data = new DataInputStream(inputStream);
                byte[] b = new byte[1024];
                data.read(b);*/

                        // 接收一个文件
                /*File file = new File("D:/test1.txt");
                FileOutputStream outputStream1 = new FileOutputStream(file);
                outputStream1.write(b);
                outputStream1.close();*/
                        String message = scanner.nextLine();
                        // 发送消息给客户端
                        try {
                            outputStream.write(message.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
