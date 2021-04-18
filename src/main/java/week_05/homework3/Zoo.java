package week_05.homework3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Zoo {

    private List<Dog> dogs;

    private Cat cat;
}
