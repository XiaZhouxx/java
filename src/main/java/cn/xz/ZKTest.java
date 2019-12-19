package cn.xz;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * @author xz
 * @ClassName ZKTest
 * @Description 测试Zk Java客户端
 * @date 2019/12/18 0018 20:41
 **/
public class ZKTest {
    /**
     * 这里没有结合Spring boot 配置
     * 就直接硬编码了
     */

    /**
     * ZK 服务端配置的客户端访问端口
     */
    private String hostName = "47.107.246.54:2181,47.107.246.54:2182,47.107.246.54:2183";
    private int sessionTime = 2000;
    private ZooKeeper zk;
    private String serverPath = "/servers";

    public static void main(String[] args) throws Exception {
        ZKTest z = new ZKTest();
        z.connect();
        z.watchZNodeUp();

        Thread.sleep(Long.MAX_VALUE);
    }

    public void connect() throws Exception {
        zk = new ZooKeeper(hostName, sessionTime, new Watcher() {
            /**
             * 监听节点 发生变动会有回调
             * @param watchedEvent
             */
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    watchZNodeUp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 测试ZK动态感知服务的上下线
     */
    public void watchZNodeUp() throws Exception{
        // 这里我假设/servers 节点下存储服务节点
        List<String> znode = zk.getChildren(serverPath, true);
        for(String s : znode) {
            byte[] data = zk.getData(serverPath + "/" + s, null, null);
            System.out.println(new String(data));
        }

    }
}
