package server.mainproject.comment.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import server.mainproject.comment.dto.CommentDto;
import server.mainproject.comment.entity.Comment;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostToComment(CommentDto.PostComment post);
    Comment commentPatchToComment(CommentDto.PatchComment patch);
    CommentDto.ResponseComment commentToResponseComment(Comment comment);
//    List<CommentDto.ResponseComment> commentsToResponseComments(List<Comment> comments);
}
