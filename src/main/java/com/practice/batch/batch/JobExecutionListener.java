package com.practice.batch.batch;

public interface JobExecutionListener {

    void beforeJob(JobExecution jobExecution);

    void AfterJob(JobExecution jobExecution);

}
