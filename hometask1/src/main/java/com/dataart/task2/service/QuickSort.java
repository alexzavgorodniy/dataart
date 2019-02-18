package com.dataart.task2.service;

class QuickSort {

    private static Integer partition(Integer[] arr, Integer low, Integer high) {
        Integer pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Integer temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static Integer[] quickSort(Integer[] arr, Integer low, Integer high) {
        if (low < high) {
            Integer pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
        return arr;
    }
}