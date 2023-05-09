package server.mainproject.post.dto;

import lombok.Builder;
import lombok.Getter;
import server.mainproject.tag.Tag;

import java.util.List;

@Getter @Builder
public class Post_TagResponseDto {
//    private Long postId;
    private List<String> tags;
}
