package com.zcloud.space.common.security.service;

import com.zcloud.space.common.core.constant.RedisCacheConstants;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @Description
 * @Author
 * @Date
 */
public class MyClientDetailsService extends JdbcClientDetailsService {
    public MyClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 重写原生方法支持redis缓存
     *
     * @param clientId
     * @return
     * @throws InvalidClientException
     */
    @Override
    @SneakyThrows
    @Cacheable(value = RedisCacheConstants.OAUTH_CLIENTS, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }
}
