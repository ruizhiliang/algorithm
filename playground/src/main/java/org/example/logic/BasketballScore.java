package org.example.logic;

import java.util.*;

/**
 * 你现在是一场采用特殊赛制投篮大赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
 * 比赛开始时，记录是空白的。
 * 你会得到一个记录操作的字符串列表Q ops，其中opsQ[是你需要记录的第i项操作，ops遵循下述规则:
 * 整数x-表示本回合新获得分数x
 * ·“+”-表示本回合新获得的得分是前两次得分的总和。
 * ·“D”-表示本回合新获得的得分是前一次得分的两倍。
 * 。“C”-表示本回合没有分数，并且前一次得分无效，将其从记录中移除
 * 请你返回记录中所有得分的总和。
 * 输入描述
 * 输入为一个字符串数组
 * 提示
 * 1.1 <= ops.length <= 1000
 * 2. ops[i] 为“C”、“D”、“+”，或者一个表示整数的字符串。整数范围是 [-3*10^4,3*10^4]
 * 3.需要考虑异常的存在，如有异常情况，请返回-1
 * 4.对于“+”操作，题目数据不保证记录此操作时前面总是存在两个有效的分数
 * 5.对于“C”和“D”操作，题目数据不保证记录此操作时前面存在一个有效的分数
 *
 * @author zlrui
 * @since 1.0
 */
public class BasketballScore {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        String[] inputArray = sc.nextLine().split(" ");

        if (inputArray.length <= 0) {
            System.out.println(0);
            return;
        }

        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < inputArray.length; i++) {
            String temp = inputArray[i];
            switch (temp) {
                case "+":
                    if (stack.size() < 2) {
                        System.out.println(-1);
                        return;
                    }
                    Long last = stack.pop();
                    Long second = stack.peek();
                    stack.push(last);
                    stack.push(last+second);
                    break;
                case "D":
                    if (stack.size() < 1) {
                        System.out.println(-1);
                        return;
                    }
                    Long last2 = stack.peek();
                    stack.push(last2 * 2);
                    break;
                case "C":
                    if (stack.size() < 1) {
                        System.out.println(-1);
                        return;
                    }
                    stack.pop();
                    break;
                default:
                    long num = Long.parseLong(temp);
                    stack.push(num);
            }
        }

        long result = 0;
        while(!stack.isEmpty()){
            result += stack.pop();
        }
        System.out.println(result);
    }

}
