package week_06.homework1.bloomFilters;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * guava lua
 */
public class BloomFilterDemo {

    public static void duplicate() {
        // 将什么类型的对象转成hash
        Funnel funnel = Funnels.stringFunnel(Charset.defaultCharset());
        long expectedInsertions = 10000000;
        double fpp = 0.00001D;
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(funnel, expectedInsertions, fpp);

        bloomFilter.put("asd");

        System.out.println(bloomFilter.mightContain("asd"));
    }

    public static void main(String[] args) {
        duplicate();
    }
}
