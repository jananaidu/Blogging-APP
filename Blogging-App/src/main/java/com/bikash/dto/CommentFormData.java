package com.bikash.dto;

import lombok.Data;

@Data
public class CommentFormData {
	private String name;
	private String email;
	private String comments;
	private Integer postId;
}
