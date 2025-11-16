package com.qfnu.forum.service;

import com.qfnu.forum.entity.Post;
import com.qfnu.forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Post> getPostsByCategory(Long categoryId) {
        return postRepository.findByCategoryIdOrderByCreatedAtDesc(categoryId);
    }

    public Post getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setViewCount(post.getViewCount() + 1);
            postRepository.save(post);
        }
        return post;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post post) {
        if (postRepository.existsById(id)) {
            post.setId(id);
            return postRepository.save(post);
        }
        return null;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Post likePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setLikeCount(post.getLikeCount() + 1);
            return postRepository.save(post);
        }
        return null;
    }
}
