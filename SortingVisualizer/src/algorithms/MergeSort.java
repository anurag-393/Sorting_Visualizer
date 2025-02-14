package algorithms;

import gui.SortingPanel;

public class MergeSort implements SortingAlgorithm{

    SortingPanel panel;
    public void mergeSort(int arr[], int start, int end) throws InterruptedException {
        if(start >= end) {
            return;
        }
        
        int mid = start + (end - start) / 2;

        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        merge(arr, start, mid, end);
    }

    public void merge(int arr[], int start, int mid, int end) throws InterruptedException {
        int[] temp = new int[end - start + 1];

        int i = start;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= end) {
            if(arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else{
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }
        while (j <= end) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        for(int l = 0, f = start; l < temp.length; l++, f++ ) {
            arr[f] = temp[l];
            panel.setArray(arr); // Update the panel with the current state of the array
            Thread.sleep(50); // Delay for visualization
        }
    }
    @Override
    public void sort(int[] array, SortingPanel panel) throws InterruptedException {
        this.panel = panel;
        mergeSort(array, 0, array.length - 1);

    }    
}
