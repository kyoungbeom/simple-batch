package com.practice.batch.batch;

public interface ItemProcessor <I, O> {

    O process(I item);

}
