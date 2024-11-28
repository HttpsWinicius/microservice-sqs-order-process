package com.sqs.microservice.config;

import com.sqs.microservice.domain.SqsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class AwsSqsConfig {

    @Autowired
    private SqsProperties sqsProperties;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(sqsProperties.getUri()))
                .region(Region.US_EAST_1)
                .build();
    }


}
