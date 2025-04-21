package org.example.practice;

/**
 * @author zlrui
 * @since 1.0
 */
public class WukongEatPeach {

    public static void main(String[] args) {

        int[] treePeaches = {805306368, 805306368, 805306368};
        int hours = 1000000000;

        int countPerHour = binarySearchSlowestSpeed(treePeaches, hours);
        System.out.println("最小吃桃速度：" + countPerHour);
    }


    public static int binarySearchSlowestSpeed (int[] data,int targetHours) {
        int maxPeaches = 0;
        for (int i=0; i<data.length; i++) {
            if (data[i] > maxPeaches) {
                maxPeaches = data[i];
            }
        }

        // 没有桃子 或者 树的数量大于时间
        if (data.length == 0 || maxPeaches == 0 || data.length > targetHours) {
            return 0;
        }

        int minSpeed = Integer.MAX_VALUE;
        int left = 1;
        int right = maxPeaches;
        while (left <= right) {
            int middle = left + (right - left) / 2;

            long time = 0;
            for (int i=0; i<data.length; i++) {
                int temp = data[i] / middle;
                time += (data[i] % middle == 0 ? temp : temp + 1);
            }

            // 符合时间 且 要求最小
            if (time <= targetHours) {
                if (middle < minSpeed) {
                    // 找到更小速度了
                    minSpeed = middle;
                }
                right = middle - 1;
            } else {
                // 时间上来不及，要加速
                left = middle + 1;
            }
        }

        if (minSpeed == Integer.MAX_VALUE) {
            return 0;
        } else {
            return minSpeed;
        }
    }

}
