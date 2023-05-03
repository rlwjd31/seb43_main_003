package server.mainproject.answer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import server.mainproject.answer.dto.DevAnswerDto;
import server.mainproject.answer.entity.DevAnswer;
import server.mainproject.answer.mapper.DevAnswerMapper;
import server.mainproject.answer.service.DevAnswerService;
import server.mainproject.response.SingleResponse;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Slf4j
public class DevAnswerController {
    private final DevAnswerService service;
    private final DevAnswerMapper mapper;

    public DevAnswerController(DevAnswerService service, DevAnswerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("answers")  // 생성
    public ResponseEntity postDevAnswer(@Valid @RequestBody DevAnswerDto.PostDevAnswer post) {
        DevAnswer devAnswer = mapper.devAnswerPostToDevAnswer(post);

        DevAnswer response = service.createDevAnswer(devAnswer);

        return new ResponseEntity<>(new SingleResponse<>(mapper.devAnswerToResponseDevAnswer(response)), HttpStatus.CREATED);
    }

    @PatchMapping("answers/{devAnswer-id}")  // 수정
    public ResponseEntity patchDevAnswer(@PathVariable("devAnswer-id") @Positive long devAnswerId,
                                         @Valid @RequestBody DevAnswerDto.PatchDevAnswer patch) {
        patch.setDevAnswerId(devAnswerId);

        DevAnswer devAnswer = service.updateDevAnswer(mapper.devAnswerPatchToDevAnswer(patch));

        return new ResponseEntity<>(new SingleResponse<>(mapper.devAnswerToResponseDevAnswer(devAnswer)), HttpStatus.OK);
    }

    @GetMapping("{question-id}/answers") // 해당 질문글에 대한 모든 답변 조회
    public ResponseEntity getDevAnswers(@PathVariable("question-id") @Positive long questionId) {
        List<DevAnswer> devAnswers = service.findDevAnswers();

        return new ResponseEntity<>(new SingleResponse<>(devAnswers),  HttpStatus.OK);
    }

    @DeleteMapping("answers/{devAnswer-id}")  // 삭제
    public ResponseEntity deleteDevAnswer(@PathVariable("devAnswer-id") @Positive long devAnswerId) {
        service.deleteDevAnswer(devAnswerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
