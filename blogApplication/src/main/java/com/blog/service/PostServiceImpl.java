package com.blog.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exceptions.PostNotFoundException;
import com.blog.model.Post;
import com.blog.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Set<Post> getAllPosts() {
        return new HashSet<>(postRepository.findAll());
    }

    @Override
    public Post getPostById(Long postId) throws PostNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));
    }

    @Override
    public Post createPost(Post post) {
        
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, Post postDetails) throws PostNotFoundException {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

       
        existingPost.setTitle(postDetails.getTitle());

        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(Long postId) throws PostNotFoundException {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        postRepository.delete(existingPost);
    }
}

