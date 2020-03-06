package com.linkstec.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.linkstec.mvc.dto.UserEntity;
import com.linkstec.mvc.exception.MvcException;

@Controller
public class MvcController {

	@Autowired
	
    @GetMapping("/hello")
    public String hello(){   
        return "jsp/hello";
    }
    
    @GetMapping("/user/{id}")
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
        UserEntity user = new UserEntity();
        user.setLoginName("tom");
        user.setId(234);
        user.setBindType(1);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("thymeleaf/user/show");
        return mv;
    }
 
    //@RequestMapping("/error")
    public String error(){        
        return "jsp/error";
    }
}