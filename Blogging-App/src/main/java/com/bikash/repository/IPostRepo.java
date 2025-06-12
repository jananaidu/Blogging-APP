package com.bikash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bikash.entity.Account;
import com.bikash.entity.Post;


public interface IPostRepo extends JpaRepository<Post, Integer> {
	public List<Post> findByAccount(Account account);
	
	@Query("SELECT p FROM Post p WHERE (:keyword IS NULL OR p.title LIKE %:keyword% OR p.shortDesc LIKE %:keyword%)")
	public List<Post> searchPost(@Param("keyword") String keyword);
	
	@Query("SELECT p FROM Post p WHERE (:keyword IS NULL OR (p.title LIKE %:keyword% OR p.shortDesc LIKE %:keyword%)) AND p.account=:acc")
	public List<Post> searchPostWithFilter(@Param("keyword") String keyword,
								 @Param("acc") Account account);
}
