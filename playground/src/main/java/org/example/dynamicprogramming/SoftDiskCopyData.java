package org.example.dynamicprogramming;

import java.util.Scanner;

/**
 * 题目描述
 * 有一名 科学家只 想要从一台古董电脑中拷贝文件到自己的电脑中加以研究。
 * 但此电脑除了有一个3.5寸软盘驱动器以外，没有任何手段可以将文件持贝出来，而且只有一张软盘可以使用。
 * 因此这一张软盘是唯一可以用来拷贝文件的载体。
 * 科学家想要尽可能多地将 计算机只中的信息拷贝到软盘中，做到软盘中文件内容总大小最大。已知该软盘容量为1474560字节。
 * 文件占用的软盘空间都是按块分配的，每个块大小为512个字节。一个块只能被一个文件使用。拷贝到软盘中的文件必须是完整的，且不能采取任何压缩技术，
 * 输入描述
 * 第1行为一个整数N，表示计算机中的文件数量。1≤N≤1000.接下来的第2行到第N+1行(共N行)，每行为一个整数，表示每个文件的大小Si，单位为字节
 * O ≤i< N,0 ≤ Si
 * 备注
 * 为了充分利用软盘空间，将每个文件在软盘上占用的块记录到本子上。即真正占用软盘空间的只有文件内容本身。
 * 输出描述
 * 科学家最多能拷贝的文件总大小
 *
 * @author zlrui
 * @since 1.0
 */
public class SoftDiskCopyData {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());

        int[] fileSizeArr = new int[num];
        for (int i = 0; i < num; i++) {
            int fileSize = Integer.parseInt(sc.nextLine());
            fileSizeArr[i] = fileSize;
        }

        // 容量
        int maxBlocks = 1474560 / 512;
        // 用一个二维数组dp[i][w]表示前i个物品在背包容量为w时的最大价值。
        int[][] dp = new int[num+1][maxBlocks+1];

        // 前i个文件
        for (int i=1; i<=num; i++) {
            // c指的当前背包容量
            for (int c=0; c<=maxBlocks; c++) {
                int occupyBlock = (int)Math.ceil(fileSizeArr[i - 1] / 512.0);
                if (occupyBlock > c) {
                    // 无法塞入当前容量
                    dp[i][c] = dp[i-1][c];
                } else {
                    // 可以塞入背包
                    dp[i][c] = Math.max(dp[i-1][c],  fileSizeArr[i-1] + dp[i-1][c-occupyBlock]);
                }
            }
        }
        System.out.println(dp[num][maxBlocks]);
        System.out.println("优化方法：" + optimize(maxBlocks, fileSizeArr));
    }

    private static int optimize (int maxBlocks, int[] fileSizeArr) {
        int[] dp = new int[maxBlocks+1];
        // 只依赖 i-1 个物品的数据，可以逆序更新，减少数组使用
        for (int i = 0; i < fileSizeArr.length; i++) {
            int fileBlock = (int)Math.ceil(fileSizeArr[i] / 512.0);
            for (int j=maxBlocks; j>=fileBlock; j--) {
                // 后面要比较的dp[j] 还里是上一轮的值【本次不选】 和 选 + 选之前的背包容量最大值
                // 最后一轮才能拿到最终值
                dp[j] = Math.max(dp[j], dp[j - fileBlock] + fileSizeArr[i]);
            }
        }
        return dp[maxBlocks];
    }

}
