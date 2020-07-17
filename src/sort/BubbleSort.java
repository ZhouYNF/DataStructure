package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author ZHOU
 * @date 2020/7/17 22:09
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 6, 9, 2, 2, 3};
        bubbleSort(arr);

    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = 0;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //前面的比后面大就交换，把大的冒泡出去
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "轮排序");
            System.out.println(Arrays.toString(arr));
        }
    }
}
