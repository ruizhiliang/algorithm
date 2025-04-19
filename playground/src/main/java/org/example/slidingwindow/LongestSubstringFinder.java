package org.example.slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串，只包含字母和数字，按要求找出字符串中的最长（连续）子串的长度，字符串本身是其最长的子串，子串要求：
 * 1、 只包含1个字母(a-z, A-Z)，其余必须是数字；
 * 2、 字母可以在子串中的任意位置；
 * 如果找不到满足要求的子串，如全是字母或全是数字，则返回-1。
 *
 * @author zlrui
 * @since 1.0
 */
public class LongestSubstringFinder {

    /**
     * 假设范围是英文字母
     * @param x
     * @return
     */
    private static boolean isLetter (char x) {
        return 'a' <= x && x <= 'z' || 'A' <= x && x <= 'Z';
    }


    /**
     * 不符合条件2种情况：
     * 1、快慢双指针同时指向了字母；
     * 2、快指针自己先到达第二个字母，慢指针还在前一个字母前面
     *
     * @param targetString
     * @return
     */
    private static int getLongestSubSeqNew (String targetString) {
        char[] charArray = targetString.toCharArray();
        int i = 0;
        int lastLetterIndex = -1;
        int maxLength = 0;
        int letterCount = 0;
        int numberCount = 0;
        for (int j=0; j<charArray.length; j++) {
            // 判断当前字符
            if (isLetter(charArray[j])) {
                // 不符合条件【如果当前是字母，且在i之后还有字母，则说明前面已经获得了最大长度（或者为0），i直接跳跃到上一个字母后面才符合条件】
                if (lastLetterIndex > i) {
                    // i直接跳跃到上一个字母后面
                    i = lastLetterIndex + 1;
                }
                // 更新保留最新字母位置
                lastLetterIndex = j;
                // 统计字母数量（用于特殊情况：全字母）
                letterCount ++;
            } else {
                // 统计字母数量（用于特殊情况：全数字）
                numberCount ++;
            }
            // 不符合条件【快慢指针同时指向了字母】
            if (isLetter(charArray[j]) && isLetter(charArray[i])) {
                // 如果慢指针落后，先往后移动
                if (i < j) {
                    i++;
                }
                continue;
            }
            // 符合条件【子串中最多只有一个字母】需要计算最大的字串长度
            maxLength = Math.max(maxLength, j-i+1);
        }

        return (letterCount == charArray.length || numberCount == charArray.length) ? -1 : maxLength;
    }

    public static void main(String[] args) {
        //String targetString = "abC124ACb";
        //String targetString = "abcdef";
        //String targetString = "123124";
        //String targetString = "";
        String targetString = "123abc4567p8";
        //String targetString = "aBB9";
        //getLongestSubSeq(targetString);
        System.out.println(getLongestSubSeqNew(targetString));


        // 特殊情况处理
        int numberCount = 0;

        int maxLen = 0;
        int maxLenLeft = -1;
        int maxLenRight = -1;


        int currentLen = 0;
        int left = 0;
        int letterCount = 0;
        for (int i = 0; i < targetString.length(); i++) {
            Character c = targetString.charAt(i);

            if (Character.isLetter(c)) {
                letterCount++;

                if (letterCount < 2) {
                    currentLen ++;
                    // 更新长度
                    if (currentLen > maxLen) {
                        maxLen = currentLen;
                        maxLenLeft = left;
                        maxLenRight = i;
                    }
                } else {
                    // 缩小窗口直到满足条件
                    while (left < i && letterCount > 1) {
                        Character leftChar = targetString.charAt(left);
                        if (Character.isLetter(leftChar)) {
                            letterCount --;
                        }
                        left ++;
                    }
                }
            } else {
                numberCount ++;
                currentLen ++;
                // 更新长度
                if (currentLen > maxLen) {
                    maxLen = currentLen;
                    maxLenLeft = left;
                    maxLenRight = i;
                }
            }
        }

        // 边界条件处理
        if (numberCount == 0 || letterCount == 0) {
            System.out.println("无符合条件的，长度：" + -1);
        } else {
            String substring = targetString.substring(maxLenLeft, maxLenRight + 1);
            System.out.println("最长字串：" + substring);
        }
    }


}
