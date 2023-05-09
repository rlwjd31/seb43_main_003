package server.mainproject.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.mainproject.post.dto.DevPostDto;
import server.mainproject.post.entity.DevPost;
import server.mainproject.post.entity.Recommend;
import server.mainproject.post.mapper.DevPostMapper;
import server.mainproject.post.service.DevPostService;
import server.mainproject.response.MultiResponse;
import server.mainproject.tag.Tag;
import server.mainproject.tag.TagRepository;
import server.mainproject.utils.URICreator;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.ArrayList;
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

    @PostMapping
    public ResponseEntity postPost(@RequestBody @Valid DevPostDto.Post post) {

        DevPost mappPost = mapper.postToEntity(post);
        List<Tag> tags = new ArrayList<>();

        for (String tagName : post.getTag()) {
            Tag tag = tagRepository.findByName(tagName);
            if (tag == null) {
                tag = new Tag();
                tag.setName(tagName);
                tagRepository.save(tag);
            }
            tags.add(tag);
        }

        DevPost create = service.createdPost(mappPost, tags);
        URI uri = URICreator.createUri("/post", create.getPostId());

        return ResponseEntity.created(uri).build();
    }
    @PatchMapping("/{post-id}/edit/{member-id}")    // todo : security 적용 후 memberId 는 제거
    public ResponseEntity patchPost(@PathVariable("post-id") @Positive long postId,
                                    @PathVariable("member-id") @Positive long memberId,
                                    @RequestBody @Valid DevPostDto.Patch patch) {
        patch.setPostId(postId);
        patch.setMemberId(memberId);

        DevPost update = service.updatePost(mapper.patchToEntity(patch));

        return new ResponseEntity(mapper.EntityToResponse(update), HttpStatus.OK);
    }
    // 최신 글 순
    @GetMapping("/new_post") // page : 1 size : 9-16 1페이지에 9-16 개 정도의 게시물. 일단 16으로
    public ResponseEntity getAllNewPost(@RequestParam(defaultValue = "1") @Positive int page,
                                        @RequestParam(defaultValue = "16") @Positive int size) {

        Page<DevPost> posts = service.findAllPost (page -1, size);
        List<DevPost> list = posts.getContent();

        return new ResponseEntity(new MultiResponse<>(mapper.ListResponse(list), posts), HttpStatus.OK);
    }
    // 평점이 높은 순
    @GetMapping("/top_review")
    public ResponseEntity getAllTopPost (@RequestParam(defaultValue = "1") @Positive int page,
                                         @RequestParam(defaultValue = "16") @Positive int size) {
        Page<DevPost> posts = service.findAllTopPost (page -1, size);
        List<DevPost> list = posts.getContent();

        return new ResponseEntity(new MultiResponse<>(mapper.ListResponse(list), posts), HttpStatus.OK);
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
    @PostMapping("/{post-id}/likes/{member-id}")    // todo : security 적용 후 member 제거
    public ResponseEntity likesPost (@PathVariable("post-id") @Positive long postId,
                                     @PathVariable("member-id") @Positive long memberId) {

        Recommend create = service.createLikes(postId, memberId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{post-id}/likes/{member-id}")      // todo : member 제거
    public ResponseEntity unLikesPost (@PathVariable("post-id") @Positive long postId,
                                       @PathVariable("member-id") @Positive long memberId) {

        service.unLikesPost(postId, memberId);
        return ResponseEntity.ok().build();
    }

}
