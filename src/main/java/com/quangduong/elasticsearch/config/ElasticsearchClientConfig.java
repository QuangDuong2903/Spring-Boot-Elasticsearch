package com.quangduong.elasticsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages
        = "com.quangduong.repository")
public class ElasticsearchClientConfig extends ElasticsearchConfiguration {

    @Autowired
    private Environment environment;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(environment.getProperty("elasticsearch.host") + ":" + environment.getProperty("elasticsearch.port"))
                .build();
    }
}
