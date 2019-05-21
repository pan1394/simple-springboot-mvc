package com.linkstec.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by panyilin on 2019/5/19.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * 注册拦截器
	 * 
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry) {
	}
 
	/**
	 * 请求试图映射
	 * 
	 * @param registry
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
 		 registry.addViewController("/login").setViewName("hello");
 		 registry.addViewController("/index").setViewName("hello");
 		 registry.addViewController("/").setViewName("hello");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**").addResourceLocations("classpath:/jsp/");
//		super.addResourceHandlers(registry);
	}

	
}
