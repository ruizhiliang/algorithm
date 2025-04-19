package org.example.slidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 窗口大于数组长度或窗口长度为0的时候，返回空。
 * 要求：空间复杂度O(n)，时间复杂度 O(n)
 *
 * @author zlrui
 * @since 1.0
 */
public class EveryWindowMaxNum {

    public static void main(String[] args) {

        // int[] data = {2,3,4,2,6,2,5,1};
        int[] data = {2,4,4,4,1,2,6,2,5,1};
        int size = 3;

        int[] result = new int[data.length - size + 1];

        Deque<Integer> deque = new LinkedList();

        int max = Integer.MIN_VALUE;
        // 初始化窗口数据
        for (int i=0; i<size; i++) {
            // 注意边界条件：等于的时候也要放进去
            if (data[i] >= max) {
                max = data[i];
                // 保存索引位置
                deque.pollLast();
                deque.offer(i);
            }
        }
        result[0] = data[deque.peek()];

        for (int i = size; i < data.length; i++) {
            // 容易出错点：存储的是索引，比较的是实际数据！！
            // 这里数据比较的边界：大于 或者 大于等于 都无所谓，保证更大的索引在就行
            while (deque.size() > 0 && data[i] > data[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            if (deque.peek() <= i - size) {
                // 把过期窗口的第一个移除
                deque.poll();
            }
            // 将最大的【数据，不是索引】放入结果中
            result[i-size+1] = data[deque.peek()];
        }

        String collect = Arrays.stream(result).mapToObj(Integer::toString).collect(Collectors.joining(","));
        System.out.println(collect);
    }

}
