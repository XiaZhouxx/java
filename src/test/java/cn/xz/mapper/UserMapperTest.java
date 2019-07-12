package cn.xz.mapper;

import cn.xz.bean.Clazz;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    PartitionDao dao;
    @Autowired
    UserMapper userMapper;

    private static final String url = "https://gkcx.eol.cn/gkcx/api";

    @Test
    public void test() {
        /**
         * 注意这里有个BUG 在Test环境下 使用多线程会有一个问题
         * 他不会等待子线程执行完成 并且junit创建的子线程都是守护线程
         * 所以主函数执行完后 子线程还没开始启动就被关闭 造成多线程无效的现象
         * 这个BUG真的要命。
         */
        // 创建核心线程数为10的线程池
        ExecutorService service = Executors.newFixedThreadPool(50);
        Runnable run = new InsertTask(userMapper);
        for(int i = 0;i < 50;i++) {
            service.execute(run);
        }
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private class InsertTask implements Runnable{

        private UserMapper mapper;

        public InsertTask(UserMapper mapper){
            this.mapper = mapper;
        }

        @Override
        public void run() {
            for(int i = 0;i < 100000;i++) {
                Map<String,Object> map = new HashMap<>();
                map.put("s_id",new Random().nextInt(600000));
                map.put("c_id",new Random().nextInt(10));
                map.put("score",new Random().nextInt(100));
                userMapper.insertByMap(map);
            }
        }
    }

    // 爬虫测试
    @Test
    public void test1() {
        Document doc = null;
        try{
            String s = "{\"uri\":\"hxsjkqt/api/gk/special/lists\",\"size\":\"20\",\"page\":\"1\",\"keyword\":\"\",\"level1\":\"\",\"level2\":\"\"}";

            Map<String, String> map = JSON.parseObject(s,Map.class);
            doc = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .data(map)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                    .timeout(3000).post();
            Elements elements = doc.getElementsByTag("span");
            int count = 0;
            for(Element e : elements) {
                System.out.println(e.text());
                //String[] text = e.text().split(" ");
                //Partition p = new Partition();
                try{
                    /*int grade = Integer.parseInt(text[0]);
                    int num = Integer.parseInt(text[1]);
                    int total = Integer.parseInt(text[2]);
                    p.setGrade(grade);
                    p.setUserNum(num);
                    p.setUserTotal(total);*/
                }catch(Exception e1) {
                    count++;
                    continue;
                }
                //p.setLevCode("0");
                /*if(count > 2) {
                    p.setLevCode("1");
                }*/
//                p.setProvince("HL");
//                p.setSubCode("b");
//                p.setYeaCode("2018");
//                dao.add(p);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        String data = "[{\"limit_year\":\"四年\",\"level2_name\":\"哲学\",\"special_id\":1,\"degree\":\"哲学学士\",\"rank_type\":1,\"view_week\":25,\"view_total\":41214,\"level3_name\":\"哲学类\",\"name\":\"哲学\",\"rank\":1096,\"rankall\":25,\"id\":\"gkspecial1\",\"level1\":1,\"level3\":34,\"level1_name\":\"本科\",\"view_month\":437,\"level2\":3},{\"limit_year\":\"四年\",\"level2_name\":\"哲学\",\"special_id\":2,\"degree\":\"哲学学士\",\"rank_type\":2,\"view_week\":9,\"view_total\":21240,\"level3_name\":\"哲学类\",\"name\":\"逻辑学\",\"rank\":1095,\"rankall\":55,\"id\":\"gkspecial2\",\"level1\":1,\"level3\":34,\"level1_name\":\"本科\",\"view_month\":197,\"level2\":3},{\"limit_year\":\"四年\",\"level2_name\":\"哲学\",\"special_id\":3,\"degree\":\"哲学学士\",\"rank_type\":4,\"view_week\":8,\"view_total\":12710,\"level3_name\":\"哲学类\",\"name\":\"宗教学\",\"rank\":1094,\"rankall\":711,\"id\":\"gkspecial3\",\"level1\":1,\"level3\":34,\"level1_name\":\"本科\",\"view_month\":145,\"level2\":3},{\"limit_year\":\"四年\",\"level2_name\":\"哲学\",\"special_id\":1098,\"degree\":\"哲学学士\",\"rank_type\":3,\"view_week\":5,\"view_total\":16478,\"level3_name\":\"哲学类\",\"name\":\"伦理学\",\"rank\":0,\"rankall\":681,\"id\":\"gkspecial1098\",\"level1\":1,\"level3\":34,\"level1_name\":\"本科\",\"view_month\":143,\"level2\":3},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":4,\"degree\":\"经济学学士\",\"rank_type\":13,\"view_week\":12,\"view_total\":3861,\"level3_name\":\"经济学类\",\"name\":\"经济学\",\"rank\":1093,\"rankall\":1003,\"id\":\"gkspecial4\",\"level1\":1,\"level3\":35,\"level1_name\":\"本科\",\"view_month\":276,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":5,\"degree\":\"经济学学士\",\"rank_type\":15,\"view_week\":6,\"view_total\":2662,\"level3_name\":\"经济学类\",\"name\":\"经济统计学\",\"rank\":1092,\"rankall\":1120,\"id\":\"gkspecial5\",\"level1\":1,\"level3\":35,\"level1_name\":\"本科\",\"view_month\":159,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":1099,\"degree\":\"经济学学士\",\"rank_type\":4,\"view_week\":6,\"view_total\":21790,\"level3_name\":\"经济学类\",\"name\":\"国民经济管理\",\"rank\":0,\"rankall\":42,\"id\":\"gkspecial1099\",\"level1\":1,\"level3\":35,\"level1_name\":\"本科\",\"view_month\":141,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":1100,\"degree\":\"经济学学士\",\"rank_type\":7,\"view_week\":8,\"view_total\":20811,\"level3_name\":\"经济学类\",\"name\":\"资源与环境经济学\",\"rank\":0,\"rankall\":100,\"id\":\"gkspecial1100\",\"level1\":1,\"level3\":35,\"level1_name\":\"本科\",\"view_month\":136,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":1101,\"degree\":\"经济学学士\",\"rank_type\":1,\"view_week\":9,\"view_total\":398871,\"level3_name\":\"经济学类\",\"name\":\"商务经济学\",\"rank\":0,\"rankall\":2,\"id\":\"gkspecial1101\",\"level1\":1,\"level3\":35,\"level1_name\":\"本科\",\"view_month\":239,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":1102,\"degree\":\"经济学学士\",\"rank_type\":8,\"view_week\":8,\"view_total\":20805,\"level3_name\":\"经济学类\",\"name\":\"能源经济\",\"rank\":0,\"rankall\":102,\"id\":\"gkspecial1102\",\"level1\":1,\"level3\":35,\"level1_name\":\"本科\",\"view_month\":148,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":6,\"degree\":\"经济学学士\",\"rank_type\":2,\"view_week\":10,\"view_total\":55569,\"level3_name\":\"财政学类\",\"name\":\"财政学\",\"rank\":1091,\"rankall\":17,\"id\":\"gkspecial6\",\"level1\":1,\"level3\":36,\"level1_name\":\"本科\",\"view_month\":216,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":7,\"degree\":\"经济学学士\",\"rank_type\":16,\"view_week\":13,\"view_total\":2472,\"level3_name\":\"财政学类\",\"name\":\"税收学\",\"rank\":1090,\"rankall\":1147,\"id\":\"gkspecial7\",\"level1\":1,\"level3\":36,\"level1_name\":\"本科\",\"view_month\":169,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":8,\"degree\":\"经济学学士\",\"rank_type\":12,\"view_week\":17,\"view_total\":4031,\"level3_name\":\"金融学类\",\"name\":\"金融学\",\"rank\":1089,\"rankall\":983,\"id\":\"gkspecial8\",\"level1\":1,\"level3\":37,\"level1_name\":\"本科\",\"view_month\":253,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":9,\"degree\":\"经济学学士\",\"rank_type\":3,\"view_week\":8,\"view_total\":46771,\"level3_name\":\"金融学类\",\"name\":\"金融工程\",\"rank\":1088,\"rankall\":24,\"id\":\"gkspecial9\",\"level1\":1,\"level3\":37,\"level1_name\":\"本科\",\"view_month\":175,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":10,\"degree\":\"经济学学士\",\"rank_type\":17,\"view_week\":7,\"view_total\":2277,\"level3_name\":\"金融学类\",\"name\":\"保险学\",\"rank\":1087,\"rankall\":1172,\"id\":\"gkspecial10\",\"level1\":1,\"level3\":37,\"level1_name\":\"本科\",\"view_month\":114,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":11,\"degree\":\"经济学学士\",\"rank_type\":9,\"view_week\":9,\"view_total\":19446,\"level3_name\":\"金融学类\",\"name\":\"投资学\",\"rank\":1086,\"rankall\":663,\"id\":\"gkspecial11\",\"level1\":1,\"level3\":37,\"level1_name\":\"本科\",\"view_month\":143,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":1103,\"degree\":\"经济学学士\",\"rank_type\":5,\"view_week\":10,\"view_total\":20877,\"level3_name\":\"金融学类\",\"name\":\"金融数学\",\"rank\":0,\"rankall\":98,\"id\":\"gkspecial1103\",\"level1\":1,\"level3\":37,\"level1_name\":\"本科\",\"view_month\":174,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":1104,\"degree\":\"经济学学士\",\"rank_type\":11,\"view_week\":6,\"view_total\":15667,\"level3_name\":\"金融学类\",\"name\":\"信用管理\",\"rank\":0,\"rankall\":686,\"id\":\"gkspecial1104\",\"level1\":1,\"level3\":37,\"level1_name\":\"本科\",\"view_month\":101,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":1105,\"degree\":\"经济学学士\",\"rank_type\":6,\"view_week\":11,\"view_total\":20878,\"level3_name\":\"金融学类\",\"name\":\"经济与金融\",\"rank\":0,\"rankall\":99,\"id\":\"gkspecial1105\",\"level1\":1,\"level3\":37,\"level1_name\":\"本科\",\"view_month\":162,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":1263,\"degree\":\"经济学学士\",\"rank_type\":18,\"view_week\":12,\"view_total\":344,\"level3_name\":\"金融学类\",\"name\":\"精算学\",\"rank\":0,\"rankall\":1257,\"id\":\"gkspecial1263\",\"level1\":1,\"level3\":37,\"level1_name\":\"本科\",\"view_month\":186,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":12,\"degree\":\"经济学学士\",\"rank_type\":14,\"view_week\":8,\"view_total\":2705,\"level3_name\":\"经济与贸易类\",\"name\":\"国际经济与贸易\",\"rank\":1085,\"rankall\":1115,\"id\":\"gkspecial12\",\"level1\":1,\"level3\":38,\"level1_name\":\"本科\",\"view_month\":176,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"经济学\",\"special_id\":13,\"degree\":\"经济学学士\",\"rank_type\":10,\"view_week\":5,\"view_total\":18950,\"level3_name\":\"经济与贸易类\",\"name\":\"贸易经济\",\"rank\":1084,\"rankall\":667,\"id\":\"gkspecial13\",\"level1\":1,\"level3\":38,\"level1_name\":\"本科\",\"view_month\":92,\"level2\":4},{\"limit_year\":\"四年\",\"level2_name\":\"法学\",\"special_id\":14,\"degree\":\"法学学士\",\"rank_type\":24,\"view_week\":16,\"view_total\":3284,\"level3_name\":\"法学类\",\"name\":\"法学\",\"rank\":1083,\"rankall\":1045,\"id\":\"gkspecial14\",\"level1\":1,\"level3\":39,\"level1_name\":\"本科\",\"view_month\":277,\"level2\":5},{\"limit_year\":\"四年\",\"level2_name\":\"法学\",\"special_id\":1106,\"degree\":\"法学学士\",\"rank_type\":10,\"view_week\":3,\"view_total\":9362,\"level3_name\":\"法学类\",\"name\":\"知识产权\",\"rank\":0,\"rankall\":766,\"id\":\"gkspecial1106\",\"level1\":1,\"level3\":39,\"level1_name\":\"本科\",\"view_month\":100,\"level2\":5},{\"limit_year\":\"四年\",\"level2_name\":\"法学\",\"special_id\":1107,\"degree\":\"法学学士\",\"rank_type\":14,\"view_week\":17,\"view_total\":7349,\"level3_name\":\"法学类\",\"name\":\"监狱学\",\"rank\":0,\"rankall\":823,\"id\":\"gkspecial1107\",\"level1\":1,\"level3\":39,\"level1_name\":\"本科\",\"view_month\":109,\"level2\":5},{\"limit_year\":\"四年\",\"level2_name\":\"法学\",\"special_id\":15,\"degree\":\"法学学士\",\"rank_type\":27,\"view_week\":6,\"view_total\":2348,\"level3_name\":\"政治学类\",\"name\":\"政治学与行政学\",\"rank\":1082,\"rankall\":1160,\"id\":\"gkspecial15\",\"level1\":1,\"level3\":40,\"level1_name\":\"本科\",\"view_month\":85,\"level2\":5},{\"limit_year\":\"四年\",\"level2_name\":\"法学\",\"special_id\":16,\"degree\":\"法学学士\",\"rank_type\":13,\"view_week\":23,\"view_total\":8452,\"level3_name\":\"政治学类\",\"name\":\"国际政治\",\"rank\":1081,\"rankall\":788,\"id\":\"gkspecial16\",\"level1\":1,\"level3\":40,\"level1_name\":\"本科\",\"view_month\":91,\"level2\":5},{\"limit_year\":\"四年\",\"level2_name\":\"法学\",\"special_id\":17,\"degree\":\"法学学士\",\"rank_type\":16,\"view_week\":4,\"view_total\":6563,\"level3_name\":\"政治学类\",\"name\":\"外交学\",\"rank\":1080,\"rankall\":851,\"id\":\"gkspecial17\",\"level1\":1,\"level3\":40,\"level1_name\":\"本科\",\"view_month\":97,\"level2\":5},{\"limit_year\":\"四年\",\"level2_name\":\"法学\",\"special_id\":1108,\"degree\":\"法学学士\",\"rank_type\":18,\"view_week\":1,\"view_total\":4785,\"level3_name\":\"政治学类\",\"name\":\"国际事务与国际关系\",\"rank\":0,\"rankall\":920,\"id\":\"gkspecial1108\",\"level1\":1,\"level3\":40,\"level1_name\":\"本科\",\"view_month\":51,\"level2\":5},{\"limit_year\":\"四年\",\"level2_name\":\"法学\",\"special_id\":1109,\"degree\":\"法学学士\",\"rank_type\":1,\"view_week\":2,\"view_total\":20670,\"level3_name\":\"政治学类\",\"name\":\"政治学、经济学与哲学\",\"rank\":0,\"rankall\":128,\"id\":\"gkspecial1109\",\"level1\":1,\"level3\":40,\"level1_name\":\"本科\",\"view_month\":55,\"level2\":5}]";

        ObjectMapper mapper = new ObjectMapper();

        JavaType type = mapper.getTypeFactory().constructParametricType(List.class,Clazz.class);

        try {
            List<Clazz> c = mapper.readValue(data,type);

            for(Clazz z : c) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //System.out.println(testCallable());
        UserMapperTest user = new UserMapperTest();
        new Thread(() -> {
            user.read();
        }).start();
        new Thread(() -> {
            user.write();
        }).start();

    }
    
    public void write(){
        this.i = 1;
        this.flag = true;
    }
    public void read() {
        if(flag) {
            System.out.println(this.i);
        }
    }
    boolean flag = false;
    int i = 0;

    public static String testCallable() {
        Callable<String> able = () -> {
           throw new RuntimeException();
        };
        FutureTask<String> task = new FutureTask<>(able);
        new Thread(task).start();
        try {
            return task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "异常补偿";
    }

}
