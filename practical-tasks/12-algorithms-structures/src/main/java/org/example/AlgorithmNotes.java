package org.example;

import java.util.Arrays;

/**
 * <h1>Алгоритмы и структуры данных в Java</h1>
 * 
 * <p>Алгоритмы — это пошаговые инструкции для решения задач, а структуры данных —
 * способы организации информации для эффективного доступа и модификации.</p>
 *
 * <h2>1. Алгоритмы сортировки</h2>
 * <p>Сортировка — одна из самых частых задач. Различают простые алгоритмы 
 * (Bubble Sort, Selection Sort) с О(n^2) и эффективные (Quick Sort, Merge Sort) с O(n log n).</p>
 *
 * <h2>2. Поиск</h2>
 * <p>Линейный поиск перебирает всё подряд. Бинарный поиск работает в <b>отсортированном</b> 
 * массиве, разделяя область поиска пополам на каждом шаге (O(log n)).</p>
 *
 * <h2>3. Структуры данных</h2>
 * <ul>
 *     <li><b>Связный список (Linked List)</b>: каждый элемент указывает на следующий. Легко вставлять в середину.</li>
 *     <li><b>Двоичное дерево поиска (BST)</b>: позволяет быстро искать, вставлять и удалять элементы.</li>
 * </ul>
 *
 * @author Student
 */
public class AlgorithmNotes {

    /**
     * Пример алгоритма сортировки пузырьком (Bubble Sort).
     * Прост в понимании, но медленен на больших данных.
     */
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // меняем элементы местами
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Бинарный поиск в отсортированном массиве.
     */
    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1; // не найдено
    }

    public static void main(String[] args) {
        System.out.println("=== Запуск модуля: Алгоритмы ===");
        
        int[] data = {5, 2, 8, 1, 9};
        System.out.println("Исходный массив: " + Arrays.toString(data));
        
        bubbleSort(data);
        System.out.println("После сортировки: " + Arrays.toString(data));
        
        int index = binarySearch(data, 8);
        System.out.println("Индекс числа 8: " + index);
    }
}
