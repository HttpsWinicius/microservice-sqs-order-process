package com.sqs.microservice.process;


import com.sqs.microservice.process.job.ReadOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProcessApplicationTests {

	@Autowired
	ReadOrder readOrder;

	@Test
	void contextLoads() {
		assertNotNull(readOrder);
	}

}
