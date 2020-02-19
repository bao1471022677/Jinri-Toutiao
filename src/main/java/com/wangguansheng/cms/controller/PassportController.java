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
	 * @Description: 去登录
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
	 * @Description: 登录
	 * @param user
	 * @return
	 * @return: String
	 */
	@PostMapping("login")
	public String login(Model model, User user,HttpServletResponse response, HttpSession session) {
		try {
			User u = userService.login(user);
			//如果用户勾选了 【记住我】
			if(StringUtil.hasText(user.getIsRemember())) {
				CookieUtil.addCookie(response,"username", u.getUsername(), 60 * 60 * 24 * 30);//存一个月
				CookieUtil.addCookie(response,"password", u.getPassword(), 60 * 60 * 24 * 30);//存一个月
			}
			
			// 根据角色进入不同的页面
			if("0".equals(u.getRole())){//普通用户,进入个人中心
				//登录成功.存入session
				session.setAttribute("user", u);
				return "redirect:/my/index";
			}else {
				//登录成功.存入session
				session.setAttribute("admin", u);
				return "redirect:/admin/index";//管理员
			}
			
		} catch (CMSException e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "系统异常,请于管理员联系");
		}
		
		return "passport/login";
	}
	/**
	 * 
	 * @Title: reg
	 * @Description: 去注册
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
			redirectAttributes.addFlashAttribute("message", "恭喜,注册成功");
			return "redirect:/passport/login";// 注册成功,重定向到登录页面
		

	}
}
	
