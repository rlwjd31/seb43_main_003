package server.mainproject.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.mainproject.post.dto.DevPostDto;
import server.mainproject.post.dto.DevPostPatchDto;
import server.mainproject.post.entity.DevPost;
import server.mainproject.post.entity.Recommend;
import server.mainproject.post.mapper.DevPostMapper;
import server.mainproject.post.service.DevPostService;
import server.mainproject.response.MultiResponse;
import server.mainproject.tag.Post_TagRepository;
import server.mainproject.tag.TagRepository;
import server.mainproject.utils.URICreator;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

// todo : 회원이 탈퇴되도 글은 유지.
@RestController
@RequestMapping("/post")
@Validated
@RequiredArgsConstructor
public class DevPostController {
    private final DevPostService service;
    private final DevPostMapper mapper;
    private final TagRepository tagRepository;
    private final Post_TagRepository ptr;

    @PostMapping
    public ResponseEntity postPost(@RequestBody @Valid DevPostDto.Post post) {

        DevPost create = service.savePost(post);
        URI uri = URICreator.createUri("/post", create.getPostId());

        return ResponseEntity.created(uri).build();
    }
    @PatchMapping("/{post-id}/edit/{member-id}")    // todo : security 적용 후 memberId 는 제거
    public ResponseEntity patchPost(@PathVariable("post-id") @Positive long postId,
                                    @PathVariable("member-id") @Positive long memberId,
                                    @RequestBody @Valid DevPostPatchDto patch) {
        patch.setMemberId(memberId);

        return new ResponseEntity(mapper.EntityToResponse(service.updatePost(patch, postId)), HttpStatus.OK);
    }
    // 최신 글 순
    @GetMapping("/new_post") // page : 1 size : 9-16 1페이지에 9-16 개 정도의 게시물. 일단 16으로
    public ResponseEntity getAllNewPost() {

        List<DevPost> posts = service.findAllPost ();

        return new ResponseEntity<>(mapper.ListResponse(posts), HttpStatus.OK);
    }
    // 평점이 높은 순
    @GetMapping("/top_review")
    public ResponseEntity getAllTopPost () {

        List<DevPost> posts = service.findAllTopPost ();

        return new ResponseEntity<>(mapper.ListResponse(posts), HttpStatus.OK);
    }
    @GetMapping("/{post-id}")
    public ResponseEntity getPost (@PathVariable("post-id") @Positive long postId) {

        DevPost find = service.findPost(postId);

        return new ResponseEntity(mapper.EntityToResponse(find), HttpStatus.OK);
    }
    @DeleteMapping("/{post-id}/{member-id}") // todo : security 적용 후 member-id 는 제거
    public ResponseEntity deletePost (@PathVariable("post-id") @Positive long postId,
                                      @PathVariable("member-id") @Positive long memberId) {

        service.deletePost(postId, memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{post-id}/recommends/{member-id}")    // todo : security 적용 후 member 제거
    public ResponseEntity recommendsPost(@PathVariable("post-id") @Positive long postId,
                                         @PathVariable("member-id") @Positive long memberId) {

        Recommend create = service.saveRecommend(postId, memberId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{post-id}/recommends/{member-id}")      // todo : member 제거
    public ResponseEntity unRecommendsPost(@PathVariable("post-id") @Positive long postId,
                                           @PathVariable("member-id") @Positive long memberId) {

        service.unRecommendPost(postId, memberId);
        return ResponseEntity.ok().build();
    }

}
