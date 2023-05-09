package server.mainproject.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mainproject.auth.Oauth.OauthUser;
import server.mainproject.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMemberId(Long memberId);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    Member findByUserName(String userName);

    Optional<Member> findMemberByEmailAndProvider(String email, String provider);
}
