package week_06.homework1.bloomFilters;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;

public class UserBloomFilterDemo {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        User user1 = new User(1L, "a");
        User user2 = new User(2L, "b");
        User user3 = new User(3L, "c");

        BloomFilter bloomFilter = customize();
        bloomFilter.put(user1);
        bloomFilter.put(user2);
        bloomFilter.put(user3);

        System.out.println(bloomFilter.mightContain(new User(1L, "a")));
        System.out.println(bloomFilter.mightContain(new User(1L, "b")));
        System.out.println(bloomFilter.mightContain(new User(1L, "c")));
        System.out.println(bloomFilter.mightContain(new User(1L, "d")));
    }

    @SuppressWarnings("all")
    public static BloomFilter customize() {
        long expectedinsertions = 1000000L;
        double fpp = 0.03D;
        Funnel funnel = new Funnel<User>() {
            @Override
            public void funnel(User o, PrimitiveSink primitiveSink) {
                primitiveSink.putString(o.getName(), Charset.defaultCharset());
            }
        };

        return BloomFilter.create(funnel, expectedinsertions, fpp);
    }


}
