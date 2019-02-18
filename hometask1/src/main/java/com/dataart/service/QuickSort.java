package com.dataart.service;

public class QuickSort {

    public static void quickSort(Integer[] A, Integer low, Integer high) {
        Integer i = low;
        Integer j = high;
        Integer x = A[low + (high - low) / 2];
        do {
            while (A[i] < x) {
                ++i;
            }
            while (A[j] > x) {
                --j;
            }
            if (i <= j) {
                Integer temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        //рекурсивные вызовы функции qSort
        if (low < j) {
            quickSort(A, low, j);
        }
        if (i < high) {
            quickSort(A, i, high);
        }
    }
}