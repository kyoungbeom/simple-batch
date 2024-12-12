package com.practice.batch.batch;

import com.practice.batch.customer.Customer;
import lombok.Builder;

import java.time.LocalDateTime;

public class Job {

    private final Tasklet tasklet;
    private final JobExecutionListener jobExecutionListener;

    @Builder
    public Job(
            ItemReader<?> itemReader,
            ItemProcessor<?, ?> itemProcessor,
            ItemWriter<?> itemWriter,
            JobExecutionListener jobExecutionListener
    ) {
        this(new SimpleTasklet(itemReader, itemProcessor, itemWriter), jobExecutionListener);
    }

    public Job(Tasklet tasklet, JobExecutionListener jobExecutionListener) {
        this.tasklet = tasklet;
        if (jobExecutionListener == null) {
            this.jobExecutionListener = new JobExecutionListener() {
                @Override
                public void beforeJob(JobExecution jobExecution) {}

                @Override
                public void AfterJob(JobExecution jobExecution) {}
            };
        } else {
            this.jobExecutionListener = jobExecutionListener;
        }
    }

    public JobExecution execute() {
        final JobExecution jobExecution = new JobExecution();

        jobExecutionListener.beforeJob(jobExecution);

        jobExecution.setStatus(BatchStatus.STARING);
        jobExecution.setStartTime(LocalDateTime.now());

        try {
            tasklet.execute();
            jobExecution.setStatus(BatchStatus.COMPLETED);
        } catch (Exception e) {
            jobExecution.setStatus(BatchStatus.FAILED);
        }

        jobExecution.setEndTime(LocalDateTime.now());

        jobExecutionListener.AfterJob(jobExecution);

        return jobExecution;
    }

}
