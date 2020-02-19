package com.wangguansheng.cms.controller;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wangguansheng.cms.domain.Article;
import com.wangguansheng.cms.domain.Complain;
import com.wangguansheng.cms.domain.Links;
import com.wangguansheng.cms.service.ArticleService;
import com.wangguansheng.cms.service.ComplainService;
import com.wangguansheng.cms.service.LinksService;
import com.wangguansheng.cms.utils.Result;
import com.wangguansheng.cms.utils.ResultUtil;
import com.wangguansheng.cms.vo.ComplainVO;

@RequestMapping("admin")
@Controller
public class AdminController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private LinksService linksService;
	
	@Resource
	private ComplainService complainService;//�ٱ�

	@RequestMapping("index")
	public String IndexController() {
		// ҳ����ת
		return "admin/index";
	}

	// ��ѯ��������
	@RequestMapping("article")
	public String detail(Model model, Integer id) {
		//������ +1
		articleService.upHits(id);
		
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
	
	//��ѯͶ��
	@GetMapping("article/complains")
	public String complain(Model model ,ComplainVO complainVO , @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
			
		PageInfo<Complain> info = complainService.selects(complainVO, page, pageSize);
		model.addAttribute("info", info);
		return "admin/article/complains";
	}
	

}
