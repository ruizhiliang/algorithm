package org.example.dynamicprogramming;

/**
 * 题目描述
 * 游戏里面，队伍通过匹配实力相近的对手进行对战。但是如果匹配的队伍实力相差太大，对于双方游戏体验都不会太好给定n个队伍的实力值，对其进行两两实力匹配，两支队伍实例差距在允许的最大差距d内，则可以匹配。要求在匹配队伍最多的情况下匹配出的各组实力差距的总和最小。
 * 输入描述
 * 第一行，n，d。队伍个数n。允许的最大实力差距d。
 * 。2<=n<=50
 * 。0<=d<=100
 * 第二行，n个队伍的实力值空格分割。
 * ·0<=各队伍实力值<=100
 * 输出描述
 * 匹配后，各组对战的实力差值的总和。若没有队伍可以匹配，则输出-1。
 *
 * @author zlrui
 * @since 1.0
 */
import java.util.*;

public class TeamMatchForMinDiff {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取队伍数量 n 和最大允许差距 d
        int n = scanner.nextInt();
        int d = scanner.nextInt();

        // 用来存储各队伍的实力值
        List<Integer> nums = new ArrayList<>();

        // 读取每个队伍的实力值并添加到 nums 列表中
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            nums.add(num);
        }

        // 对队伍实力值进行升序排序，方便后续贪心和动态规划处理
        Collections.sort(nums);

        // 记录某个队伍为了达到最佳状态时，是否已经匹配
        int[] isMatch = new int[n];

        // 初始化 min_sum 列表，存储最小的差值总和，n+1个元素，初始值为 0
        List<Integer> min_sum = new ArrayList<>(Collections.nCopies(n, 0));

        // 初始化
        if (nums.get(1) - nums.get(0) <= d) {
            isMatch[0] = 0;
            isMatch[1] = 1;
            min_sum.set(1, nums.get(1) - nums.get(0));
        }

        // 从第3个元素开始考虑配对（因为要两两配对，所以从2开始）
        for (int i = 2; i < n; i++) {
            // 如果当前两支队伍的实力差距在允许范围内，tmp置为1，表示可以匹配
            if (nums.get(i) - nums.get(i-1) <= d) {
                if (isMatch[i-2] + 1 > isMatch[i-1]) {
                    // 增加匹配
                    isMatch[i] = isMatch[i-2] + 1;
                    min_sum.set(i, min_sum.get(i-2) + nums.get(i) - nums.get(i-1));
                } else if (isMatch[i-2] + 1 < isMatch[i-1]) {
                    // 不增加匹配
                    isMatch[i] = isMatch[i-1];
                    min_sum.set(i, min_sum.get(i-1));
                } else {
                    // 比较如果拆散前面的实力差距
                    int res1 = min_sum.get(i - 1); // 本次不匹配
                    int res2 = min_sum.get(i - 2) + (nums.get(i) - nums.get(i-1)); // 增加匹配
                    if (res2 < res1) {
                        isMatch[i] = isMatch[i-2] + 1;
                        min_sum.set(i, min_sum.get(i-2) + nums.get(i) - nums.get(i-1));
                    } else {
                        isMatch[i] = isMatch[i-1];
                        min_sum.set(i, min_sum.get(i-1));
                    }
                }
            } else {
                // 匹配数量保持不变
                isMatch[i] = isMatch[i-1];
                // 如果不配对，选择保持原有的差值总和
                min_sum.set(i, min_sum.get(i-1));
            }
        }


        // 最终结果：如果没有任何队伍配对成功，输出 -1，否则输出最小的差值总和
        if (isMatch[n-1] == 0) {
            System.out.println(-1);  // 无法配对
        } else {
            System.out.println(min_sum.get(n-1));  // 输出最小的差值总和
        }
    }
}

