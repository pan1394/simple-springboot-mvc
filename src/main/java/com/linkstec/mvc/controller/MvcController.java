package com.linkstec.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.linkstec.mvc.exception.MvcException;

@Controller
public class MvcController {

    @GetMapping("/hello")
    public String hello(){        
        return "hello";
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
        return "user";
    }
    
    
    //@RequestMapping("/error")
    public String error(){        
        return "error";
    }
}