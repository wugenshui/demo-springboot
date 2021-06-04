package oauth.spring.security.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class OauthSpringSecurityOauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(OauthSpringSecurityOauth2Application.class, args);
    }
}
