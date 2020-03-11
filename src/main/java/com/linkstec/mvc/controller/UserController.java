package com.linkstec.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkstec.mvc.annotation.Authorization;
import com.linkstec.mvc.dto.UserDto;
import com.linkstec.mvc.token.Constants;
import com.linkstec.mvc.token.TokenManager;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private TokenManager tm;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@ResponseBody
	@PostMapping("/login")
	public String login(@RequestParam("username") String id, @RequestParam("password") String pwd) {
		logger.info("user id: {} loged successfully.", id);
		UserDto user = new UserDto();
		user.setId(id);
		String token = tm.generate(user);
		logger.info("token sent out: {}, for user id {}", token, id);
		return token;
	}
	
	@Authorization
	@GetMapping("/")
	public String access(HttpServletRequest request, Model model) {
		String id = (String) request.getAttribute(Constants.CURRENT_USER_ID);
		logger.info("current user id: {}", id);
		UserDto user = new UserDto();
		user.setId(id);
		model.addAttribute("user", user);
		return "jsp/user";
	}
}
