package com.bikash.entity;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "BLOG_COMMENT_TBL")
public class Comment {
	
	@Id
	@SequenceGenerator(name = "blog-seq1" , sequenceName = "BOLG_COMMENT_SEQ" , initialValue = 5000 , allocationSize = 1)
	@GeneratedValue(generator = "blog-seq1" , strategy = GenerationType.SEQUENCE)
	private Integer commentId;
	
	private String name;
	
	private String email;
	
	@Lob
	private String comments;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate postCreatedOn;
	
	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;
	

}
