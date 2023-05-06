package server.mainproject.post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.mainproject.answer.entity.DevAnswer;
import server.mainproject.audit.Auditable;
import server.mainproject.member.entity.Member;
import server.mainproject.tag.Post_Tag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter
@NoArgsConstructor
public class Post extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String userName;

    @Column
    private String link;

    @Column
    private int likes;

    @Column(nullable = false)
    private int review;

    @Column
    private double allReviews;

    @Transient
    private List<String> tags;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Likes> likesList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<DevAnswer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private Set<Post_Tag> postTags = new HashSet<>();

}
