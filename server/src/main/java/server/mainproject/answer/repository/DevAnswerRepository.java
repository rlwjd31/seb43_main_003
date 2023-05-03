package server.mainproject.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mainproject.answer.entity.DevAnswer;

import java.util.Optional;

public interface DevAnswerRepository extends JpaRepository<DevAnswer, Long> {
    Optional<DevAnswer> findByDevAnswerId(long devAnswerId);
}
