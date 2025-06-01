package umc._th.spring.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("jwt.token")
public class JwtProperties {
    private TokenType tokenType;
    private String secretKey="";
    private Expiration expiration;

    @Getter
    @Setter
    public static class Expiration{
        private Long access;
    }
    
    public enum TokenType {
        ACCESS, REFRESH
    }
}