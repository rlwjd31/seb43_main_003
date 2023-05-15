package server.mainproject.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.mainproject.auth.utils.CustomAuthorityUtils;
import server.mainproject.exception.BusinessLogicException;
import server.mainproject.exception.ExceptionCode;
import server.mainproject.member.entity.Member;
import server.mainproject.member.repository.MemberRepository;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private JavaMailSender mailSender;

    public Member createMember(Member member) {

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {

        Member findMember = memberRepository.findByMemberId(member.getMemberId());

        Optional.ofNullable(member.getUserName())
                .ifPresent(findMember::setUserName);
        Optional.ofNullable(member.getPassword())
                .ifPresent(findMember::setPassword);

        //변경된 비밀번호 암호화 해서 저장
        String encryptedPassword = passwordEncoder.encode(findMember.getPassword());
        findMember.setPassword(encryptedPassword);

        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId) {

        Member findMember = memberRepository.findByMemberId(memberId);

        return findMember;

    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(long memberId) {
        memberRepository.deleteById(memberId);
    }

    public Member verifiedMember(long memberId) {
        Optional<Member> optional = memberRepository.findById(memberId);
        Member findId = optional.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findId;
    }

//    // 패스워드 변경
//    public Member updatePassword(String email, String password, String afterPassword) {
//        // 회원이 존재하는지 검증
//        Member findMember = checkMemberExist(email);
//        // 비밀번호가 일치하는지 검증
//        if (passwordEncoder.matches(password, findMember.getPassword())) {
//            findMember.setPassword(passwordEncoder.encode(afterPassword));
//            memberRepository.save(findMember);
//        } else {
//            throw new BusinessLogicException(ExceptionCode.PASSWORD_NOT_CORRECT);
//        }
//        return findMember;
//    }
//
//    // 메일 인증을 통한 리커버리
//    public Member recovery(String email, String mailKey, String afterPassword) {
//        //회원이 존재하는지 검증
//        Member findMember = checkMemberExist(email);
//        //메일 키가 일치하는지 검증
//        if (findMember.getMailKey().equals(mailKey)) {
//            findMember.setPassword(passwordEncoder.encode(afterPassword));
//            memberRepository.save(findMember);
//        } else {
//            throw new BusinessLogicException(ExceptionCode.MAILKEY_MISMATCH);
//        }
//
//        return findMember;
//    }
//
//    // recovery email send
//    @Async
//    public void recoveryEmailSend(String emailSignUp, String emailNeedToSend)
//            throws MessagingException, UnsupportedEncodingException {
//        String newMailKey = createCode();
//        Member findMember = memberRepository.findByEmail(emailSignUp));
//        if (emailSignUp.equals(emailNeedToSend)) {
//            findMember.setMailKey(newMailKey);
//            memberRepository.save(findMember);
//            sendEmailRecovery(emailSignUp, newMailKey);
//        } else {
//            sendEmailDismatch(emailNeedToSend);
//        }
//
//    }
//
//
//
//
//
//    public Member checkMemberExist(Long id) {
//        return memberRepository.findById(id)
//                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
//    }
//
//    public Member checkMemberExist(String email) {
//        return memberRepository.findByEmail(email)
//                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
//    }
//
//    public String createCode() {
//        Random random = new Random();
//        StringBuffer key = new StringBuffer();
//
//        for (int i = 0; i < 10; i++) {
//            int index = random.nextInt(3);
//
//            switch (index) {
//                case 0:
//                    key.append((char) ((int) random.nextInt(26) + 97));
//                    break;
//                case 1:
//                    key.append((char) ((int) random.nextInt(26) + 65));
//                    break;
//                case 2:
//                    key.append(random.nextInt(9));
//                    break;
//            }
//        }
//        return key.toString();
//    }
//
//    public String sendEmailRecovery(String toEmail, String mailKey)
//            throws MessagingException, UnsupportedEncodingException {
//
//        //메일전송에 필요한 정보 설정
//        MimeMessage emailForm = createEmailFormRecovery(toEmail, mailKey);
//        //실제 메일 전송
//        mailSender.send(emailForm);
//
//        return mailKey;
//    }
//
//    public MimeMessage createEmailFormRecovery(String email, String mailKey)
//            throws MessagingException, UnsupportedEncodingException {
//
////    String mailKey = createCode(); //인증 코드 생성
//        String setFrom = "${spring.mail.username}"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
////    String setFrom = "hgm@hgm.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
//        String toEmail = email; //받는 사람
//        String title = "Hello Developer 계정 복구 서비스입니다."; //제목
//        String href =
//                ""
//                        + email + "&mailKey=" + mailKey;
//
//        MimeMessage message = mailSender.createMimeMessage();
//        message.addRecipients(MimeMessage.RecipientType.TO, email); //보낼 이메일 설정
//        message.setSubject(title); //제목 설정
//        message.setFrom(setFrom); //보내는 이메일
//        message.setText(setContextRecovery(href), "utf-8", "html");
//
//        return message;
//    }
//
//    public void sendEmailDismatch(String toEmail)
//            throws MessagingException, UnsupportedEncodingException {
//
//        //메일전송에 필요한 정보 설정
//        MimeMessage emailForm = createEmailFormDismatch(toEmail);
//        //실제 메일 전송
//        mailSender.send(emailForm);
//
//    }
//
//    public MimeMessage createEmailFormDismatch(String email)
//            throws MessagingException, UnsupportedEncodingException {
//
////    String mailKey = createCode(); //인증 코드 생성
////        String setFrom = "${spring.mail.username}"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
//    String setFrom = "ksr940818@gmail.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
//        String toEmail = email; //받는 사람
//        String title = "HelloDeveloper 계정 복구 서비스입니다."; //제목
//
//
//        MimeMessage message = mailSender.createMimeMessage();
//        message.addRecipients(MimeMessage.RecipientType.TO, email); //보낼 이메일 설정
//        message.setSubject(title); //제목 설정
//        message.setFrom(setFrom); //보내는 이메일
//
//        return message;
//    }


}
