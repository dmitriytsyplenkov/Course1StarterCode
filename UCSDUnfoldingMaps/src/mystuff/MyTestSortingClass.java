package mystuff;

import java.util.Arrays;

public class MyTestSortingClass {
    public static void main(String[] args) {
        int[] arr = {5, 46, 2, 23, 78, 4};
        //selectionSort(arr);
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        int curSmallestIndex;
        for (int i = 0; i < arr.length - 1; i++) {
           curSmallestIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[curSmallestIndex] > arr[j]) {
                    curSmallestIndex = j;
                }
            }
            if (i != curSmallestIndex) {
                swapElements(arr, curSmallestIndex, i);
            }
        }
    }

    public static void insertionSort(int[] arr) {
        int curPos;
        for (int pos = 1; pos < arr.length; pos++) {
            curPos = pos;
            while (curPos > 0 && arr[curPos] < arr[curPos - 1]) {
                swapElements(arr, curPos, curPos - 1);
                curPos--;
            }

        }
    }

    public static void swapElements(int[] arr, int pos1, int i) {
        arr[i] = arr[i] + arr[pos1];
        arr[pos1] = arr[i] - arr[pos1];
        arr[i] = arr[i] - arr[pos1];
    }

}
