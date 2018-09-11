package com.moxi.controller;

import javax.servlet.http.HttpSession;

import com.moxi.dao.TesterMapper;
import com.moxi.pojo.PersonalInfomation;
import com.moxi.pojo.Tester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);


	@Autowired
	TesterMapper testerMapper;

	@GetMapping("/admin/registerSave")
	public String registerSave(Model model, Tester tester, HttpSession httpSession) {
		System.out.print("coming to the backend of register Save");

		try {
			int num = testerMapper.selectTesterExists(tester.getSeriesNumber(), tester.getIDCard());
			int numOfAccount = testerMapper.selectAccountExists(tester.getAccount());

			if (numOfAccount > 0){
				//todo 后续action
				model.addAttribute("error", "该用户本次考试已经注册过了！");
				return "login";
			}
			if (num == 0) {
				testerMapper.insertTester(tester);
			}else {
				//todo 后续action
				model.addAttribute("error", "该账号已经被注册过了，请重试! ");
				logger.error("error", "该账号已经被注册过了，请重试! " + tester.getSeriesNumber());
				return "register";
			}
		}catch (Exception e) {
			logger.error("failure " + e.getMessage() + " " + tester.getSeriesNumber());
			logger.error(e.getMessage(), e);
			model.addAttribute("error", "发生错误");
			return "register";
		}
		return "login";
	}
	/**
	 * 登录跳转
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/login")
	public String loginGet(Model model) {
		System.out.print("coming to loginGet part");
		return "login";
	}

	/**
	 * 登录
	 * 
	 *
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@PostMapping("/admin/login")
	public String loginPost(String userName, String password, Model model, HttpSession httpSession) {
		System.out.print("coming to login post part");
//		Admin adminRes = adminService.findByNameAndPassword(admin);
		Tester tester = testerMapper.selectTesterByAccountAndPassword(userName, password);
		if (tester != null) {
			httpSession.setAttribute("admin", tester);
			// todo 登录成功后的跳转
			return "redirect:dashboard";
		} else {
			model.addAttribute("error", "用户名或密码错误，请重新登录！");

			return "login";
		}
	}



	/**
	 * 注册
	 * 修改后的
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/register")
	public String register(Tester model) {
		return "register";
	}

	/**
	 * 仪表板页面
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/dashboard")
	public String dashboard(Model model) {
		return "dashboard";
	}

	@GetMapping("admin/personalInfo")
	public String personalInfoSave(Model model){
		return "apply";
	}



	@PostMapping("admin/personalInfo")
	public String personalInfoSave(Model model, PersonalInfomation personalInfomation, HttpSession httpSession) {
		System.out.println("come into personalInfo part");
		// check status, 是否已经录入过信息
		try {
			int num = testerMapper.getIfPersonalInfoExit(personalInfomation.getSeriesNumber(), personalInfomation.getCardId());
			if (num > 0) {
				httpSession.setAttribute("error", "已经注册过信息");
				model.addAttribute("error", "已经注册过信息");
				return "apply";
			}else {
				testerMapper.saveTesterInfo(personalInfomation);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("error " + e.getMessage() + " " + personalInfomation.getSeriesNumber());
			model.addAttribute("error", e);
			return "redirect:dashboard";
		}

		// todo 如果成功，展示个人信息
		return "redirect:dashboard";
	}

}
