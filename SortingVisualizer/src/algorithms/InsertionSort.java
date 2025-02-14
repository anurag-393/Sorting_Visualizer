package algorithms;

import gui.SortingPanel;

public class InsertionSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array, SortingPanel panel) throws InterruptedException {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
                panel.setArray(array);
                Thread.sleep(50); // Delay for visualization
            }
            array[j + 1] = key;
            panel.setArray(array);
            Thread.sleep(50); // Delay for visualization
        }
    }
}
