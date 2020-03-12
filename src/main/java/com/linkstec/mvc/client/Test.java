package com.linkstec.mvc.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
 		String url = "http://localhost:8083/user";
 		String obj = RestTemplateUtils.get(url, new HashMap<>());
 		System.out.println(obj);
 		
 		url = "http://localhost:8083/1";
 		obj = RestTemplateUtils.get(url, new HashMap<>());
 		System.out.println(obj);
 		
 		url = "http://localhost:8083/user/login";
 		Map<String, String> param = new HashMap<>();
 		param.put("username", "jack");
 		param.put("password", "ds");
 		obj = RestTemplateUtils.post(url, param);
 		System.out.println(obj);
 		
		HttpHeaders s = RestTemplateUtils.head(url);
		System.out.println(s);
		
		Set<HttpMethod> ss = RestTemplateUtils.options(url);
	    ss.forEach(System.out::println) ;
	}
}
