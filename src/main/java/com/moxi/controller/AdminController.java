package com.moxi.controller;

import javax.servlet.http.HttpServletRequest;
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
	public String registerSave(Model model) {
		return "register";
	}

	@PostMapping("/admin/registerSave")
	public String registerSave(Model model, Tester tester, HttpSession httpSession) {

		try {
			int num = testerMapper.selectTesterExists(tester.getSeriesNumber(), tester.getIDCard());
			int numOfAccount = testerMapper.selectAccountExists(tester.getAccount());

			if (numOfAccount > 0){
				model.addAttribute("error", "该用户本次考试已经注册过了！");
				return "login";
			}
			if (num == 0) {
				testerMapper.insertTester(tester);
			}else {
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

	@GetMapping("/admin/modify")
	public String modify(Model model) {
		System.out.println("come into modify part");
		return "modifyPassword";
	}

	@PostMapping("/admin/modify")
	public String modify(String userName, String seriesNumber, String password, Model model, HttpSession httpSession) {

		int numOfAccount = testerMapper.selectAccountExists(userName);
		if (numOfAccount > 0) {
			testerMapper.updatePassword(userName, seriesNumber, password);
			return "login";
		}else {
			httpSession.setAttribute("error", "不存在该账户");
			model.addAttribute("error", "已经注册过信息");
			return "modifyPassword";
		}
	}

	/**
	 * 登录
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
	public String personalInfoSave(HttpServletRequest request, PersonalInfomation personalInfomation, HttpSession httpSession) {
		logger.info("come into personal information part");
		String pathTemp = request.getSession().getServletContext().getRealPath("/upload/");
		String path = pathTemp.replace("webapp", "resources/static") + personalInfomation.getCardId() + ".jpg";
		String[] picFiles = path.split("/");
		String pic = "../upload/" + "/" + picFiles[picFiles.length - 1];
		// check status, 是否已经录入过信息
		try {

			int num = testerMapper.getIfPersonalInfoExit(personalInfomation.getSeriesNumber(), personalInfomation.getCardId());
			if (num > 0) {
				httpSession.setAttribute("error", "已经注册过信息");
				return "apply";
			}else {


				testerMapper.saveTesterInfo(personalInfomation);
				testerMapper.updatePicLoad(pic, personalInfomation.getCardId());
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("error " + e.getMessage() + " " + personalInfomation.getSeriesNumber());
			return "apply";
		}

		// 到上传图片页面
		return "files/uploadpic";
	}

	@GetMapping("/admin/show")
	public String test(Model model) {
		System.out.println("come into modify part");
		return "show";
	}

}
