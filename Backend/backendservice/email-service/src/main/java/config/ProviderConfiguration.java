package config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
@Data
@NoArgsConstructor
public class ProviderConfiguration {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private Boolean debug;
}
