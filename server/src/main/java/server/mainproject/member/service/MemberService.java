package server.mainproject.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.mainproject.auth.utils.CustomAuthorityUtils;
import server.mainproject.exception.BusinessLogicException;
import server.mainproject.exception.ExceptionCode;
import server.mainproject.member.entity.Member;
import server.mainproject.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

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


}
