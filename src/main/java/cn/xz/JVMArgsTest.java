package cn.xz;

import java.lang.ref.SoftReference;

/**
 * @author xz
 * @ClassName JVM常用参数测试
 * @Description
 * @date 2019/11/2 0002 20:31
 **/
public class JVMArgsTest {
    public static void main(String[] args) {
        /**
         * -XX:+PrintGC 在JVM运行时如果有触发GC则打印GC信息
         * -XX:+PrintGCDetails 则还会在在JVM运行完毕后打印堆的详细信息
         * -XX:+PrintHeapAtGC 则在运行时也会打印堆的详细信息
         * -XX:+PrintGCTimeStamps 打印GC发生的时间 JVM启动时间-当前时间
         * -XX:+PrintGCApplicationConcurrentTime 打印应用程序执行时间
         * -XX:+PrintGCApplicationStoppedTime 打印GC时造成的停顿时间
         * -XX:+PrintReferenceGC 打印追踪软、弱、虚引用
         *
         * -XX:+TraceClassLoading 追踪类的加载
         * -XX:+TractClassUnloading 追踪类的卸载
         * -verbose:class 追踪类的加载和卸载
         * -XX:+PrintClassHistogram 运行时打印类的分布情况 结合Ctrl + Break
         * -XX:+PrintVMOptions 打印JVM明确接收到的指令
         * -XX:+PrintCommandLineFlags 打印JVM实际接收到的指令(比如一些默认的参数)
         * -Xloggc: xxx 将GC日志输出到指定文件
         */
        Object o = new Object();
        Object b = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o);
        o = null;
        System.gc();

    }
}
