package com.biabuluo.filter;

import com.alibaba.fastjson.JSON;
import com.biabuluo.constans.SystemConstants;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.LoginUser;
import com.biabuluo.enums.AppHttpCodeEnum;
import com.biabuluo.utils.JwtUtil;
import com.biabuluo.utils.RedisCache;
import com.biabuluo.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtauthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析toke
        Claims claims = null;
        //filter不会经过controller层的异常统一处理
        try {
             claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时 非法
            //响应告诉前端需要重新登录
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(responseResult));
            return;
        }
        String userid = claims.getSubject();
        //从redis中获取用户信息
        String redisKey = SystemConstants.REDIS_USERID_KEY_PRE +userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(responseResult));
            return;
        }
        //存入securityContextHolder
        //todo 获取权限信息封装到authentication
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
