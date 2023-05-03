package server.mainproject.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class DevAnswerDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDevAnswer {
        @NotBlank
        private String content;
        @NotBlank
        private int review;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PatchDevAnswer {
        private long devAnswerId;
        @NotBlank
        private String content;
        @NotBlank
        private int review;
    }

    @Getter
    @Setter
    public static class ResponseDevAnswer {
        private long devAnswerId;
        private String content;
        private int review;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
