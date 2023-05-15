package server.mainproject.tag;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity @Getter
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column @Setter
    private String name;

    @OneToMany(mappedBy = "tag")
    private Set<Post_Tag> postTags = new HashSet<>();

}
