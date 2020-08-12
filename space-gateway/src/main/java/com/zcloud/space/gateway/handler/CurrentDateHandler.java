package com.zcloud.space.gateway.handler;

import cn.hutool.core.date.DateUtil;
import com.zcloud.space.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Description
 * @Author
 * @Date
 */
@Slf4j
@Component
@AllArgsConstructor
public class CurrentDateHandler implements HandlerFunction<ServerResponse> {

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {

        // 转换流信息写出
        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(new R<>().setMsg("操作成功！").setData(DateUtil.now())));
    }
}
