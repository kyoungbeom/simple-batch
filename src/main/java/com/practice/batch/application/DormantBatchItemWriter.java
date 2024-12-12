package com.practice.batch.application;

import com.practice.batch.EmailProvider;
import com.practice.batch.batch.ItemWriter;
import com.practice.batch.customer.Customer;
import com.practice.batch.customer.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchItemWriter implements ItemWriter<Customer> {

    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public DormantBatchItemWriter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.emailProvider = new EmailProvider.Fake();
    }

    // 휴먼계정으로 상태를 변환한다.
    // 메일을 보낸다.
    @Override
    public void write(Customer customer) {
        customerRepository.save(customer);
        emailProvider.send(customer.getEmail(), "휴먼전환 이메일입니다.", "내용");
    }

}
