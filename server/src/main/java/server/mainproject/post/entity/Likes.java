package server.mainproject.post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.mainproject.audit.Auditable;
import server.mainproject.member.entity.Member;

import javax.persistence.*;

@Entity @Getter @Setter
@NoArgsConstructor
public class Likes extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long likesId;

//    @Column
//    private Boolean likeStatus = false; // 좋아요 상태. 기본값 : 해제.

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
