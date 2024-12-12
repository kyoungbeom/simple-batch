package com.practice.batch.application;

import com.practice.batch.batch.ItemProcessor;
import com.practice.batch.customer.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DormantBatchItemProcessor implements ItemProcessor<Customer, Customer> {

    // 휴먼계정 대상을 추출 및 변환한다.
    @Override
    public Customer process(Customer customer) {
        final boolean isDormantTarget = LocalDateTime.now()
                .minusDays(365)
                .isAfter(customer.getLoginAt());

        if (isDormantTarget) {
            customer.setStatus(Customer.Status.DORMANT);
            return customer;
        } else {
            return null;
        }
    }

}
