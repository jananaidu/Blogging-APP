package com.bikash.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikash.constant.AppConst;
import com.bikash.dto.CommentFormData;
import com.bikash.dto.PostFormData;
import com.bikash.entity.Account;
import com.bikash.entity.Comment;
import com.bikash.entity.Post;
import com.bikash.repository.IAcconutRepo;
import com.bikash.repository.ICommentRepo;
import com.bikash.repository.IPostRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class BlogManagementServiceImpl implements IBlogManagementService {

	@Autowired
	private HttpSession session;

	@Autowired
	private IAcconutRepo accRepo;

	@Autowired
	private IPostRepo postRepo;

	@Autowired
	private ICommentRepo commentRepo;

	@Override
	public String postCreated(PostFormData post) {

		Integer postId = post.getPostId();
		// If id available then perform update operation
		if (postId != null) {
			Post postData = postRepo.findById(postId).get();
			BeanUtils.copyProperties(post, postData);
			// set the updated post time
			postData.setPostCreatedOn(LocalDate.now());

			// update the data to Post table
			postRepo.save(postData);

			// return post updated message
			return AppConst.STR_POST_UPDATED;
		}

		// If id not available then perform save operation
		Post newPost = new Post();
		BeanUtils.copyProperties(post, newPost);
		// Set post creation time
		newPost.setPostCreatedOn(LocalDate.now());
		// get id from session attributes
		int id = (int) session.getAttribute(AppConst.STR_USER_ID);
		// get account through id
		Optional<Account> opt = accRepo.findById(id);
		Account acc = opt.get();
		// set this user details to the post
		newPost.setAccount(acc);

		// save the data to Post table
		postRepo.save(newPost);

		// return post created message
		return AppConst.STR_POST_CREATED;
	}

	@Override
	public void logout() {
		// Destroy the session
		session.invalidate();
	}

	@Override
	public List<Post> getUserPost() {
		// get user details from session
		Integer id = (Integer) session.getAttribute(AppConst.STR_USER_ID);
		Account account = accRepo.findById(id).get();
		List<Post> userPost = postRepo.findByAccount(account);
		return userPost;
	}

	@Override
	public Post getPostForEdit(Integer postId) {
		Post post = postRepo.findById(postId).get();
		return post;
	}

	@Override
	public List<Post> findAllPost() {
		List<Post> allPostList = postRepo.findAll();
		return allPostList;
	}

	@Override
	public String addComment(CommentFormData comment) {

		Comment cmt = new Comment();
		BeanUtils.copyProperties(comment, cmt);

		// set comment added time
		cmt.setPostCreatedOn(LocalDate.now());

		// Get post for which comment added
		Post post = postRepo.findById(comment.getPostId()).get();
		// set post for which comment is added
		cmt.setPost(post);
		// save the comment
		commentRepo.save(cmt);
		return AppConst.STR_COMMENT_ADDED;
	}

	@Override
	public Boolean deletePost(Integer postId) {
		Optional<Post> postOpt = postRepo.findById(postId);
		if (postOpt.isPresent()) {
			Post post = postOpt.get();
			postRepo.delete(post);
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean deleteComment(Integer postId) {
		Optional<Comment> cmtOpt = commentRepo.findById(postId);
		if (cmtOpt.isPresent()) {
			Comment comment = cmtOpt.get();
			commentRepo.delete(comment);
			return true;
		}
		return false;
	}

	@Override
	public List<Comment> getAllComments() {
		Optional<Account> opt=accRepo.findById((Integer)session.getAttribute(AppConst.STR_USER_ID));
		if(opt.isPresent())
		{
			Account account=opt.get();
			List<Post> posts=postRepo.findByAccount(account);
			List<Comment> allComments=new ArrayList<Comment>();
			for(Post post : posts)
			{
				List<Comment> comments=post.getComments();
				for(Comment cmt:comments)
				{
					allComments.add(cmt);
				}
			}
			return allComments;
		}
		return Collections.emptyList();
	}
	
	@Override
	public List<Comment> getFilterComments(String keyword) {
		
		return commentRepo.getFilterComments(keyword,(Integer)session.getAttribute(AppConst.STR_USER_ID));
	}
	
	@Override
	public List<Post> findFilterPost(String keyword) {
		Optional<Account> opt=accRepo.findById((Integer)session.getAttribute(AppConst.STR_USER_ID));
		if(opt.isPresent())
		{
			Account acc=opt.get();
			return postRepo.searchPostWithFilter(keyword,acc);
		}
		return Collections.emptyList();
	}
	
	@Override
	public List<Post> findFilterAllPost(String keyword) {
			return postRepo.searchPost(keyword);
	}

}
