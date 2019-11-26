package com.bw.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.cms.domain.Article;
import com.bw.cms.domain.Links;
import com.bw.cms.service.ArticleService;
import com.bw.cms.service.LinksService;
import com.bw.cms.utils.Result;
import com.bw.cms.utils.ResultUtil;
import com.github.pagehelper.PageInfo;

@RequestMapping("admin")
@Controller
public class AdminController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private LinksService linksService;

	@RequestMapping("index")
	public String IndexController() {
		// ҳ����ת
		return "admin/index";
	}

	// ��ѯ��������
	@RequestMapping("article")
	public String detail(Model model, Integer id) {
		Article articleDetails = articleService.selectByPrimaryKey(id);
		model.addAttribute("articleDetails", articleDetails);
		return "admin/article/articleDetails";
	}
	
	//�������� ��ѯ �б�
	@GetMapping("queryLinks")
	public String selects(Model model,@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		PageInfo<Links> info = linksService.selects(page, pageSize);	
		model.addAttribute("info", info);
		return "admin/links/links";
	}
	
	//��ת ��� ҳ��  �����������
	@GetMapping("addLinks")
	public String add() {
		return "admin/links/addLinks";
		
	}
	
	//���  ��������
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping("links/add")
	public Result<Links> add(Links links){
		linksService.insert(links);
		return ResultUtil.success();
	}
	

}
