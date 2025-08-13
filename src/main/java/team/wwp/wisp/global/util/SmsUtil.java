package team.wwp.wisp.global.util;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import team.wwp.wisp.global.thirdparty.sms.data.SmsEnvironment;

@Component
@RequiredArgsConstructor
public class SmsUtil {
    
    private final SmsEnvironment smsEnvironment;

    private DefaultMessageService messageService;

    @PostConstruct
    public void init() {
        this.messageService = NurigoApp.INSTANCE
            .initialize(
                smsEnvironment.key(),
                smsEnvironment.secret(),
                "https://api.coolsms.co.kr"
            );
    }

    public void sendSms(String to, String authCode) {
        Message message = new Message();
        message.setFrom(smsEnvironment.sender());
        message.setTo(to);
        message.setText("인증번호는 " + authCode + "입니다.");

        this.messageService.sendOne(new SingleMessageSendingRequest(message));
    }
}
