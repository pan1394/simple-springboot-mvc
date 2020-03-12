package com.linkstec.mvc.controller;

import com.linkstec.mvc.client.RestTemplateUtils;
import com.linkstec.mvc.convert.UserConverter;
import com.linkstec.mvc.domain.User;
import com.linkstec.mvc.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private UserConverter converter;

	@ResponseBody
	@PostMapping("/user")
	public UserDto post(@RequestBody UserDto u) {
		User user = converter.dto2Domain(u);
		log.info(user.toString());

		log.info("id:{}, name:{}", u.getId(), u.getLoginName());
		return u;
	}
	
	//test
	public static void main(String... args) {
// 		RestTemplate rs = new RestTemplate();
// 		UserDto actual = rs.postForObject("http://localhost:8083/api/user", user, UserDto.class);
		UserDto user = new UserDto();
		user.setId("1");
		user.setLoginName("jack ma");
		user.setPwd("bluuu...");

		UserDto actual = RestTemplateUtils.post("http://localhost:8083/api/user", user, UserDto.class );
		System.out.println(actual == user);
		System.out.println(actual.getId().equals(user.getId()));
	}
}
