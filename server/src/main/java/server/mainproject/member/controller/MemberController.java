package server.mainproject.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.mainproject.exception.ExceptionCode;
import server.mainproject.member.dto.MemberDto;
import server.mainproject.member.entity.Member;
import server.mainproject.member.mapper.MemberMapper;
import server.mainproject.member.repository.MemberRepository;
import server.mainproject.member.service.MemberService;
import server.mainproject.response.SingleResponse;
import server.mainproject.utils.URICreator;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;
    private final MemberRepository memberRepository;


    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member member = mapper.memberPostDtoToMember(requestBody);

        if (memberRepository.existsByEmail(requestBody.getEmail())) {
            String errorMessage = "이메일 중복! 다른 이메일을 사용해주세요!";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }

        Member createdMember = memberService.createMember(member);

        URI uri = URICreator.createUri("/post", createdMember.getMemberId());

        return ResponseEntity.created(uri).build();
    }


    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@Valid @RequestBody MemberDto.Patch requestBody,
                                      @PathVariable("member-id") Long memberId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getPrincipal().toString()
        Member member = mapper.memberPatchDtoToMember(requestBody);
        member.setMemberId(memberId);

        Member findedMember = memberService.updateMember(member);

        return new ResponseEntity<>(
                new SingleResponse<>(mapper.memberToMemberResponseDto(findedMember)),
                HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") Long memberId) {
        Member member = memberService.findMember(memberId);
        MemberDto.Response response= mapper.memberToMemberResponseDto(member);
        response.setStatus("success");
        return new ResponseEntity<>(
                new SingleResponse<>(response),
                HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getMembers() {
        List<Member> members = memberService.findMembers();
        return new ResponseEntity<>(
                new SingleResponse<>(mapper.membersToMemberReponseDtos(members)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMembers(@PathVariable("member-id") Long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    // 이메일키 인증
//    @GetMapping("/email_auth")
//    public void getEmailAuth(@RequestParam("id")  Long id,
//                             @RequestParam("mailKey") String mailKey,
//                             HttpServletResponse response) throws IOException {
//
//        memberService.mailKeyAuth(id, mailKey);
//        String redirectUri = "http://localhost:8080";
//        response.sendRedirect(redirectUri);
//    }

    // 비밀번호 변경
//    @PatchMapping("/password")
//    public ResponseEntity updatePassword(Principal principal,
//                                         @RequestBody @Valid PasswordPatchDto passwordPatchDto) {
//        log.info("### PW PATCH 시작합니다!");
//        String password = passwordPatchDto.getPassword();
//        String afterPassword = passwordPatchDto.getAfterPassword();
//        log.info("###PW = " + password + ", AFTER PW = " + afterPassword);
//        memberService.updatePassword(principal.getName(), password, afterPassword);
//
//        return ResponseEntity.ok().build();
//    }
//
//    // 리커버리 이메일 샌드
//    @PostMapping("/recovery/signup/send")
//    public ResponseEntity recoveryEmailSend(@RequestBody @Valid RecoveryEmailSendDto dto)
//            throws MessagingException, UnsupportedEncodingException {
//        String emailSignUp = dto.getEmailSignUp();
//        String emailNeedToSend = dto.getEmailNeedToSend();
//
//        userService.recoveryEmailSend(emailSignUp, emailNeedToSend);
//
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/recovery/password/send")
//    public ResponseEntity recoveryPWEmailSend(@RequestBody @Valid RecoveryPWEmailSendDto dto)
//            throws MessagingException, UnsupportedEncodingException {
//        String email = dto.getEmail();
//
//        userService.recoveryPWEmailSend(email);
//        log.info("if문 start!");
//        if (userService.existsByEmail(email)){
//            User findUser = userService.checkUserExist(email);
//            userService.checkNotGoogleAuth(findUser);
//        }
//        return ResponseEntity.ok().build();
//    }
//
//    // 리커버리 진행
//    @PatchMapping("/recovery")
//    public ResponseEntity recovery(@RequestBody @Valid RecoveryPasswordPatchDto dto) {
//        String email = dto.getEmail();
//        String mailKey = dto.getMailKey();
//        String afterPassword = dto.getAfterPassword();
//
//        userService.recovery(email, mailKey, afterPassword);
//
//        return ResponseEntity.ok().build();
//    }
//
//    //이메일 인증 다시 보내기
//    @GetMapping("/resend")
//    public ResponseEntity resend(Principal principal)
//            throws MessagingException, UnsupportedEncodingException {
//        String email = principal.getName();
//        User findUser = userService.checkUserExist(email);
//        userService.sendEmail(email, findUser.getMailKey(), findUser.getId());
//
//        return ResponseEntity.ok().build();
//    }


}

