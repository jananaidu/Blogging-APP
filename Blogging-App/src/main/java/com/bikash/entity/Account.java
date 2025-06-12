package com.bikash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "BLOG_ACCOUNT_TBL")
public class Account {
	
	@Id
	@SequenceGenerator(name = "blog-seq1" , sequenceName = "BOLG_USER_SEQ" , initialValue = 100 , allocationSize = 1)
	@GeneratedValue(generator = "blog-seq1" , strategy = GenerationType.SEQUENCE)
	private Integer userId;
	
	@Column(length = 30)
	private String firstName;
	
	@Column(length = 30)
	private String lastName;
	
	@Column(length = 50)
	private String email;
	
	@Column(length = 50)
	private String password;							
}
