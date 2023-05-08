package server.mainproject.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mainproject.post.entity.DevPost;

public interface DevPostRepository extends JpaRepository<DevPost, Long> {
}
