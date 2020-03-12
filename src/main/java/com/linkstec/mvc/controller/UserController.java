package com.linkstec.mvc.controller;

import com.linkstec.mvc.annotation.Authorization;
import com.linkstec.mvc.dto.UserDto;
import com.linkstec.mvc.token.Constants;
import com.linkstec.mvc.token.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private TokenManager tm;

	@PostMapping("/login")
	public String login(@RequestParam("username") String id, @RequestParam("password") String pwd, Model model) {
//		compare user/pwd with database data
		log.info("user id: {} loged successfully.", id);
		UserDto user = new UserDto();
		user.setId(id);
//		auth pass
		String token = tm.generate(user);
		model.addAttribute("token", token);
		log.info("token sent out: {}, for user id {}", token, id);
		return "jsp/hello";
	}


	@Authorization
	@GetMapping("/")
	public String access(HttpServletRequest request, Model model) {
		String id = (String) request.getAttribute(Constants.CURRENT_USER_ID);
		log.info("current user id: {}", id);
		UserDto user = new UserDto();
		user.setId(id);
		model.addAttribute("user", user);
		return "jsp/user";
	}
}
