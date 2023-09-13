/*
 * Copyright [2023] [msauth of copyright http://www.douclouds.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mszlu.msauth.security.cli.config;

import com.mszlu.msauth.security.cli.client.HomeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author 码神之路
 */
@Configuration
public class WebClientConfig {
    /**
     * 创建名为 welcomeClient 的 bean，类型为 WelcomeClient。
     * 使用 OAuth2AuthorizedClientManager 作为参数创建 HttpServiceProxyFactory，然后使用它创建客户端。
     *
     * @param authorizedClientManager 用于创建 HttpServiceProxyFactory 的 OAuth2AuthorizedClientManager 实例
     * @return WelcomeClient 实例
     * @throws Exception 如果创建客户端时发生错误，则抛出异常
     */
    @Bean
    public HomeClient homeClient(OAuth2AuthorizedClientManager authorizedClientManager) throws Exception {
        return httpServiceProxyFactory(authorizedClientManager).createClient(HomeClient.class);
    }

    /**
     * 创建 HttpServiceProxyFactory，以便在创建客户端时使用。
     *
     * @param authorizedClientManager 用于创建 HttpServiceProxyFactory 的 OAuth2AuthorizedClientManager 实例
     * @return 创建的 HttpServiceProxyFactory 实例
     */
    private HttpServiceProxyFactory httpServiceProxyFactory(OAuth2AuthorizedClientManager authorizedClientManager) {
        // 创建 ServletOAuth2AuthorizedClientExchangeFilterFunction，使用它来处理 OAuth2 认证
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);

        // 设置默认的 OAuth2 授权客户端
        oauth2Client.setDefaultOAuth2AuthorizedClient(true);

        // 创建 WebClient，应用 OAuth2 认证配置
        WebClient webClient = WebClient.builder()
                .apply(oauth2Client.oauth2Configuration())
                .build();

        // 创建 WebClientAdapter，它允许我们在创建客户端时使用 WebClient
        WebClientAdapter client = WebClientAdapter.forClient(webClient);

        // 创建 HttpServiceProxyFactory，它可用于创建客户端
        return HttpServiceProxyFactory.builder(client).build();
    }

    /**
     * 创建 OAuth2AuthorizedClientManager 的 bean。
     *
     * @param clientRegistrationRepository 用于管理客户端注册信息的 ClientRegistrationRepository 实例
     * @param authorizedClientRepository   用于管理授权客户端信息的 OAuth2AuthorizedClientRepository 实例
     * @return OAuth2AuthorizedClientManager 实例
     */
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {

        // 创建 OAuth2AuthorizedClientProvider，用于获取授权客户端
        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .build();

        // 创建 DefaultOAuth2AuthorizedClientManager，使用它来管理授权客户端
        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientRepository);

        // 设置授权客户端提供程序
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        // 返回 OAuth2AuthorizedClientManager
        return authorizedClientManager;
    }
}
