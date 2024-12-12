package com.practice.batch.application;

import com.practice.batch.batch.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchJob(
            DormantBatchItemReader dormantBatchItemReader,
            DormantBatchItemProcessor dormantBatchItemProcessor,
            DormantBatchItemWriter dormantBatchItemWriter,
            DormantBatchJobExecutionListener dormantBatchJobExecutionListener
    ) {
        return Job.builder()
                .itemReader(dormantBatchItemReader)
                .itemProcessor(dormantBatchItemProcessor)
                .itemWriter(dormantBatchItemWriter)
                .jobExecutionListener(dormantBatchJobExecutionListener)
                .build();
    }

}
