package com.biabuluo.handler;

import com.alibaba.fastjson.JSON;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.enums.AppHttpCodeEnum;
import com.biabuluo.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
@Override
public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR, "认证失败");
    String json = JSON.toJSONString(result);
    WebUtils.renderString(response,json);

  }
}
