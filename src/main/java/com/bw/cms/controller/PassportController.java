package com.bw.cms.controller;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bw.cms.domain.User;
import com.bw.cms.service.UserService;
import com.bw.cms.vo.UserVO;

@RequestMapping("passport")
@Controller
public class PassportController {
	
	@Resource
	private UserService userService;
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(null!=session)
		session.invalidate();
		return "redirect:/passport/login";
	}
	/**
	 * 
	 * @Title: login 
	 * @Description: ȥ��¼
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	/**
	 * 
	 * @Title: login
	 * @Description: ��¼
	 * @param user
	 * @return
	 * @return: String
	 */
	@PostMapping("login")
	public String login(Model model, User user, HttpSession session) {
		User u = userService.login(user);

		if (u.getRole().equals("1")) {// 1:����Ա 0:��ͨ�û�
			session.setAttribute("admin", u);
			return "redirect:/admin/index";// ����Ա�������Ա��̨
		} else {
			session.setAttribute("user", u);
			return "redirect:/my/index";// ��ͨע������������
		}
	}
	/**
	 * 
	 * @Title: reg
	 * @Description: ȥע��
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg() {
		return "passport/reg";
	}

	@PostMapping("reg")
	public String reg(Model model, UserVO userVO, RedirectAttributes redirectAttributes) {

		    userService.insertSelective(userVO);
			redirectAttributes.addFlashAttribute("username", userVO.getUsername());
			redirectAttributes.addFlashAttribute("message", "��ϲ,ע��ɹ�");
			return "redirect:/passport/login";// ע��ɹ�,�ض��򵽵�¼ҳ��
		

	}
}
	
