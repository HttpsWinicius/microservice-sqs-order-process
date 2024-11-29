package com.sqs.microservice.process.util;

public class TimeLoggerUtilProcess {

    protected long startTimeLogger() {
        return System.currentTimeMillis();
    }

    protected long endTimeLogger(Long startTime) {
        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000;
    }

}
