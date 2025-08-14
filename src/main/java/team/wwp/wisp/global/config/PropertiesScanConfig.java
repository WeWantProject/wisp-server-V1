package team.wwp.wisp.global.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import team.wwp.wisp.global.security.jwt.data.JwtEnvironment;
import team.wwp.wisp.global.thirdparty.sms.data.SmsEnvironment;

@EnableConfigurationProperties({JwtEnvironment.class, SmsEnvironment.class})
@Configuration
public class PropertiesScanConfig {
}
