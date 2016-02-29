package org.jhouse.survey.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by jhouse on 10/9/14.
 */
@Configuration
@EnableWebSocket
public class DevPingWebSocketContextConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoHandler(), "/surveyWS")
        .addInterceptors(new org.jhouse.survey.ws.SessionInterceptor());


    }

    @Bean
    public WebSocketHandler echoHandler() {
        return new org.jhouse.survey.ws.CSWebSocketHandler();
    }
}
