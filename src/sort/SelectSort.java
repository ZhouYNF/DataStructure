package sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author ZHOU
 * @date 2020/7/17 21:45
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 6, 9, 2};
        selectSort(arr);
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //假设当前数值就是最小
            int mixIndex = i;
            int mix = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                //拿着当前数值去比对，是否还有更小的数值，临时存起
                if (mix > arr[j]) {
                    mixIndex = j;
                    mix = arr[j];
                }
            }
            //pan判断是否有修改过最小数值，没有就无需修改
         if (mixIndex != i) {
                //将数值交换
                arr[mixIndex] = arr[i];
                arr[i] = mix;
            }
            System.out.println("第" + i + "轮排序");
            System.out.println(Arrays.toString(arr));
        }

    }

}
