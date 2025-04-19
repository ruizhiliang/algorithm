package org.example.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串（Minimum Window Substring）
 *
 * 给定字符串 S 和目标字符串 T，找出最小的窗口，包含 T 中的所有字符。
 * 解法：滑动窗口+哈希表记录字符频率。
 *
 * @author zlrui
 * @since 1.0
 */
public class MaxSubString {

    // 先设置一个没有处理过的集合
    public static void main(String[] args) {

//        String t = "adeg";
//        String s = "abcdefaghijk";

//        String t = "ABC";
//        String s = "ADOBECODEBANC";

        String t = "ABC";
        String s = "ADOBECODEBANCGGGAB";

        solution1(s, t);
        solution2(s, t);

    }

    public static int solution1 (String s, String t) {
        // 待查找符合的字符数量（去重） -- 这个很关键，可以不用对比
        int waitDealCount = 0;
        // 未解决
        Map<Character, Integer> targetMap = new HashMap<>();

        int leftIndex = 0;
        int rightIndex = Integer.MAX_VALUE;

        // 初始化Map数据
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            Integer orDefault = targetMap.getOrDefault(c, 0);
            if (orDefault == 0) {
                waitDealCount++;
            }
            targetMap.put(c, orDefault+1);
        }

        for (int left = 0, right = 0; right < s.length(); right++) {
            Character x = s.charAt(right);
            if (!targetMap.containsKey(x)) {
                continue;
            }
            Integer count = targetMap.get(x);
            targetMap.put(x, count-1);
            if (count == 1) {
                // 完成一个字符的积累
                waitDealCount --;

                // 处理完成了
                while (waitDealCount == 0 && left < right) {
                    // 先记录【必须要比之前的短】，再缩窗口
                    if (right - left < rightIndex - leftIndex) {
                        leftIndex = left;
                        rightIndex = right;
                    }

                    Character leftChar = s.charAt(left);
                    if (!targetMap.containsKey(leftChar)) {
                        left ++;
                        continue;
                    }

                    int charCnt = targetMap.get(leftChar) + 1;
                    if (charCnt > 0) {
                        waitDealCount ++;
                    }
                    targetMap.put(leftChar, charCnt);
                    left ++;
                }
            }
        }

        System.out.println("left: " +leftIndex +" , right：" + rightIndex);
        System.out.println("解法1 字串：" + s.substring(leftIndex, rightIndex+1));

        return rightIndex - leftIndex + 1;
    }

    public static int solution2 (String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return -1;
        }
        // 存储目标字符串 t 中每个字符的频率
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        // 存储当前窗口中每个字符的频率
        Map<Character, Integer> windowMap = new HashMap<>();
        // 记录当前窗口中已经匹配的字符种类数
        int matched = 0;
        // 记录最小窗口的起始位置和长度
        int minLeft = 0, minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (targetMap.containsKey(c)) {
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
                if (windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                    matched++;
                }
            }
            // 当窗口中已经匹配了目标字符串的所有字符种类时，尝试缩小窗口
            while (left <= right && matched == targetMap.size()) {
                if (right - left + 1 < minLen) {
                    minLeft = left;
                    minLen = right - left + 1;
                }
                char leftChar = s.charAt(left);
                if (targetMap.containsKey(leftChar)) {
                    windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                    if (windowMap.get(leftChar).intValue() < targetMap.get(leftChar).intValue()) {
                        matched--;
                    }
                }
                left++;
            }
            right++;
        }

        System.out.println("解法2 字串：" + (minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen)));

        return minLen == Integer.MAX_VALUE ?  -1 : minLeft;
    }

}
