package org.example.practice;

import java.util.Scanner;

/**
 * 小明负责维护项目下的代码，需要查找出重复代码，用以支撑后续代码优化只，请你帮助小明找出重复的代码ngth<=100，由英文字母、数字和空格组成)，
 * 找出两行代码中的最重复代码查找方法：以字符串形式给定两行代码(字符串长度1<length<=100，由英文字母、数字和空格组成)，找出两行代码中的最长公共子串
 * 注:如果不存在公共子串，返回空字符串
 *
 * 输入描述
 * 输入的参数text1,text2分别表示两行代码
 * 输出描述
 * 输出任一最长公共子串
 *
 * @author zlrui
 * @since 1.0
 */
public class LongestStringMatch {

    public static void main(String[] args) {

        //String text1 = "2LQ74WK8Ld0x7d8FP8l61pD7Wsz1E9xOMp920hM948eGjL9Kb5KJt80";
        //String text2 = "U08U29zzuodz16CBZ8xfpmmn5SKD80smJbK83F2T37JRqYfE76vh6hrE451uFQ100ye9hog1Y52LDk0L52SuD948eGjLz0htzd5YF9J1Y6oI7562z4T2";

        //String text1 = "aa7bcdef";
        //String text2 = "a09bcdggg";


        Scanner sc = new Scanner(System.in);
        String text1 = sc.nextLine();
        String text2 = sc.nextLine();

        int[][] dp = new int[text1.length()][text2.length()];
        int maxLen = 0;
        int maxI = -1;

        for (int i=0; i<text1.length(); i++) {
            char a = text1.charAt(i);
            for (int j=0; j<text2.length(); j++){
                char b = text2.charAt(j);
                // 边界
                if (i == 0 || j == 0) {
                    dp[i][j] = a == b ? 1 : 0;
                } else {
                    dp[i][j] = a == b ? dp[i-1][j-1] + 1 : 0;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        maxI = i;
                    }
                }
            }
        }

        if (maxI == -1) {
            System.out.println("");
        } else {
            String charList = text1.substring(maxI-maxLen+1, maxI+1);
            System.out.println(charList);
        }
    }


}
