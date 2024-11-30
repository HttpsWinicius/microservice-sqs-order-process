package com.sqs.microservice.process.config;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class AwsSqsConfigTest {

    private static final String REGION_SQS = "us-east-1";


    @Autowired
    private AwsSqsConfig awsSqsConfig;

    @Test
    void testSqsAsyncClientConfiguration() {

        SqsAsyncClient sqsAsyncClient = awsSqsConfig.sqsAsyncClient();

        assertNotNull(sqsAsyncClient);
        assertEquals(REGION_SQS, sqsAsyncClient.serviceClientConfiguration().region().id());
    }
}
