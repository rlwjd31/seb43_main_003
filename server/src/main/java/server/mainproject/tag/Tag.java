package server.mainproject.tag;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity @Getter
//@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column @Setter
    private String name;

    @OneToMany(mappedBy = "tag", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Post_Tag> postTags = new HashSet<>();

//    public Tag (String tagName) {
//    }
}
