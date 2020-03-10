package com.linkstec.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Created by panyilin on 2019/5/19.
 */
@Configuration 
public class MvcConfig implements WebMvcConfigurer {
	
	/**
	 * jsp 视图解析器
	 * @return
	 */
	 @Bean
     public ViewResolver viewResolver() {
         InternalResourceViewResolver resolver = new InternalResourceViewResolver();
         resolver.setPrefix("/WEB-INF/");
         resolver.setSuffix(".jsp");
         //view name pattern
         //this means view names which suits below pattern will use jsp template engine.
         resolver.setViewNames("jsp/*");
         resolver.setOrder(2);
         return resolver;
     }
	 
	 
     @Bean
     public ITemplateResolver templateResolver() {
         SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
         templateResolver.setTemplateMode("HTML5");
         templateResolver.setPrefix("/WEB-INF/");
         templateResolver.setSuffix(".html");
         templateResolver.setCharacterEncoding("utf-8");
         templateResolver.setCacheable(false);
         return templateResolver;
     }

     @Bean
     public SpringTemplateEngine templateEngine() {
         SpringTemplateEngine templateEngine = new SpringTemplateEngine();
         templateEngine.setTemplateResolver(templateResolver());
         return templateEngine;
     }

     /**
      * thymeleaf 视图解析器。
      * @return
      */
     @Bean
     public ThymeleafViewResolver viewResolverThymeLeaf() {
         ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
         viewResolver.setTemplateEngine(templateEngine());
         viewResolver.setCharacterEncoding("utf-8");
         //view name pattern
         viewResolver.setViewNames(new String[]{"thymeleaf/*"});
         viewResolver.setOrder(1);
         return viewResolver;
     }
     
     
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
 		 registry.addViewController("/login").setViewName("jsp/hello");
 		 registry.addViewController("/index").setViewName("jsp/hello");
 		 registry.addViewController("/").setViewName("thymeleaf/login"); 
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**").addResourceLocations("classpath:/jsp/");
//		super.addResourceHandlers(registry);
	}

	
}
