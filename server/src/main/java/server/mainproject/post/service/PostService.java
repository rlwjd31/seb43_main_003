package server.mainproject.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mainproject.exception.BusinessLogicException;
import server.mainproject.exception.ExceptionCode;
import server.mainproject.member.entity.Member;
import server.mainproject.member.service.MemberService;
import server.mainproject.post.entity.Likes;
import server.mainproject.post.entity.Post;
import server.mainproject.post.repository.LikesRepository;
import server.mainproject.post.repository.PostRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final LikesRepository likesRepository;
    private final MemberService memberService;

    public Post createdPost (Post post) {

        Member member = memberService.verifiedMember(post.getMember().getMemberId());
        post.setUserName(member.getUserName());

//        post.setUserName("보름달");
        return repository.save(post);
    }
    // 종아요 누름 기능
    public Likes createLikes (long postId, long memberId) {

        Member member = memberService.verifiedMember(memberId);

        Post post = existsPost(postId);

        post.getLikesList()
                .stream()
                .filter(id -> id.getMember().getMemberId() == memberId)
                .filter(two -> two.getPost().getPostId() == postId)
                .findAny()
                .ifPresent(e -> {
                    throw new BusinessLogicException(ExceptionCode.ALREADY_LIKES);
                });

        int like = post.getLikes();
        post.setLikes(like + 1);

        Likes likes = new Likes();
        likes.setPost(post);
        likes.setMember(member);

        return likesRepository.save(likes);
    }
//    public Likes
    public Post updatePost (Post post) {

        Member member = memberService.verifiedMember(post.getMember().getMemberId());

        Post find = existsPost(post.getPostId());

        verifiedPostMember(find, member.getMemberId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> find.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> find.setContent(content));

        if (post.getReview() != 0) {
            find.setReview(post.getReview());
        }

        Optional.ofNullable(post.getLink()).ifPresent(link -> find.setLink(link));

        find.setUserName(member.getUserName());

        return repository.save(find);
    }
    @Transactional(readOnly = true)
    public Post findPost (long postId) {
        return existsPost(postId);
    }
    // 최신글 순으로 조회
    @Transactional(readOnly = true)
    public Page<Post> findAllPost (int page, int size) {

        return repository.findAll(PageRequest.of(page, size, Sort.by("postId").descending()));
    }
    // 회원 마이페이지에서 게시물 다 보기 추가

    public void deletePost (long postId, long memberId) {

        Post post = existsPost(postId);

        verifiedPostMember(post, memberId);


        repository.delete(post);
    }

    public void unLikesPost (long postId, long memberId) {

        memberService.verifiedMember(memberId);
        Post post = existsPost(postId);

        Optional<Likes> optional = likesRepository.findAll()
                .stream()
                        .filter(id -> id.getMember().getMemberId() == memberId)
                                .filter(i -> i.getPost().getPostId() == postId)
                                        .findFirst();
        Likes likes = optional.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POST_NOT_WRITE));

        int num = post.getLikes();
        post.setLikes(num -1);
        updatePost(post);

        likesRepository.delete(likes);
    }


    public Post existsPost (long postId) {
        Optional<Post> optional = repository.findById(postId);
        Post findId = optional.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));

        return findId;
    }
// 좋아요 post 이미 눌렀는지 확인. 좋아요 취소에 이 글의 이 멤버의 좋아요가 맞는지.
    public void verifiedPostMember(Post post, long memberId) {
        if (post.getMember().getMemberId() != memberId) {
            throw new BusinessLogicException(ExceptionCode.POST_NOT_WRITE);
        }
    }
}
