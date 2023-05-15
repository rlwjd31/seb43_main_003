package server.mainproject.post.mapper;

import org.mapstruct.Mapper;
import server.mainproject.comment.dto.CommentDto;
import server.mainproject.comment.entity.Comment;
import server.mainproject.member.dto.AuthorResponseDto;
import server.mainproject.member.entity.Member;
import server.mainproject.post.dto.DevPostDto;
import server.mainproject.post.dto.Post_TagResponseDto;
import server.mainproject.post.entity.DevPost;
import server.mainproject.tag.Post_Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DevPostMapper {
    DevPost postToEntity (DevPostDto.Post post);
    DevPost patchToEntity (DevPostDto.Patch patch);
    default DevPostDto.Response EntityToResponse (DevPost post) {
        Set<Post_Tag> postTags = post.getPostTags();

        DevPostDto.Response response = new DevPostDto.Response(
                "success",
                post.getPostId(), post.getTitle(), post.getContent(),
                post.getLink(), post.getStar(), post.getStarAvg(),
                post.getRecommend(),
                postMemberDtoResponse(post),
                postTagDtoResponse(postTags),
                postCommentResponse(post.getComments())
        );

        return response;
    }
    default List<CommentDto.ResponseComment> postCommentResponse (List<Comment> comments) {
        return comments
                .stream()
                .map(comment -> {
                    CommentDto.ResponseComment response = new CommentDto.ResponseComment();
//                    response.setMemberId(comment.getMemberId());
//                    response.setUserName(comment.getUserName());
//                    response.setProfileImage(null);
                    response.setPostId(comment.getPostId());
                    response.setCommentId(comment.getCommentId());
                    response.setStar(comment.getStar());
                    response.setCreatedAt(comment.getCreatedAt());
                    response.setModifiedAt(comment.getModifiedAt());
                    return response;
                }
                ).collect(Collectors.toList());
    }
    default List<Post_TagResponseDto> postTagDtoResponse (Set<Post_Tag> postTags) {
        List<Post_TagResponseDto> result = new ArrayList<>();
        List<String> tagName = postTags.stream().map(tag -> tag.getTag().getName())
                .collect(Collectors.toList());

        result.add(Post_TagResponseDto
                .builder()
                .tags(tagName)
                .build());

        return result;

    }
    default List<AuthorResponseDto> postMemberDtoResponse (DevPost devPost) {
        List<AuthorResponseDto> author = new ArrayList<>();

        AuthorResponseDto ar = AuthorResponseDto
                .builder()
                .memberId(devPost.getMember().getMemberId())
                .userName(devPost.getMember().getUserName())
//                .createdAt(devPost.getCreatedAt())
//                .modifiedAt(devPost.getModifiedAt())
                .build();
        author.add(ar);

        return author;
    }
    List<DevPostDto.Response> ListResponse (List<DevPost> posts);
}
