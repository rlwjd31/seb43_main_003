package server.mainproject.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Builder
@AllArgsConstructor
public class AuthorResponseDto {
    private Long memberId;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
