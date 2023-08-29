package com.biabuluo.handler;

import com.alibaba.fastjson.JSON;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.enums.AppHttpCodeEnum;
import com.biabuluo.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
@Override
public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH,"权限错误");
    String json = JSON.toJSONString(result);
    WebUtils.renderString(response,json);
  }
}
