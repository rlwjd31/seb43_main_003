package server.mainproject.answer.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mainproject.answer.entity.DevAnswer;
import server.mainproject.answer.repository.DevAnswerRepository;
import server.mainproject.exception.BusinessLogicException;
import server.mainproject.exception.ExceptionCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
@Service
public class DevAnswerService {
    private DevAnswerRepository devAnswerRepository;

    public DevAnswerService(DevAnswerRepository devAnswerRepository) {
        this.devAnswerRepository = devAnswerRepository;
    }

    public DevAnswer createDevAnswer(DevAnswer devAnswer) {  // 생성
        return devAnswerRepository.save(devAnswer);
    }

    public DevAnswer updateDevAnswer(DevAnswer devAnswer) {  // 댓글과 별점 수정
        DevAnswer findDevAnswer = findVerifiedDevAnswer(devAnswer.getDevAnswerId());

        Optional.ofNullable(devAnswer.getContent())
                .ifPresent(content -> findDevAnswer.setContent(content));
        Optional.ofNullable(devAnswer.getReview())
                .ifPresent(review -> findDevAnswer.setReview(review));

        findDevAnswer.setModifiedAt(LocalDateTime.now());

        return devAnswerRepository.save(findDevAnswer);
    }

    public List<DevAnswer> findDevAnswers() {  // 모든 댓글 조회

        return devAnswerRepository.findAll();
    }

    public void deleteDevAnswer(long devAnswerId) {  // 특정 댓글 삭제
        devAnswerRepository.deleteById(devAnswerId);
    }

    public DevAnswer findVerifiedDevAnswer(long devAnswerId) {  // 해당 댓글의 존재 유무 체크
        DevAnswer devAnswer = devAnswerRepository.findById(devAnswerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return devAnswer;
    }
}
