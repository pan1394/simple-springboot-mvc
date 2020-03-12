package com.linkstec.mvc.controller;

import com.linkstec.mvc.dto.UserDto;
import com.linkstec.mvc.exception.MvcException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class MvcController {
	
    @GetMapping("/hello")
    public String hello(){  
       log.info("test log");
    	/**
    	 * 视图名字符串。
    	 */
        return "jsp/hello";
    }
    
    @GetMapping("/{id}")
    public String dosth(@PathVariable("id") String id, Model model){   
    	if(!StringUtils.isEmpty(id)) {
    		if(id.equals("1")) {
    			model.addAttribute("user", "jack ma");
    		}else if(id.equals("2")) {
    			throw new MvcException("没有此用户");
    		}else {
    			int i = 3/0;
    		}
    	}
        return "jsp/user";
    }
    
    @GetMapping(value = "/test")
    public ModelAndView test(HttpServletRequest req) {
        UserDto user = new UserDto();
        user.setLoginName("tom");
        user.setId("asdf");
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("thymeleaf/user/show");
        return mv;
    }
}