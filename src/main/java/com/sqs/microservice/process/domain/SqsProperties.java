package com.sqs.microservice.process.domain;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws.sqs.url")
@Data
public class SqsProperties {

    private String uri;
}
