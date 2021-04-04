package week_03.hoursework_04;

import java.util.List;
import java.util.Random;

public class RandomEndpointRouter implements HttpEndpointRouter {
    @Override
    public String select(List<String> endpoint) {
        int index = new Random().nextInt(endpoint.size());
        return endpoint.get(index);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(new Random().nextInt(3));
        }
    }
}
