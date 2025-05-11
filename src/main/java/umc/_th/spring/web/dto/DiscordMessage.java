package umc._th.spring.web.dto;

import umc._th.spring.apiPayload.code.ErrorReasonDTO;

public record DiscordMessage(String content) {

    public static DiscordMessage createDiscordMessage(ErrorReasonDTO error) {
        return new DiscordMessage(String.format(
                "[%s] %s",
                error.getCode(),
                error.getMessage()
        ));
    }
}
