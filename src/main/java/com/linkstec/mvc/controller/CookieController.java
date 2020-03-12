package com.linkstec.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/cookie")
@ResponseBody
public class CookieController {

	@GetMapping("/change-username")
	public String setCookie(HttpServletResponse response) {
	    Cookie cookie = new Cookie("username", "Jovan");
	    cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
	    response.addCookie(cookie);
	    
	    return "Username is changed!";
	}
	
	//2) 使用Spring框架提供的@CookieValue注解获取特定的 cookie的值
	@GetMapping("/")
	public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
	    return "Hey! My username is " + username;
	}
	
	//3) 读取所有的 Cookie 值
	@GetMapping("/all-cookies")
	public String readAllCookies(HttpServletRequest request) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        return Arrays.stream(cookies)
	                .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
	    }
	    return "No cookies";
	}
}
