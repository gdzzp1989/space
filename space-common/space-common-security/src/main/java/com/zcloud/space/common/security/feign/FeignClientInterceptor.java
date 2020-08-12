package com.zcloud.space.common.security.feign;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.zcloud.space.common.core.constant.SecurityConstants;
import com.zcloud.space.common.security.component.IgnoreUrlProperties;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.util.Collection;

/**
 * @Description 扩展OAuth2FeignRequestInterceptor
 * @Author
 * @Date
 */
@Slf4j
public class FeignClientInterceptor extends OAuth2FeignRequestInterceptor {
    private final OAuth2ClientContext oAuth2ClientContext;
    private final AccessTokenContextRelay accessTokenContextRelay;
    @Autowired
    private IgnoreUrlProperties ignoreUrlProperties;

    /**
     * Default constructor which uses the provided OAuth2ClientContext and Bearer tokens
     * within Authorization header
     *
     * @param oAuth2ClientContext     provided context
     * @param resource                type of resource to be accessed
     * @param accessTokenContextRelay
     */
    public FeignClientInterceptor(OAuth2ClientContext oAuth2ClientContext
            , OAuth2ProtectedResourceDetails resource, AccessTokenContextRelay accessTokenContextRelay) {
        super(oAuth2ClientContext, resource);
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.accessTokenContextRelay = accessTokenContextRelay;
    }


    /**
     * Create a template with the header of provided name and extracted extract
     * 1. 如果使用 非web 请求，header 区别
     * 2. 根据authentication 还原请求token
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        boolean isIngore = false;
        if (CollectionUtil.isNotEmpty(ignoreUrlProperties.getIgnoreUrls())) {
            for (String ingoreUrl : ignoreUrlProperties.getIgnoreUrls()) {
                if(template.url().startsWith(ingoreUrl.replace("*", ""))){
                    isIngore = true;
                    break;
                }
            }
        }
        Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
        if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_IN) && isIngore) {
            return;
        }
        System.out.println(template.url());
        accessTokenContextRelay.copyToken();
        if (oAuth2ClientContext != null && oAuth2ClientContext.getAccessToken() != null) {
            super.apply(template);
        }
    }
}
