package com.linkstec.mvc.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtils {

	public static String delete(String url, Map<String, String> param) {
		return execute(url, param, String.class, HttpMethod.DELETE, null);
	}
	
	public static String put(String url, Map<String, String> param) {
		return execute(url, param, String.class, HttpMethod.PUT, null);
	}
	
	public static String post(String url, Map<String, String> param) {
		return execute(url, param, String.class, HttpMethod.POST, null);
	}
	
	public static String get(String url, Map<String, String> param) {
		return execute(url, param, String.class, HttpMethod.GET, null);
	}
	
	public static HttpHeaders head(String url) {
		return execute(url, new HashMap<String,String>(), HttpHeaders.class, HttpMethod.HEAD, null);
	}
	
	public static Set<HttpMethod> options(String url) {
		return execute(url, new HashMap<String,String>(), Set.class, HttpMethod.OPTIONS, null);
	}
	
	public static <T> T execute(String url, Map<String, String> param, Class<T> clazz, HttpMethod m, String token) {
		RestTemplate restTemplate = new RestTemplate();
		T response = null;
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		param.forEach((String t, String u) -> {
				paramMap.add(t, u);
			}
		 ); 
		
		StringBuilder sb = new StringBuilder(url);
		sb.append("?");
		param.forEach((k,v) -> {
			sb.append(String.format("%s=%s&", k,v));
		});
		String reqUrl = sb.toString();
		
		HttpHeaders header = new HttpHeaders();  
//	    header.setContentType();
		header.add("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYiIsImV4cCI6MTU4MzkxODA0NH0.RC0ghOvCB3f2MxQDPEufYKU3UwpGQKUM_7o55ty4qB0");
		if(m == HttpMethod.DELETE || m == HttpMethod.GET || m == HttpMethod.POST || m == HttpMethod.PUT ) {
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, header);
		    ResponseEntity<T> res = restTemplate.exchange(reqUrl, m, requestEntity, clazz);
		    return res.getBody();
		}else if(m == HttpMethod.HEAD) {
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, header);
		    ResponseEntity<T> res = restTemplate.exchange(reqUrl, m, requestEntity, clazz);
		    return (T) res.getHeaders();
		}else if(m == HttpMethod.OPTIONS) {
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, header);
		    ResponseEntity<T> res = restTemplate.exchange(reqUrl, m, requestEntity, clazz);
		    return (T) res.getHeaders().getAllow();
		}
		return response; 
	}
}
