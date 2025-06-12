package com.bikash.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bikash.constant.AppConst;
import com.bikash.entity.Post;
import com.bikash.service.IBlogManagementService;

@Controller
public class LoadHomePage {
	
	@Autowired
	private IBlogManagementService blogService;
	
	@GetMapping("/")
	public String loadHomePage(Map<String,Object> map)
	{
		List<Post> postList=blogService.findAllPost();
		map.put(AppConst.STR_POST_LIST, postList);
		return "home";
	}
}
