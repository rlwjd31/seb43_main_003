package server.mainproject.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
//@AllArgsConstructor
public class AuthorResponseDto {
//    private Long memberId;
    private String name;
    private int star;
    private String profileImage;
}
