package com.linkstec.mvc.controller;

import com.linkstec.mvc.convert.UserConverter;
import com.linkstec.mvc.domain.User;
import com.linkstec.mvc.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

	private static Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	private UserConverter converter;

	@PostMapping("/user")
	public UserDto post(@RequestBody UserDto u) {
		User user = converter.dto2Domain(u);
		logger.info(user.toString());

		logger.info("id:{}, name:{}", u.getId(), u.getLoginName());
		return u;
	}
	
	//test
	public static void main(String... args) {
		RestTemplate rs = new RestTemplate();
		UserDto user = new UserDto();
		user.setId("1");
		user.setLoginName("jack ma");
		user.setPwd("bluuu...");
		UserDto another = rs.postForObject("http://localhost:8083/api/user", user, UserDto.class);
		System.out.println(another == user);
		System.out.println(another.getId().equals(user.getId()));
	}
}