package cn.xz.bean;

/**
 * @author xz
 * @ClassName InnerClass
 * @Description
 * @date 2019/7/5 0005 9:46
 **/
public class InnerClass {
    static class StaticInnerClass{
        private String name = "staticInnerClass";
    }

    class InnerClass1{
        private String name = "innerClass1";
    }

    public static void main(String[] args) {
        StaticInnerClass staticInnerClass = new InnerClass.StaticInnerClass();
        InnerClass1 innerClass1 = new InnerClass().new InnerClass1();
    }
}
