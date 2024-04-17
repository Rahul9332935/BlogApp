package com.blog.service;

import java.util.Set;

import com.blog.exceptions.PostNotFoundException;
import com.blog.model.Post;

public interface PostService {

    Set<Post> getAllPosts();

    Post getPostById(Long postId) throws PostNotFoundException;

    Post createPost(Post post);

    Post updatePost(Long postId, Post postDetails) throws PostNotFoundException;

    void deletePost(Long postId) throws PostNotFoundException;
}
