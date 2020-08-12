package com.zcloud.space.upms.service;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zcloud.space.upms.entity.SysRoute;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Description 路由配置表
 * @Author
 * @Date
 */
public interface ISysRouteService extends IService<SysRoute> {
    /**
     * 获取全部路由
     *
     * @return
     */
    List<SysRoute> routes();

    /**
     * 更新路由信息
     *
     * @param routes 路由信息
     * @return
     */
    Mono<Void> updateRoutes(JSONArray routes);
}

