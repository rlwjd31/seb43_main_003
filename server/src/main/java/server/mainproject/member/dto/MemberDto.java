package server.mainproject.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.mainproject.comment.dto.CommentDto;
import server.mainproject.post.dto.DevPostDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String password;
        @NotBlank
        private String userName;
    }
    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;
        private String userName;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String memberId;
        private String email;
        private String userName;
        private List<DevPostDto.Response> posts;
        private List<CommentDto.ResponseComment> answers;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
