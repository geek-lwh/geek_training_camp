package week_05.homework8.preperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = SchoolProperties.SCHOOL_PREFIX)
public class SchoolProperties {

    public String schoolName;

    private String enable = "true";

    public static final String SCHOOL_PREFIX = "school";

}
