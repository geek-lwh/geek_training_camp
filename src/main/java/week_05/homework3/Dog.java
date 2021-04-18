package week_05.homework3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
// 默认使用无惨构造方法不要替换掉
@NoArgsConstructor
public class Dog implements Animal {

    private String name;

    private int age;

    @Override
    public void eat() {
        System.out.println("i am men");
    }
}
