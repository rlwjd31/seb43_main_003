package server.mainproject.post.dto;

import lombok.Getter;
import lombok.Setter;
import server.mainproject.member.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
public class PostDto {
    @Getter
    public static class Post {
        private long memberId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        private int review;
        private String link;

        public Member getMember () {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }
    }
    @Getter
    @Setter
    public static class Patch {
        private long postId;
        private long memberId;
        private String title;
        private String content;
        private int review;
        private String link;

        public Member getMember () {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }
    }
    @Getter
    @Setter
    public static class Response {
        private long postId;
        private String title;
        private String content;
        private String userName;
        private String link;
        private int review; // todo : 평점/별점 구하는 알고리즘 알아보기 (인터페이스 구현) 아마도
        private int allReviews; // todo : 댓글 단 사람들의 평점 댓글이랑 매핑 후 인터페이스 적용.
        private int likes;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
