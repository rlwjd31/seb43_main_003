package server.mainproject.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import server.mainproject.answer.dto.DevAnswerDto;
import server.mainproject.member.dto.AuthorResponseDto;
import server.mainproject.member.entity.Member;
import server.mainproject.tag.Tag;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DevPostDto {
    @Getter
    public static class Post {
        private Long memberId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        private int star;
        private String link;

        private List<String> tag;   // 리스트로 받아서

        public Member getMember () {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }
    }
    @Getter
    @Setter
    public static class Patch {
        private Long postId;
        private Long memberId;
        private String title;
        private String content;
        private int star;
        private String link;

        public Member getMember () {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }
    }
    @Getter @Setter
    @AllArgsConstructor
    public static class Response {
        private Long postId;
        private String title;
        private String content;
        private String userName;
        private String link;
        private int star;
        private Double starAvg;
        private int recommend;
        private List<AuthorResponseDto> authors;
        private List<Tag> tags;
        private List<DevAnswerDto.ResponseDevAnswer> answers;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

//        public Member getMember() {
//            Member member = new Member();
//            member
//        }
    }
}
