package org.example.dynamicprogramming;

import java.util.Scanner;

/**
 * 题目描述
 * 一天一只顽猴想去从山脚爬到山顶，途中经过一个有个N个台阶的阶梯，但是这猴子有一个习惯:
 * 每一次只能跳1步或跳3步，试问猴子通过这个阶梯有多少种不同的跳跃方式?
 * 输入描述
 * 输入只有一个整数N(0<N<=50)此阶梯有多少个台阶,
 * 输出描述
 * 输出有多少种跳跃方式(解决方案数)
 *
 * @author zlrui
 * @since 1.0
 */
public class MonkeyClimbMountain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // f(N) = f(N-1) + f(N-3)
        int N = sc.nextInt();

        if (N == 0) {
            System.out.println(0);
            return;
        } else if (N == 1){
            System.out.println(1);
            return;
        } else if (N == 2){
            System.out.println(1);
            return;
        } else if (N == 3){
            System.out.println(2);
            return;
        }

        int[] dp = new int[N];

        // 爬1步：1种
        dp[0] = 1;
        // 爬2步：1种（先1后1）
        dp[1] = 1;
        // 爬3步：2种（1/1/1 和 直接3）
        dp[2] = 2;

        for (int i = 3; i < N; i++) {
            dp[i] = dp[i-1] + dp[i-3];
        }

        System.out.println(dp[N-1]);
    }


}
