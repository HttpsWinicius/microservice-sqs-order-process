package com.sqs.microservice.process;

import com.sqs.microservice.process.job.ListenOrderQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProcessApplicationTests {

	@Autowired
	ListenOrderQueue listenOrderQueue;

	@Test
	void contextLoads() {
		assertNotNull(listenOrderQueue);
	}

}
