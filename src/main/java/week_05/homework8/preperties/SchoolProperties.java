package week_05.homework8.preperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties()
public class SchoolProperties {

    public String schoolName;

}
