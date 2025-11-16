package com.qfnu.forum.repository;

import com.qfnu.forum.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryIdOrderByCreatedAtDesc(Long categoryId);
    List<Post> findAllByOrderByCreatedAtDesc();
}
