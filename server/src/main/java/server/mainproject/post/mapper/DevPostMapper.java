package server.mainproject.post.mapper;

import org.mapstruct.Mapper;
import server.mainproject.post.dto.DevPostDto;
import server.mainproject.post.entity.DevPost;

import java.util.List;
@Mapper(componentModel = "spring")
public interface DevPostMapper {
    DevPost postToEntity (DevPostDto.Post post);
    DevPost patchToEntity (DevPostDto.Patch patch);
    DevPostDto.Response EntityToResponse (DevPost post);
    List<DevPostDto.Response> ListResponse (List<DevPost> posts);
}
