package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZHOU
 * @date 2020/7/17 23:14
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 6, 9, 2};
        //shellsort(arr);
        //ArrayList<Object> list = new ArrayList<>();
        //list.add(1);


    }

    private static void shellsort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[gap + j]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
    }
}
