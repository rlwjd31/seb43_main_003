package server.mainproject.tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.mainproject.post.entity.Post;

import javax.persistence.*;

@Entity @Getter @Setter
@NoArgsConstructor
public class Post_Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postTagId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

}
