package org.sagesource.web.controller;

import org.sagesource.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
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
	public String index(Model model) throws SQLException {
		model.addAttribute("user", "tester");
		model.addAttribute("datetime", new Date());

		return "index";
	}

}
