package soochow.zmq.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties(prefix = "soochow.zmq")
@Getter
@Setter
public class AppProperties {

    private Set<String> anonymousUrls;

    private String loginUrl;

}
