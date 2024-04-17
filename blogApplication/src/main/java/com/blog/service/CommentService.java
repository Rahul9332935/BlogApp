package com.blog.service;

import java.util.Set;

import com.blog.exceptions.CommentNotFoundException;
import com.blog.model.Comment;

public interface CommentService {

    Set<Comment> getAllCommentsByPostId(Long postId) throws CommentNotFoundException;

    Comment createComment(Long postId, Comment comment) throws CommentNotFoundException;

    Comment updateComment(Long postId, Long commentId, Comment commentDetails) throws CommentNotFoundException;

    void deleteComment(Long postId, Long commentId) throws CommentNotFoundException;
}
