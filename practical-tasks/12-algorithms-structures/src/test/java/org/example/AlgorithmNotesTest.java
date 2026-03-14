package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Тестирование алгоритмов</h1>
 */
public class AlgorithmNotesTest {

    @Test
    public void testBubbleSort() {
        int[] input = {3, 1, 4, 1, 5, 9};
        int[] expected = {1, 1, 3, 4, 5, 9};
        
        AlgorithmNotes.bubbleSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testBinarySearch() {
        int[] sorted = {1, 1, 3, 4, 5, 9};
        
        assertEquals(2, AlgorithmNotes.binarySearch(sorted, 3));
        assertEquals(0, AlgorithmNotes.binarySearch(sorted, 1));
        assertEquals(-1, AlgorithmNotes.binarySearch(sorted, 100));
    }
}
