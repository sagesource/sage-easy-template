package org.sagesource.web.controller;

import org.sagesource.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>首页Controller</p>
 * <pre>
 *     author      XueQi
 *     date        2017/3/14
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@Controller
public class IndexController extends BaseController {

	/**
	 * 首页入口
	 *
	 * @return <p>
	 * ${user}:用户名
	 * ${date}:时间</p>
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpSession session, Model model) {
		model.addAttribute("user", "tester");
		model.addAttribute("datetime", new Date());
		model.addAttribute("session", session);

		return "index";
	}

	/**
	 * 登录页入口
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	/**
	 * 管理页面入口
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		return "admin";
	}
}
