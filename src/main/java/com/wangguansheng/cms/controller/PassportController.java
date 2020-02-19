package com.wangguansheng.cms.controller;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bw.utils.StringUtil;
import com.wangguansheng.cms.domain.User;
import com.wangguansheng.cms.service.UserService;
import com.wangguansheng.cms.utils.CMSException;
import com.wangguansheng.cms.utils.CookieUtil;
import com.wangguansheng.cms.vo.UserVO;

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
	public String login(Model model, User user,HttpServletResponse response, HttpSession session) {
		try {
			User u = userService.login(user);
			//����û���ѡ�� ����ס�ҡ�
			if(StringUtil.hasText(user.getIsRemember())) {
				CookieUtil.addCookie(response,"username", u.getUsername(), 60 * 60 * 24 * 30);//��һ����
				CookieUtil.addCookie(response,"password", u.getPassword(), 60 * 60 * 24 * 30);//��һ����
			}
			
			// ���ݽ�ɫ���벻ͬ��ҳ��
			if("0".equals(u.getRole())){//��ͨ�û�,�����������
				//��¼�ɹ�.����session
				session.setAttribute("user", u);
				return "redirect:/my/index";
			}else {
				//��¼�ɹ�.����session
				session.setAttribute("admin", u);
				return "redirect:/admin/index";//����Ա
			}
			
		} catch (CMSException e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "ϵͳ�쳣,���ڹ���Ա��ϵ");
		}
		
		return "passport/login";
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
	
