package com.dataart.service;

import java.util.concurrent.Callable;
import java.util.stream.Stream;
import org.apache.commons.lang3.RandomUtils;

public class NumberService {

    private static final Integer ARRAY_CAPACITY = 1000;

    public Integer[] getIntArray() {
        Integer[] ints = new Integer[ARRAY_CAPACITY];
        for (Integer anInt : ints) {
            anInt = RandomUtils.nextInt(-100, 100);
        }
        return ints;
    }


    public Integer[] start() throws Exception {
        return ((Callable<Integer[]>) () -> {
            Integer[] integers = getIntArray();
            Integer max = Stream.of(integers)
                    .max(Integer::compareTo)
                    .orElseThrow(IllegalArgumentException::new);
            Integer min = Stream.of(integers)
                    .min(Integer::compareTo)
                    .orElseThrow(IllegalArgumentException::new);

            return QuickSort.quickSort(integers, min, max);
        }).call();
    }
}