package server.mainproject.answer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.mainproject.member.entity.Member;
import server.mainproject.post.entity.Post;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DevAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long devAnswerId;
    private String content;
    private int review;  // 별점
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    public long getMemberId() {
        return member.getMemberId();
    }

    public String getNickName() {
        return member.getNickName();
    }

    public String getProfileImage() {
        return member.getProfileImage();
    }

    public long getPostId() {
        return post.getPostId();
    }
}
