package server.mainproject.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mainproject.post.entity.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
