package algorithms;

import gui.SortingPanel;

public class BubbleSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array, SortingPanel panel) throws InterruptedException {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    panel.setArray(array);
                    Thread.sleep(50); // Delay for visualization
                }
            }
        }
    }
}
