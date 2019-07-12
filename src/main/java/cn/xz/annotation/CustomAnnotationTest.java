package cn.xz.annotation;

import java.io.*;
import java.util.Arrays;

/**
 * @author xz
 * @ClassName CustomAnnotationTest
 * @Description
 * @date 2019/6/13 0013 21:40
 **/
public class CustomAnnotationTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CustomAnnotationHandle.outAnnotationInfo(User.class);

        User user = new User();
        user.setName("123");


        // 在内存中序列化和反序列化
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(user);
        byte[] b = outputStream.toByteArray();

        System.out.println(Arrays.toString(b));

        ByteArrayInputStream inputStream = new ByteArrayInputStream(b);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        User user1 = (User) objectInputStream.readObject();

        System.out.println(user1);
    }
}
