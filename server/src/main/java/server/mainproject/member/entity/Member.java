package server.mainproject.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import server.mainproject.comment.entity.Comment;
import server.mainproject.audit.Auditable;
import server.mainproject.post.entity.DevPost;
import server.mainproject.post.entity.Recommend;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Entity @Builder
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String userName;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<DevPost> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Recommend> recommends = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Comment> answers = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    private String provider;
}
