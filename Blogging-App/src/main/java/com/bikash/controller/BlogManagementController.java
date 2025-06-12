package com.bikash.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bikash.constant.AppConst;
import com.bikash.dto.CommentFormData;
import com.bikash.dto.PostFormData;
import com.bikash.entity.Comment;
import com.bikash.entity.Post;
import com.bikash.service.IBlogManagementService;

@Controller
public class BlogManagementController {
	
	
	@Autowired
	private IBlogManagementService blogService;
	
	@GetMapping("/dashboard")
	public String loadDashboardPage(Map<String,Object> map)
	{
		List<Post> postList=blogService.getUserPost();
		map.put(AppConst.STR_POST_LIST, postList);
		return "dashboard";
	}
	
	@GetMapping("/filterdashboard")
	public String loadDashboardPageWithFilter(@RequestParam String keyword,Map<String,Object> map)
	{
		List<Post> postList=blogService.findFilterPost(keyword);
		map.put(AppConst.STR_POST_LIST, postList);
		if(postList.isEmpty())
		{
			map.put(AppConst.STR_ERROR_MSG,AppConst.STR_RECORD_NOT_FOUND);
		}
		return "dashboardwithfilter";
	}
	
	@GetMapping("/createpost")
	public String loadPostCreationPage(@ModelAttribute("post") PostFormData postData)
	{
		return "createpost";
	}
	
	@PostMapping("/createpost")
	public String postCreationData(@ModelAttribute("post") PostFormData postData,RedirectAttributes red)
	{
		String result=blogService.postCreated(postData);
		red.addFlashAttribute(AppConst.STR_SUCCESS_MSG,result);
		return "redirect:/createpost";
	}
	
	@GetMapping("/logout")
	public String loadHomePage()
	{
		blogService.logout();
		return "forward:/";
	}
	
	@GetMapping("/editpost")
	public String loadEditPost(@RequestParam Integer postId,Map<String,Object> map)
	{
		Post post=blogService.getPostForEdit(postId);
		map.put(AppConst.STR_POST_DATA,post);
		return "editpost";
	}
	
	@GetMapping("/deletepost")
	public String deletePost(@RequestParam Integer postId,Map<String,Object> map)
	{
		Boolean status=blogService.deletePost(postId);
		if(status)
		{
			map.put(AppConst.STR_SUCCESS_MSG,AppConst.STR_POST_DELETED);
			return "forward:/dashboard";
		}
		map.put(AppConst.STR_ERROR_MSG,AppConst.STR_POST_NOT_FOUND_TO_DLT);
		return "forward:/dashboard";
	}
	
	@GetMapping("/deletecomment")
	public String deleteComment(@RequestParam Integer commentId,Map<String,Object> map)
	{
		Boolean status=blogService.deleteComment(commentId);
		if(status)
		{
			map.put(AppConst.STR_SUCCESS_MSG,AppConst.STR_CMT_DELETED);
			return "forward:/allcomments";
		}
		map.put(AppConst.STR_ERROR_MSG,AppConst.STR_CMT_NOT_FOUND_TO_DLT);
		return "forward:/allcomments";
	}
	
	@GetMapping("/readmore")
	public String loadReadMorePage(@ModelAttribute("comment") CommentFormData commentData,@RequestParam Integer postId,Map<String,Object> map)
	{
		Post post=blogService.getPostForEdit(postId);
		map.put(AppConst.STR_POST_DATA,post);
		return "postwithcomments";
	}
	
	@PostMapping("/addcomment")
	public String addComment(@ModelAttribute("comment") CommentFormData commentData,RedirectAttributes red)
	{
		blogService.addComment(commentData);
		red.addFlashAttribute(AppConst.STR_SUCCESS_MSG,AppConst.STR_COMMENT_ADDED);
		red.addFlashAttribute(AppConst.STR_POST_ID,commentData.getPostId());
		return "redirect:/readmore?postId="+commentData.getPostId();
	}
	
	@GetMapping("/allcomments")
	public String loadAllComments(Map<String,Object> map)
	{
		List<Comment> comments=blogService.getAllComments();
		map.put(AppConst.STR_COMMENT_LIST,comments);
		return "comments";
	}
	@GetMapping("/filtercomments")
	public String loadFilterComments(@RequestParam String keyword,Map<String,Object> map)
	{
		List<Comment> comments=blogService.getFilterComments(keyword);
		map.put(AppConst.STR_COMMENT_LIST,comments);
		if(comments.isEmpty())
		{
			map.put(AppConst.STR_ERROR_MSG,AppConst.STR_RECORD_NOT_FOUND);
		}
		return "filtercomments";
	}
	@GetMapping("/filterpost")
	public String loadPostWithFilter(@RequestParam String keyword,Map<String,Object> map)
	{
		List<Post> postList=blogService.findFilterAllPost(keyword);
		if(postList.isEmpty())
		{
			map.put(AppConst.STR_ERROR_MSG,AppConst.STR_RECORD_NOT_FOUND);
		}
		map.put(AppConst.STR_POST_LIST, postList);
		return "filterpost";
	}
}
