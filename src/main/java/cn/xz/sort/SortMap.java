package cn.xz.sort;

import cn.xz.annotation.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xz
 * @ClassName SortMap
 * @Description 接收一个Map 进行排序
 * @date 2019/6/30 0030 13:11
 **/
public class SortMap {

    public static void main(String[] args) {
        /**
         * 例如 按照年龄进行排序
         */
        Map<Integer,User> map = new HashMap<>();
        map.put(1,new User("张三",11));
        map.put(2,new User("李四",12));
        map.put(3,new User("王五",44));
        map.put(5,new User("王麻子",9));
        System.out.println(map);
        sortMap(map);

        int[] arr = {1,1,3,4,5,77,88,99,0,3,5,6,8,0,8};
        array(arr);
    }

    public static Map<Integer,User> sortMap(Map<Integer, User> map){
        return null;
    }

    public static void array(int[] arr){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i : arr) {
            if(map.containsKey(i)){
                map.put(i,map.get(i) + 1);
            }else{
                map.put(i,1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "重复了" + entry.getValue() + "次");
        }
    }
}
