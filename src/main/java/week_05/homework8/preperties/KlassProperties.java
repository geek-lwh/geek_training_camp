package week_05.homework8.preperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = KlassProperties.KLASS_PREFIX)
public class KlassProperties {

    public String klassName;

    private String enable = "true";

    public static final String KLASS_PREFIX = "klass";


}
