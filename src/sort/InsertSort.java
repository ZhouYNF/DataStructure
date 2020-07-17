package sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author ZHOU
 * @date 2020/7/16 22:37
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 6, 9, 2};
        insertsort(arr);
    }

    //插入排序
    public static void insertsort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {

            int insertVal = arr[i];

            int insertindex = i - 1;

            // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            while (insertindex >= 0 && insertVal < arr[insertindex]) {
                arr[insertindex + 1] = arr[insertindex];
                insertindex--;
                System.out.println("while内部交换" + (insertindex + 1));
                System.out.println(Arrays.toString(arr));
            }
            if (insertindex + 1 != i) {
                arr[insertindex + 1] = insertVal;
            }

            System.out.println("第" + i + "轮排序");
            System.out.println(Arrays.toString(arr));
        }
    }
}
