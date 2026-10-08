package com.dataart.task2.service;

import java.util.Arrays;

class SortMerge {

    static Integer[] sortMerge(Integer[] ints) {
        Integer len = ints.length;
        Integer n = 1;
        Integer shift;
        Integer two_size;
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
                if (n + two_size >= 0) {
                    System.arraycopy(arr2, 0, ints, shift, n + two_size);
                }
                shift += n * 2;
            }
            n *= 2;
        }

        return ints;
    }

    private static Integer[] merge(Integer[] arr_1, Integer[] arr_2) {
        int len_1 = arr_1.length, len_2 = arr_2.length;
        int a = 0, b = 0, len = len_1 + len_2;
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