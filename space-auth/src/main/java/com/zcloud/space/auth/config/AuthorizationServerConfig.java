package com.zcloud.space.auth.config;

import com.zcloud.space.common.core.constant.Constants;
import com.zcloud.space.common.core.constant.RedisCacheConstants;
import com.zcloud.space.common.core.constant.SecurityConstants;
import com.zcloud.space.common.data.cache.MyRedisTokenStore;
import com.zcloud.space.common.data.tenant.TenantContextHolder;
import com.zcloud.space.common.security.component.MyWebResponseExceptionTranslator;
import com.zcloud.space.common.security.service.MyClientDetailsService;
import com.zcloud.space.common.security.service.MyUser;
import com.zcloud.space.common.security.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 认证服务器配置
 * @Author
 * @Date
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	private final DataSource dataSource;
	private final MyUserDetailsService myUserDetailsService;
	private final AuthenticationManager authenticationManagerBean;
	private final RedisConnectionFactory redisConnectionFactory;


	@Override
	@SneakyThrows
	public void configure(ClientDetailsServiceConfigurer clients) {
		MyClientDetailsService clientDetailsService = new MyClientDetailsService(dataSource);
		clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
		clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
		clients.withClientDetails(clientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer
				.allowFormAuthenticationForClients()
				.checkTokenAccess("isAuthenticated()");
	}


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.tokenStore(tokenStore())
				.tokenEnhancer(tokenEnhancer())
				.userDetailsService(myUserDetailsService)
				.authenticationManager(authenticationManagerBean)
				.reuseRefreshTokens(false)
				.exceptionTranslator(new MyWebResponseExceptionTranslator());
	}


	@Bean
	public TokenStore tokenStore() {
		MyRedisTokenStore tokenStore = new MyRedisTokenStore(redisConnectionFactory);
		tokenStore.setPrefix(RedisCacheConstants.OAUTH_PREFIX);
		tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
			@Override
			public String extractKey(OAuth2Authentication authentication) {
				return super.extractKey(authentication) + ":" + TenantContextHolder.getTenantId();
			}
		});
		return tokenStore;
	}

	/**
	 * token增强，客户端模式不增强。
	 *
	 * @return TokenEnhancer
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return (accessToken, authentication) -> {
			if (SecurityConstants.OAUTH_CLIENT_CREDENTIALS
					.equals(authentication.getOAuth2Request().getGrantType())) {
				return accessToken;
			}

			final Map<String, Object> additionalInfo = new HashMap<>(8);
			MyUser user = (MyUser) authentication.getUserAuthentication().getPrincipal();
			additionalInfo.put("user_id", user.getId());
			additionalInfo.put("license", "by zcloud");
			additionalInfo.put("code", Constants.BYTE_YES);
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
			return accessToken;
		};
	}
}
