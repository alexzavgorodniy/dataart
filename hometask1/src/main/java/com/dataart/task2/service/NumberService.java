package com.dataart.task2.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import javafx.util.Pair;
import org.apache.commons.lang3.RandomUtils;

public class NumberService {

    private static final Integer ARRAY_CAPACITY = 1000;

    private Integer[] getIntArray() {
        Integer[] ints = new Integer[ARRAY_CAPACITY];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = RandomUtils.nextInt(0, 100);
        }
        return ints;
    }

    public void start() throws Exception {
        long start = System.currentTimeMillis();
        Integer[] firstArray = startFirstThread();
        long res1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        Integer[] secondArray = startSecondThread();
        long res2 = System.currentTimeMillis() - start;

        Pair<Float, Float> pair = startThirdThread(firstArray, secondArray);
        System.out.println("среднее арифметическое наибольних 10 элементов из первого потока: " + pair.getKey()
                + "\nсреднее арифметическое нименьших 100 элементов из второго потока: " + pair.getValue());

        System.out.println("сортировкой слиянием: " + res1 + " ms");
        System.out.println("быстрой сортировкой: " + res2 + " ms");

        writeFiles(Arrays.toString(firstArray), Arrays.toString(secondArray));
        System.out.println("Both files were created!");
    }

    private void writeFiles(String firstArray, String secondArray) {
        try (FileOutputStream fosOne = new FileOutputStream(new File("first.txt"));
                FileOutputStream fosTwo = new FileOutputStream(new File("second.txt"))) {
            fosOne.write(firstArray.getBytes());
            fosTwo.write(secondArray.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Pair<Float, Float> startThirdThread(Integer[] firstArray, Integer[] secondArray) throws Exception {
        return ((Callable<Pair<Float, Float>>) () -> {
            int indexOfLastElement = firstArray.length;
            int sumBigger = 0;
            for (int i = indexOfLastElement - 1; i > indexOfLastElement - 11; i--) {
                sumBigger += firstArray[i];
            }
            float averageOfTenBiggerNumbers = sumBigger / 10f;

            int sumMinor = 0;
            for (int i = 0; i < 100; i++) {
                sumMinor += secondArray[i];
            }
            float averageOfHundredMinorNumbers = sumMinor / 100f;
            return new Pair<>(averageOfTenBiggerNumbers, averageOfHundredMinorNumbers);
        }).call();
    }

    private Integer[] startFirstThread() throws Exception {
        Integer[] integers = getIntArray();
        Callable<Integer[]> callable = () -> SortMerge.sortMerge(integers);
        return callable.call();
    }

    private Integer[] startSecondThread() throws Exception {
        Integer[] integers = getIntArray();
        Callable<Integer[]> callable = () -> QuickSort.quickSort(integers, 0, ARRAY_CAPACITY - 1);
        return callable.call();
    }
}