package eu.tasgroup.fdr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${remote-api-base-url}")
    private String remoteApiBaseUrl;

    @Bean
    public WebClient webClient() {

        return WebClient.builder()
                .baseUrl(remoteApiBaseUrl)
                .build();
    }

}
