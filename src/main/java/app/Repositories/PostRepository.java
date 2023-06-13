package app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.Models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Здесь вы можете определить любые дополнительные методы запросов, которые вам нужны
}