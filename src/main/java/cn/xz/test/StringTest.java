package cn.xz.test;

/**
 * @author xz
 * @ClassName StringTest
 * @Description
 * @date 2019/6/15 0015 15:12
 **/
public class StringTest {
    public static void main(String[] args) {
        String s = "123";
        String s1 = "123";

        String s2 = new String("456"); // 这里会创建一个堆引用
        String s3 = "456";
        String s4 = s2.intern(); // 先获取到常量池中456的引用并返回

        System.out.println(s == s1); // true
        System.out.println(s2 == s3); // false
        System.out.println(s2 == s4); // true

        String ss = new String("1") + new String("2");
        System.out.println(ss.intern() == ss); // true
    }
}
