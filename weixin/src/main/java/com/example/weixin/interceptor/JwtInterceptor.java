package com.example.weixin.interceptor;

import com.example.weixin.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qin
 * @date 2020-06-22
 */

public class JwtInterceptor implements HandlerInterceptor {


    private Integer shortTokenTime = 60;

    private Integer longTokenTime = 120;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("nju-token");
        String longToken = request.getHeader("nju-long-token");
//      如果短期token超时，长期token未超时 带上请求头
        if(token!=null&&longToken!=null){
            Integer userId = JwtUtil.verifyTokenAndGetUserId(token);
            Integer userId2 = JwtUtil.verifyTokenAndGetUserId(longToken);
            Integer userType2=JwtUtil.verifyTokenAndGetUserType(longToken);
            if(userId==null&&userId2!=null){
                //短token时长20min 长token时长60min
                response.setHeader("nju-token",JwtUtil.createToken(userId2,userType2,shortTokenTime));
                response.setHeader("nju-long-token",JwtUtil.createToken(userId2,userType2,longTokenTime));
            }
        }
        //对长期token进行验证
        if(longToken!=null){
            Integer iuserId = JwtUtil.verifyTokenAndGetUserId(longToken);
            if(iuserId!=null){
                return true;
            }
        }
        return false;
    }
}
