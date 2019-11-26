package com.bw.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.cms.domain.Category;
import com.bw.cms.service.CategoryService;

@RequestMapping("category")
@Controller
public class CategoryController {

	@Autowired
	private  CategoryService categoryService;
	
	//根据栏目查询分类
	@RequestMapping("selects")
	@ResponseBody
	private List<Category> selects(Integer channelId){
		return categoryService.selectsByChannelId(channelId);
	}
	
}
