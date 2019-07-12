package cn.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author xz
 * @ClassName MyTest
 * @Description
 * @date 2019/4/1 0001 13:49
 **/
public class JsonpTest {
    private static final String url = "http://gaokao.eol.cn/chong_qing/dongtai/201806/t20180623_1611973.shtml";
    public static void main(String[] args) {
            initID();
    }

    public static void initID() {
        Document doc = null;
        try{
            doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                    .timeout(3000).get();
            Elements elements = doc.getElementsByTag("tr");
            for(Element e : elements) {
                System.out.println(e.text());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


}
