package server.mainproject.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.mainproject.post.dto.PostDto;
import server.mainproject.post.entity.Likes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
//        private List<Likes> likesList;
        private List<PostDto.Response> postList;
    }
}
