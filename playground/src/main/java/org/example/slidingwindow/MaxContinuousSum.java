package org.example.slidingwindow;

/**
 * 最大连续子数组和（Maximum Subarray Sum）
 *
 * 给定一个整数数组，找到一个具有最大和的连续子数组。
 * 解法：动态调整窗口大小（滑动窗口）并计算窗口内的和。
 *
 * @author zlrui
 * @since 1.0
 */
public class MaxContinuousSum {


    public static void main(String[] args) {

        //int[] data = {1, 2, 3, -4, 2, 4, 6, 7, -100, 5, 6, 1, 10};
        int[] data = {1, 2, 3, -4, 2, 4, 6, 7, -100, -600, 310};
        //int[] data = {-50, -1, -2, -3, -4, -100, -600, -310};
        //int[] data = {-50};

        int res1 = solution1(data);
        System.out.println("solution1 最大值：" + res1);

        int res2 = solution2(data);
        System.out.println("solution2 最大值：" + res2);

    }



    public static int solution1 (int[] data) {
        //
        int maxSum = Integer.MIN_VALUE;
        int maxIndex = 0;

        int currentSum = 0;
        int left = 0;
        for (int i = 0; i < data.length; i++) {
            currentSum += data[i];

            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxIndex = left;
            }

            // 当前和小于0，重置窗口
            if (currentSum < 0) {
                left = i + 1;
                currentSum = 0;
            }
        }

        System.out.println("起始位置：" + maxIndex);
        return  maxSum;
    }

    public static int solution2 (int[] data) {

        int left = 0, right = 0;
        int currentSum = 0, max = Integer.MIN_VALUE;
        int maxStart = 0, maxEnd = 0;

        while (right < data.length) {
            // 先求当前和
            currentSum += data[right];

            if (currentSum > max) {
                max = currentSum;
                maxStart = left;
                maxEnd = right;
            }

            if (currentSum < 0) {
                // 重置窗口
                currentSum = 0;
                left = right+1;
            }

            right ++;
        }

        System.out.println("maxStart:"+maxStart + "-->maxEnd:"+maxEnd);
        return max;
    }


}
