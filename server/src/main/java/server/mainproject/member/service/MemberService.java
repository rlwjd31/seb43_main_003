package server.mainproject.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public Member createMember(Member member) {

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {

        Member findMember = memberRepository.findByMemberId(member.getMemberId());

        Optional.ofNullable(member.getNickName())
                .ifPresent(findMember::setNickName);
        Optional.ofNullable(member.getUserName())
                .ifPresent(findMember::setUserName);
        Optional.ofNullable(member.getPassword())
                .ifPresent(findMember::setPassword);
        Optional.ofNullable(member.getProfileImage())
                .ifPresent(findMember::setProfileImage);

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


}
