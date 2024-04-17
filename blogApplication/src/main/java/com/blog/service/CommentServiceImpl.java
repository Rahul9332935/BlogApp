package com.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exceptions.CommentNotFoundException;
import com.blog.exceptions.PostNotFoundException;
import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Set<Comment> getAllCommentsByPostId(Long postId) throws CommentNotFoundException {
		Post post=  postRepository.findById(postId).orElseThrow(()->new PostNotFoundException("no post is there with id "+postId));
		
		return post.getComments();
	}

	@Override
	public Comment createComment(Long postId, Comment comment) throws CommentNotFoundException {
		Post post=  postRepository.findById(postId).orElseThrow(()->new PostNotFoundException("no post is there with id "+postId));
		Set<Comment> comments= post.getComments();
		comments.add(comment);
		post.setComments(comments);
		Comment c= commentRepository.save(comment);
		postRepository.save(post);
		return c;
	}

	@Override
	public Comment updateComment(Long postId, Long commentId, Comment commentDetails) throws CommentNotFoundException {
	    
	    Post post = postRepository.findById(postId)
	            .orElseThrow(() -> new PostNotFoundException("No post found with id: " + postId));

	    Optional<Comment> optionalComment = post.getComments().stream()
	            .filter(comment -> comment.getId().equals(commentId))
	            .findFirst();

	    if (optionalComment.isPresent()) {
	        Comment existingComment = optionalComment.get();
	        
	        existingComment.setContent(commentDetails.getContent());
	        return commentRepository.save(existingComment);
	    } else {
	        throw new CommentNotFoundException("No comment found with id: " + commentId + " in post with id: " + postId);
	    }
	}


	@Override
	public void deleteComment(Long postId, Long commentId) throws CommentNotFoundException {
	   
	    Post post = postRepository.findById(postId)
	            .orElseThrow(() -> new PostNotFoundException("No post found with id: " + postId));

	   
	    Optional<Comment> optionalComment = post.getComments().stream()
	            .filter(comment -> comment.getId().equals(commentId))
	            .findFirst();

	    if (optionalComment.isPresent()) {
	   
	        Comment commentToDelete = optionalComment.get();
	        post.getComments().remove(commentToDelete);

	        postRepository.save(post);
	    } else {
	        throw new CommentNotFoundException("No comment found with id: " + commentId + " in post with id: " + postId);
	    }
	}


}
