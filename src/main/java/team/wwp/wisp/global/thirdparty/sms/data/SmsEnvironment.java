package team.wwp.wisp.global.thirdparty.sms.data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "coolsms")
public record SmsEnvironment(
    String key,
    String secret,
    String sender
) {
}
