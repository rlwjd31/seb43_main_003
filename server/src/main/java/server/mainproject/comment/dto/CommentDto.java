package server.mainproject.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.mainproject.member.entity.Member;
import server.mainproject.post.entity.DevPost;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostComment {
        //Todo : 추가
        private long memberId;
        private long postId;
        @NotBlank
        private String content;
        private int star;

        //Todo : 추가
        public Member getMember () {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }

        public DevPost getDevPost() {
            DevPost devPost = new DevPost();
            devPost.setPostId(postId);

            return devPost;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PatchComment {
        //Todo : 추가
        private long memberId;
        private long postId;
        private long commentId;
        @NotBlank
        private String content;
        @NotBlank
        private int star;

        //Todo : 추가
        public Member getMember () {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }

        public DevPost getDevPost() {
            DevPost devPost = new DevPost();
            devPost.setPostId(postId);

            return devPost;
        }
    }

    @Getter
    @Setter
    public static class ResponseComment {
        private long memberId;
        private String userName;
        private String profileImage;
        private long postId;
        private long commentId;
        private String content;
        private int star;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
