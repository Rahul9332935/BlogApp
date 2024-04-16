package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
