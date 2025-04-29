package org.example.logic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目描述
 * 公司用一个 字符串只 来表示员工的出勤信息
 * 。absent:缺勤
 * 。late:迟到
 * 。leaveearly:早退
 * ·present:正常上班
 * 现需根据员工出勤信息，判断本次是否能获得出勤奖，能获得出勤奖的条件如下
 * 。缺勤不超过一次;
 * 没有连续的迟到/早退
 * ·任意连续7次考勤，缺勤/迟到/早退不超过3次。
 * 输入描述
 * 用户的考勤数据字符串
 * ·记录条数 >=1;
 * 输入字符串长度<10000;
 * ·不存在非法输入;
 *
 * @author zlrui
 * @since 1.0
 */
public class ClockingIn {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());
        List<String> resultList = new ArrayList<>();

        while (num-- > 0) {
            String[] records = sc.nextLine().split(" ");
            resultList.add(String.valueOf(checkAttention(records)));
        }

        String res = resultList.stream().collect(Collectors.joining(" "));
        System.out.println(res);
    }


    private static boolean checkAttention (String[] records) {
        //
        int violateCondition1Count = 0;
        int violateCondition2LastIndex = -100;
        Queue<Integer> violateCondition3Queue = new LinkedList<>();
        for (int i = 0; i < records.length; i++) {
            String item = records[i];
            // 判断条件1：缺勤
            if ("absent".equals(item)) {
                violateCondition1Count ++;
                if (violateCondition1Count > 1) {
                    return false;
                }
                violateCondition3Queue.offer(i);
            }
            // 判断条件2：连续迟到/早退
            else if ("late".equals(item) || "leaveearly".equals(item)) {
                if (i-1 == violateCondition2LastIndex) {
                    return false;
                } else {
                    violateCondition2LastIndex = i;
                    violateCondition3Queue.offer(i);
                }
            }

            // 判断条件3
            if (!violateCondition3Queue.isEmpty()) {
                Integer index = violateCondition3Queue.peek();
                if (index <= i - 7) {
                    violateCondition3Queue.poll();
                }

                if (violateCondition3Queue.size() > 3) {
                    return false;
                }
            }
        }

        return true;
    }

}
