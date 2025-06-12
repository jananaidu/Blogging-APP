package com.bikash.service;

import java.util.List;

import com.bikash.dto.CommentFormData;
import com.bikash.dto.PostFormData;
import com.bikash.entity.Comment;
import com.bikash.entity.Post;

public interface IBlogManagementService {
	public String postCreated(PostFormData post);
	public void logout();
	public List<Post> getUserPost();
	public Post getPostForEdit(Integer postId);
	public Boolean deletePost(Integer postId);
	public Boolean deleteComment(Integer postId);
	public List<Post> findAllPost();
	public List<Post> findFilterPost(String keyword);
	public List<Post> findFilterAllPost(String keyword);
	public String addComment(CommentFormData comment);
	public List<Comment> getAllComments();
	public List<Comment> getFilterComments(String keyword);
}
