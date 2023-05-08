package server.mainproject.comment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.mainproject.comment.dto.CommentDto;
import server.mainproject.comment.entity.Comment;
import server.mainproject.comment.mapper.CommentMapper;
import server.mainproject.comment.service.CommentService;
import server.mainproject.response.SingleResponse;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Slf4j
public class CommentController {
    private final CommentService service;
    private final CommentMapper mapper;

    public CommentController(CommentService service, CommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("comments")  // 생성
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.PostComment post) {
        Comment comment = mapper.commentPostToComment(post);

        Comment response = service.createComment(comment);

        return new ResponseEntity<>(new SingleResponse<>(mapper.commentToResponseComment(response)), HttpStatus.CREATED);
    }

    @PatchMapping("comments/{comment-id}")  // 수정
    public ResponseEntity patchComment(@PathVariable("comment-id") @Positive long commentId,
                                       @Valid @RequestBody CommentDto.PatchComment patch) {
        patch.setCommentId(commentId);

        Comment comment = service.updateComment(mapper.commentPatchToComment(patch));

        return new ResponseEntity<>(new SingleResponse<>(mapper.commentToResponseComment(comment)), HttpStatus.OK);
    }

    @GetMapping("{post-id}/comments") // 해당 질문글에 대한 모든 답변 조회
    public ResponseEntity getDevAnswers(@PathVariable("post-id") @Positive long postId) {
        List<Comment> comments = service.findComments();

        return new ResponseEntity<>(new SingleResponse<>(comments),  HttpStatus.OK);
    }

    @DeleteMapping("comments/{comment-id}")  // 삭제
    public ResponseEntity deleteComment(@PathVariable("comment-id") @Positive long commentId) {
        service.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}