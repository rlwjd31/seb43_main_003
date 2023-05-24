package server.mainproject.member;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import server.mainproject.comment.dto.CommentDto;
import server.mainproject.exception.BusinessLogicException;
import server.mainproject.exception.ExceptionCode;
import server.mainproject.member.dto.AuthorResponseDto;
import server.mainproject.member.dto.MemberDto;
import server.mainproject.member.entity.Member;
import server.mainproject.member.mapper.MemberMapper;
import server.mainproject.member.repository.MemberRepository;
import server.mainproject.member.service.MemberService;
import server.mainproject.post.dto.DevPostDto;
import server.mainproject.post.dto.RecommendResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private MemberMapper mapper;

    @Test
    void postMemberTest() throws Exception {
        // given
        MemberDto.Post post = new MemberDto.Post(
                "hgd@gmail.com",
                "hgd123!@",
                "홍길동",
                1);

        Member member = new Member();
        member.setMemberId(1L);

        given(mapper.memberPostDtoToMember(
                Mockito.any(MemberDto.Post.class)))
                .willReturn(new Member());

        // given()은 Mock 객체 특정 값을 리턴하는 동작 지정
        // Mockito의 when()과 동일 기능
        given(memberService.createMember(
                Mockito.any(Member.class)))
                .willReturn(member); // .willReturn()은 리턴할 stub 데이터

        String content = gson.toJson(post);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/members"))));
    }

    @Test
    void patchMemberTest() throws Exception {
        // given
        MemberDto.Patch patch = new MemberDto.Patch(
                1,
                "홍길동",
                "hgd123!@",
                1);


        given(mapper.memberPatchDtoToMember(
                Mockito.any(MemberDto.Patch.class)))
                .willReturn(new Member());

        // given()은 Mock 객체 특정 값을 리턴하는 동작 지정
        // Mockito의 when()과 동일 기능
        given(memberService.updateMember(
                Mockito.any(Member.class)))
                .willReturn(new Member());

        AuthorResponseDto author = AuthorResponseDto.builder()
                .name("홍길동")
                .star(5)
                .profileImgNum(1)
                .build();

        List<String> tags = new ArrayList<>();
        tags.add("content");
        tags.add("youtube");

        List<CommentDto.ResponseComment> comments = new ArrayList<>();
        comments.add(new CommentDto.ResponseComment(
                "success",
                1L,
                1L,
                "comment",
                5,
                author,
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        List<DevPostDto.Response> posts =  new ArrayList<>();
        posts.add(new DevPostDto.Response(
                "success",
                1L,
                "title",
                "content",
                "URL",
                5,
                "thumbNail",
                4.0,
                5,
                "youtube",
                "video",
                author,
                tags,
                comments,
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        List<RecommendResponseDto> recommends = new ArrayList<>();
        recommends.add(new RecommendResponseDto(
                1L,
                1L,
                1L,
                "title",
                "link",
                5,
                4.0,
                5,
                author,
                tags,
                comments
        ));

        MemberDto.Response response = new MemberDto.Response(
                "success",
                1,
                "hgd@gmail.com",
                "홍길동",
                1,
                posts,
                comments,
                recommends,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        given(mapper.memberToMemberResponseDto(
                Mockito.any(Member.class)))
                .willReturn(response);

        String content = gson.toJson(patch);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/members/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userName").value(patch.getUserName()))
                .andExpect(jsonPath("$.data.profileImgNum").value(patch.getProfileImgNum()))
                .andExpect(jsonPath("$.data.memberId").value(patch.getMemberId()));

    }
}
