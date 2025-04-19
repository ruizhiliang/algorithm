package org.example.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串（Longest Substring Without Repeating Characters）
 *
 * 给定一个字符串，找出最长的不包含重复字符的子字符串。
 * 解法：滑动窗口+哈希表。
 *
 * @author zlrui
 * @since 1.0
 */
public class MaxWithoutRepeatString {

    public static void main(String[] args) {

        String origin = "AABACADEFCE";

        Map<Character, Integer> counter = new HashMap<>();
        int maxIndex = 0;
        int maxLen = 0;

        int currentLen = 0;
        for (int left=0, i=0; i<origin.length(); i++) {
            char c = origin.charAt(i);
            currentLen++;
            counter.put(c, counter.getOrDefault(c, 0)+1);
            if (counter.getOrDefault(c, 0) > 1) {
                // 左侧缩减窗口
                while (left < i && counter.get(c) > 1) {
                    char next = origin.charAt(left);
                    counter.put(next, counter.get(next)-1);
                    left ++;
                    currentLen --;
                }
            } else {
                if (currentLen > maxLen) {
                    maxLen = currentLen;
                    maxIndex = left;
                }
            }
        }

        System.out.println("maxLength:" + maxLen);
        System.out.println("maxIndex:" + maxIndex);
        System.out.println("字串内容：");
        for (int k=maxIndex; k<maxIndex+maxLen; k++) {
            System.out.print(origin.charAt(k));
        }
        System.out.println();
    }

}
