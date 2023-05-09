package server.mainproject.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.mainproject.member.entity.Member;
import server.mainproject.post.entity.DevPost;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    private String content;
    private int star;  // 별점
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private DevPost devPost;

    public long getMemberId() {
        return member.getMemberId();
    }

    public String getUserName() {
        return member.getUserName();
    }

//    public String getProfileImage() {
//        return member.getProfileImage();
//    }

    public long getPostId() {
        return devPost.getPostId();
    }
}
