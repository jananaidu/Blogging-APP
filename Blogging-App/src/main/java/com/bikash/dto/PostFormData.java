package com.bikash.dto;

import lombok.Data;

@Data
public class PostFormData {
	
	private Integer postId;
	private String title;
	private String shortDesc;
	private String content;
}

