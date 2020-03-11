package com.linkstec.mvc.exception.handler;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.JWT;
import com.linkstec.mvc.annotation.Authorization;
import com.linkstec.mvc.exception.UnAuthorizationException;
import com.linkstec.mvc.token.Constants;
import com.linkstec.mvc.token.TokenManager;

@Component
public class AuthHandler extends HandlerInterceptorAdapter {
	
   @Autowired
   private TokenManager manager;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
    
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        
        //verify the token
        if (!StringUtils.isEmpty(token) && manager.verified(token)) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(Constants.CURRENT_USER_ID,  JWT.decode(token).getAudience().get(0));
            return true;
        }
        //verified token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new UnAuthorizationException("需要验证！");
        }
        return true;
    }

    
}
