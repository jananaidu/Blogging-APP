package com.bikash.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bikash.entity.Comment;

public interface ICommentRepo extends JpaRepository<Comment, Integer> {
	
	@Query("SELECT c FROM Comment c WHERE  (:keyword IS NULL OR c.comments LIKE %:keyword% OR c.email LIKE %:keyword%) AND (c.post.account.userId=:userId)")
	public List<Comment> getFilterComments(@Param("keyword") String key,
											@Param("userId") Integer userId);
}
