package cn.xz.test;

import org.junit.Test;

import java.util.ArrayList;

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
        System.out.println(ss.intern() == ss);
        /**
         *   上方为true intern会优先在常量池中寻找常量的地址
         *   而ss是由两个字符串对象拼接而来,并且拼接后的 12 在常量池中并不存在，所以接着在堆中查找到并返回
         *   注意这里 再1.7之前是将字符串复制到常量池中并返回 1.7之后则是复制其内存地址返回
         *   所以==为true 1.7之前则为false
          */

        String ss1 = new StringBuilder("1").append("234").toString();
        System.out.println(ss1.intern() == ss1);


    }

    @Test
    public void test(){
        ArrayList<String> list = new ArrayList<String>(3){{
            add("2");
            add("9");
            add("6");
        }};

    }
}
