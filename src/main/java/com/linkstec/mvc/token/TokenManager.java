package com.linkstec.mvc.token;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.linkstec.mvc.dto.UserDto;

@Component
public class TokenManager {
  
	/**
	 * 生成token, 设置失效时间。
	 * @param user
	 * @return
	 */
	public String generate(UserDto user) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.add(Calendar.SECOND, Constants.EXPIRATION_TIME);
		String token = "";
		/*
		 * 存入信息
		 */
		token = JWT.create().withAudience(user.getId())
				.withExpiresAt(c.getTime())
				/**
				 * 密钥
				 * 
				 */
				.sign(Algorithm.HMAC256(SecretKey.key));
		return token;
	}

	/**
	 * 校验token
	 * @param token
	 * @return
	 */
	public boolean verified(String token) {
		boolean verified = true;
		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SecretKey.key)).build();
		try {
			jwtVerifier.verify(token);
		} catch (JWTVerificationException e) {
			verified = false;
			// throw new RuntimeException("401");
		}
		return verified;
	}
}
