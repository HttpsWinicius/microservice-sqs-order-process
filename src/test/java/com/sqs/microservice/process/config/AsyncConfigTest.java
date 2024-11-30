package com.sqs.microservice.process.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AsyncConfigTest {


    private static final int VALUE_EXPECTED_CORE_POOL_SIZE = 10;
    private static final int VALUE_EXPECTED_MAX_POOL_SIZE = 50;
    private static final int VALUE_EXPECTED_QUEUE_CAPACITY = 100;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Test
    void testTaskExecutorConfiguration() {

        assertNotNull(taskExecutor);

        assertEquals(VALUE_EXPECTED_CORE_POOL_SIZE, taskExecutor.getCorePoolSize());
        assertEquals(VALUE_EXPECTED_MAX_POOL_SIZE, taskExecutor.getMaxPoolSize());
        assertEquals(VALUE_EXPECTED_QUEUE_CAPACITY, taskExecutor.getQueueCapacity());
        assertTrue(taskExecutor.getThreadNamePrefix().startsWith("async-listen"));
    }
}
