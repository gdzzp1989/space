package com.zcloud.space.common.security.component;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcloud.space.common.core.constant.Constants;
import com.zcloud.space.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description 用来解决匿名用户访问无权限资源时的异常
 * @Author
 * @Date
 */
@Slf4j
@Component
@AllArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding(Constants.UTF8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        R<String> result = new R<>();
        result.setCode(Constants.FAIL);
        if (authException != null) {
            result.setMsg(authException.getMessage());
            result.setData(authException.getMessage());
        }
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));

    }
}
