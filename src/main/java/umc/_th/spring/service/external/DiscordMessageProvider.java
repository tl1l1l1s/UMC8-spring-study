package umc._th.spring.service.external;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc._th.spring.apiPayload.code.ErrorReasonDTO;
import umc._th.spring.web.client.DiscordFeignClient;
import umc._th.spring.web.dto.DiscordMessage;

@RequiredArgsConstructor
@Component
public class DiscordMessageProvider {
    private final DiscordFeignClient discordFeignClient;

    public void sendMessage(ErrorReasonDTO errorReason) {
        DiscordMessage discordMessage = DiscordMessage.createDiscordMessage(errorReason);
        sendMessageToDiscord(discordMessage);
    }

    private void sendMessageToDiscord(DiscordMessage discordMessage) {
        try {
            discordFeignClient.sendMessage(discordMessage);
        } catch (FeignException e) {
            throw new IllegalStateException("디스코드 알림 전송에 실패했습니다.");
        }
    }
}
