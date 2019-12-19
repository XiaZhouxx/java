package cn.xz.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xz
 * @ClassName TestFileNIO
 * @Description NIO操作文件 API 但是NIO文件操作是阻塞操作
 * @date 2019/7/28 0028 18:39
 **/
public class TestFileNIO {

    @Test
    public void testFileNIO() throws Exception {
        FileOutputStream in = new FileOutputStream("D:/test1.txt");
        FileInputStream input = new FileInputStream("D:/test.txt");
        // 获取一个文件管道
        FileChannel channel = in.getChannel();
        FileChannel channel1 = input.getChannel();
        // 创建一个字节缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer buffer1 = ByteBuffer.allocate(1024);

        buffer.put("NIO1".getBytes());
        buffer.flip(); // 反转缓冲区中的指针，因为写入管道时是从数据后的指针开始写入，导致写入空白字节。

        // 将缓冲区的数据写入管道
        channel.write(buffer);
        channel1.read(buffer1);

        // 关闭流
        in.close();
        input.close();
        System.out.println(new String(buffer1.array()));

    }
    @Test
    public void testFileNIOCopy() throws Exception {
        File file = new File("D:/test2.txt");
        file.createNewFile();

        FileInputStream in = new FileInputStream("D:/test.txt");
        FileOutputStream out = new FileOutputStream(file);

        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        // 复制 将 inChannel中的复制到outChannel
        inChannel.transferTo(0,inChannel.size(), outChannel);
        // outChannel 中的数据来自 inChannel
        // outChannel.transferFrom(inChannel,0,inChannel.size());

        in.close();
        out.close();
    }
}
