package server.mainproject.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "회원이 존재하지 않습니다."),
    MEMBER_EXITS(402, "존재하는 회원"),
    POST_NOT_FOUND(404, "게시글이 존재하지 않습니다."),
    POST_NOT_WRITE(402, "권한이 없습니다."),
    ANSWER_NOT_FOUND(404, "답변이 존재하지 않습니다."),
    ANSWER_NOT_MEMBER(402, "회원이 작성한 답변이 아닙니다.");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
