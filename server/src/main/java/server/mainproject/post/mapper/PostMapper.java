package server.mainproject.post.mapper;

import org.mapstruct.Mapper;
import server.mainproject.post.dto.PostDto;
import server.mainproject.post.entity.Post;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postToEntity (PostDto.Post post);
    Post patchToEntity (PostDto.Patch patch);
    PostDto.Response EntityToResponse (Post post);
    List<PostDto.Response> ListResponse (List<Post> posts);
}
