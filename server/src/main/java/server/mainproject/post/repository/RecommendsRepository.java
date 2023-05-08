package server.mainproject.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mainproject.post.entity.Recommends;

public interface RecommendsRepository extends JpaRepository<Recommends, Long> {
}
