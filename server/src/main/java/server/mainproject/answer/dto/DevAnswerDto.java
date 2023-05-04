package server.mainproject.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.mainproject.member.entity.Member;
import server.mainproject.post.entity.Post;

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
        private long postId;
        @NotBlank
        private String content;
        private int review;

        //Todo : 추가
        public Member getMember () {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }

        public Post getPost () {
            Post post = new Post();
            post.setPostId(postId);

            return post;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PatchDevAnswer {
        //Todo : 추가
        private long memberId;
        private long postId;
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

        public Post getPost () {
            Post post = new Post();
            post.setPostId(postId);

            return post;
        }
    }

    @Getter
    @Setter
    public static class ResponseDevAnswer {
        private long memberId;
        private String nickName;
        private String profileImage;
        private long postId;
        private long devAnswerId;
        private String content;
        private int review;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
