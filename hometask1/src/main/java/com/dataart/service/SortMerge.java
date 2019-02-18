package com.dataart.service;

import java.util.Arrays;

public class SortMerge {

    public static void sortMerge(Integer[] ints) {
        Integer len = ints.length;
        Integer n = 1; // кратность сравнений (сравнивать по 1-му элем., 2-м ...)
        Integer shift; // сдвиг в перебираемом массиве
        Integer two_size; // количество элементов для 2-го массива
        Integer[] arr2;
        while (n < len) {
            shift = 0;
            while (shift < len) {
                if (shift + n >= len) {
                    break;
                }
                two_size = (shift + n * 2 > len) ? (len - (shift + n)) : n;
                arr2 = merge(Arrays.copyOfRange(ints, shift, shift + n),
                        Arrays.copyOfRange(ints, shift + n, shift + n + two_size));
                for (int i = 0; i < n + two_size; ++i) {
                    ints[shift + i] = arr2[i]; // замена на отсортированное
                }
                shift += n * 2;
            }
            n *= 2;
        }
    }

    private static Integer[] merge(Integer[] arr_1, Integer[] arr_2) {
        Integer len_1 = arr_1.length, len_2 = arr_2.length;
        Integer a = 0, b = 0, len = len_1 + len_2; // a, b - счетчики в массивах
        Integer[] result = new Integer[len];
        for (int i = 0; i < len; i++) {
            if (b < len_2 && a < len_1) {
                if (arr_1[a] > arr_2[b]) {
                    result[i] = arr_2[b++];
                } else {
                    result[i] = arr_1[a++];
                }
            } else if (b < len_2) {
                result[i] = arr_2[b++];
            } else {
                result[i] = arr_1[a++];
            }
        }
        return result;
    }
}