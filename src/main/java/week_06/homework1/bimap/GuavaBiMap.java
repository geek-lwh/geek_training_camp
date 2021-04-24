package week_06.homework1.bimap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class GuavaBiMap {

    /**
     * 在使用BiMap时，会要求Value的唯一性。否则报错
     * 适用于k,v都要求唯一的场景
     * hashmap要求k唯一,但是v可以不唯一
     */
    public static void test() {
        BiMap<Integer, String> logfileMap = HashBiMap.create();
        logfileMap.put(1, "a.log");
        logfileMap.put(2, "b.log");
        logfileMap.put(3, "c.log");
        System.out.println("logfileMap:" + logfileMap);
        // inverse 翻转 没有新生对象,对源生对象修改会受到影响
        BiMap<String, Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:" + filelogMap);
    }

    public static void main(String[] args) {
        test();
    }

}
