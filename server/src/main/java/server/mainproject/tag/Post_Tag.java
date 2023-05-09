package server.mainproject.tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.mainproject.post.entity.DevPost;

import javax.persistence.*;

@Entity @Getter @Setter
@NoArgsConstructor
public class Post_Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postTagId;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private DevPost post;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

}
