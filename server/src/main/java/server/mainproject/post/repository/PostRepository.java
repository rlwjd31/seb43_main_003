package server.mainproject.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.mainproject.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
