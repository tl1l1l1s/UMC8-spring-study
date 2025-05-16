package umc._th.spring.web.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import umc._th.spring.web.dto.DiscordMessage;

@org.springframework.cloud.openfeign.FeignClient(name = "${discord.name}", url = "${discord.webhook-url}")
public interface DiscordFeignClient {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void sendMessage(@RequestBody DiscordMessage message);
}
