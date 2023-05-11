package server.mainproject.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mainproject.comment.entity.Comment;
import server.mainproject.exception.BusinessLogicException;
import server.mainproject.exception.ExceptionCode;
import server.mainproject.member.entity.Member;
import server.mainproject.member.mapper.MemberMapper;
import server.mainproject.member.service.MemberService;
import server.mainproject.post.entity.DevPost;
import server.mainproject.post.entity.Recommend;
import server.mainproject.post.repository.RecommendRepository;
import server.mainproject.post.repository.DevPostRepository;
import server.mainproject.tag.Post_Tag;
import server.mainproject.tag.Post_TagRepository;
import server.mainproject.tag.Tag;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DevPostService {
    private final DevPostRepository repository;
    private final RecommendRepository recommendRepository;
    private final MemberService memberService;
    private final Post_TagRepository ptr;

    public DevPost createdPost (DevPost post, List<Post_Tag> tags) {

        Member member = memberService.verifiedMember(post.getMember().getMemberId());
        post.setUserName(member.getUserName());

        DevPost savePost = repository.save(post);

        tags
                .stream()
                .map(tag -> {
                    tag.setPost(savePost);
                    return tag;
                })
                .forEach(tag -> ptr.save(tag));

        return savePost;
    }
    // 종아요 누름 기능
    public Recommend createLikes (long postId, long memberId) {

        Member member = memberService.verifiedMember(memberId);

        DevPost post = existsPost(postId);

        post.getRecommends()
                .stream()
                .filter(id -> id.getMember().getMemberId() == memberId)
                .filter(two -> two.getPost().getPostId() == postId)
                .findAny()
                .ifPresent(e -> {
                    throw new BusinessLogicException(ExceptionCode.ALREADY_LIKES);
                });

        int like = post.getRecommend();
        post.setRecommend(like + 1);

        Recommend recommend = new Recommend();
        recommend.setPost(post);
        recommend.setMember(member);

        return recommendRepository.save(recommend);
    }
    public DevPost updatePost (DevPost post) {

        Member member = memberService.verifiedMember(post.getMember().getMemberId());

        DevPost find = existsPost(post.getPostId());

        verifiedPostMember(find, member.getMemberId());

        Optional.ofNullable(post.getTitle()).ifPresent(title -> find.setTitle(title));
        Optional.ofNullable(post.getContent()).ifPresent(content -> find.setContent(content));

        if (post.getStar() != 0) {
            find.setStar(post.getStar());
        }

        Optional.ofNullable(post.getLink()).ifPresent(link -> find.setLink(link));

        find.setUserName(member.getUserName());

        return repository.save(find);
    }
    @Transactional(readOnly = true)
    public DevPost findPost (long postId) {
        DevPost post = existsPost(postId);

        double answersReview = post.getComments()
                .stream()
                .filter(id -> id.getDevPost().getPostId() == postId)
                .map(review -> review.getStar())
                .mapToDouble(avr -> avr)
                .average()
                .orElse(0.0);

        DecimalFormat df = new DecimalFormat("#.#");
        String formattedReview = df.format(answersReview);
        double roundedReview = Double.parseDouble(formattedReview);

        post.setStarAvg(roundedReview);

        return post;
    }
    // 최신글 순으로 조회
    @Transactional(readOnly = true)
    public Page<DevPost> findAllPost (int page, int size) {

       Page<DevPost> posts = repository.findAll(PageRequest.of(page, size, Sort.by("postId").descending()));

        DecimalFormat df = new DecimalFormat("#.#");

        postAnswerReviewAvg(posts, df);

        return posts;
    }

    @Transactional(readOnly = true)
    public Page<DevPost> findAllTopPost (int page, int size) {

        Page<DevPost> posts = repository.findAll(PageRequest.of(page, size));

        DecimalFormat df = new DecimalFormat("#.#");

        postAnswerReviewAvg(posts, df);

        List<DevPost> sortedReviews = new ArrayList<>(posts.getContent());
        sortedReviews.sort(Comparator.comparingDouble(DevPost::getStarAvg).reversed());

        return new PageImpl<>(sortedReviews, posts.getPageable(), posts.getTotalElements());
    }

    public void deletePost (long postId, long memberId) {

        DevPost post = existsPost(postId);

        verifiedPostMember(post, memberId);


        repository.delete(post);
    }

    public void unLikesPost (long postId, long memberId) {

        memberService.verifiedMember(memberId);
        DevPost post = existsPost(postId);

        Optional<Recommend> optional = recommendRepository.findAll()
                .stream()
                        .filter(id -> id.getMember().getMemberId() == memberId)
                                .filter(i -> i.getPost().getPostId() == postId)
                                        .findFirst();
        Recommend recommend = optional.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POST_NOT_WRITE));

        int num = post.getRecommend();
        post.setRecommend(num -1);
        updatePost(post);

        recommendRepository.delete(recommend);
    }


    public DevPost existsPost (long postId) {
        Optional<DevPost> optional = repository.findById(postId);
        DevPost findId = optional.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));

        return findId;
    }

    public void verifiedPostMember(DevPost post, long memberId) {
        if (post.getMember().getMemberId() != memberId) {
            throw new BusinessLogicException(ExceptionCode.POST_NOT_WRITE);
        }
    }
    private static void postAnswerReviewAvg(Page<DevPost> posts, DecimalFormat df) {
        posts.forEach(post -> {
            double reviews = post.getComments()
                    .stream()
                    .filter(answer -> answer.getDevPost().getPostId() == post.getPostId())
                    .mapToDouble(Comment::getStar)
                    .average()
                    .orElse(0.0);

            String format = df.format(reviews);
            double roundedReview = Double.parseDouble(format);

            post.setStarAvg(roundedReview);
        });
    }
}
