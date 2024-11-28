package com.sqs.microservice.domain;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws.sqs.localstack")
@Data
public class SqsProperties {

    private String uri;
    private String url;

}
