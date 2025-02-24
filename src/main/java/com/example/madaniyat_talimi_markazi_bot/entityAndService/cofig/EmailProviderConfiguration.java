package com.example.madaniyat_talimi_markazi_bot.entityAndService.cofig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Configuration
@ConfigurationProperties(prefix = "email")
@Data
public class EmailProviderConfiguration {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private String host;

    private Integer port;

    private String username;

    private String password;

    private Boolean debug;

}
