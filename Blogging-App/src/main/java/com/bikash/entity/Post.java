package com.bikash.entity;


import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "BLOG_POST_TBL")
@SQLDelete(sql = "update blog_post_tbl set status='INACTIVE' where post_Id=?")
//Above query will execute whenever any delete request come

@SQLRestriction(value = "status <> 'INACTIVE'")
public class Post {
	
	@Id
	@SequenceGenerator(name = "blog-seq1" , sequenceName = "BOLG_POST_SEQ" , initialValue = 1000 , allocationSize = 1)
	@GeneratedValue(generator = "blog-seq1" , strategy = GenerationType.SEQUENCE)
	private Integer postId;
	
	private String title;
	
	private String shortDesc;
	
	@Lob
	private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate postCreatedOn;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate postUpdatedOn;
	
	@OneToMany(targetEntity = Comment.class,mappedBy = "post",cascade = CascadeType.REMOVE,orphanRemoval = true) //orphanRemoval means if we delete parent child also should deleted
	private List<Comment> comments;
	
	private String status="ACTIVE";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")  //userId foreign key column
	private Account account;
}
