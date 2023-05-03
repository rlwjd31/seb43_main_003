package server.mainproject.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String password;
    }
    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;
        private String userName;
        private String nickName;
        private String password;
        private String profileImage;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String memberId;
        private String email;
        private String userName;
        private String nickName;
        private String profileImage;
    }
}
