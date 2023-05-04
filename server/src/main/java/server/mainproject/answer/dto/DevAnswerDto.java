package server.mainproject.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.mainproject.member.entity.Member;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class DevAnswerDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDevAnswer {
        //Todo : 추가
        private long memberId;
        @NotBlank
        private String content;
        private int review;

        //Todo : 추가
        public Member getMember () {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PatchDevAnswer {
        //Todo : 추가
        private long memberId;
        private long devAnswerId;
        @NotBlank
        private String content;
        @NotBlank
        private int review;

        //Todo : 추가
        public Member getMember () {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }
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
