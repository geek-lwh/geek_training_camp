package week_05.homework3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Cat implements Animal {

    private String name;

    private int age;

    @Override
    public void eat() {
        System.out.println("i am women");
    }
}
