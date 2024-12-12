package com.practice.batch.application;

import com.practice.batch.EmailProvider;
import com.practice.batch.batch.JobExecution;
import com.practice.batch.batch.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchJobExecutionListener implements JobExecutionListener {

    private EmailProvider emailProvider;

    public DormantBatchJobExecutionListener() {
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void AfterJob(JobExecution jobExecution) {
        emailProvider.send(
                "admin@practice.com",
                "배치 완료 알림",
                "DormantBatchJob 이 수행되었습니다. status : " + jobExecution.getStatus()
        );
    }

}
