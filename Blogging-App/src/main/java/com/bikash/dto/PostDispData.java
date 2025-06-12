package com.bikash.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PostDispData {
	
	public String title;
	public String shortDesc;
	public LocalDate postCreatedOn;
}

