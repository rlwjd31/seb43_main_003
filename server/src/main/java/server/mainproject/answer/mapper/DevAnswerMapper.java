package server.mainproject.answer.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import server.mainproject.answer.dto.DevAnswerDto;
import server.mainproject.answer.entity.DevAnswer;

@Component
@Mapper(componentModel = "spring")
public interface DevAnswerMapper {
    DevAnswer devAnswerPostToDevAnswer(DevAnswerDto.PostDevAnswer post);
    DevAnswer devAnswerPatchToDevAnswer(DevAnswerDto.PatchDevAnswer patch);
    DevAnswerDto.ResponseDevAnswer devAnswerToResponseDevAnswer(DevAnswer devAnswer);
//    List<DevAnswerDto.ResponseDevAnswer> devAnswersToResponseDevAnswers(List<DevAnswer> devAnswers);
}
