package week_05.homework8.preperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = StudentProperties.STUDENT_PREFIX)
public class StudentProperties {

    public String studentName;

    private String enable = "true";

    public static final String STUDENT_PREFIX = "student";

}
