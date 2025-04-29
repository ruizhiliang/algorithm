package org.example.logic;

import java.util.Scanner;

/**
 * 3020年，空间通信集团的员工人数突破20亿人，即将遇到现有工号不够用的窘境现在，请你负责调研新工号系统。
 * 继承历史传统，新的工号系统由小写英文字母Q(a-z)和数字(0-9)两部分构成新工号由一段英文字母开头，之后跟随一段数字，
 * 比如”aaahw0001",“a12345",abcd1"”a00”。注意新工号不能全为字母或者数字,允许数字部分有前导0或者全为0。
 * 但是过长的工号会增加同事们的记忆成本，现在给出新工号至少需要分配的人数X和新工号中字母的长度Y，求新工号中数字的最短长度Z
 *
 * 输入描述
 * 一行两个非负整数 XY，用数字用单个空格分隔。
 * 0<X<=2^50-1
 * 0<Y <=5
 *
 * 输出描述
 * 输出新工号中数字的最短长度Z
 *
 * @author zlrui
 * @since 1.0
 */
public class StaffNum {

    public static void main(String[] args) {


        //long X = 260; //员工数量;
        //int Y = 1; //字母数量
        Scanner sc = new Scanner(System.in);
        long X = sc.nextLong();
        int Y = sc.nextInt();

        long coefficient = (long)Math.pow(26, Y);
        // 向上取整
        long tempVal = (X + coefficient - 1) / coefficient;

        long left = 1;
        long right = tempVal;

        long target = -1;
        while (left <= right) {
            long middle = (right - left) / 2 + left;
            // 目标值
            if (Math.pow(10, middle) > tempVal) {
                target = middle;
                // 尝试更优的
                right = middle-1;
            } else if(Math.pow(10, middle) < tempVal) {
                left = middle+1;
            } else {
                target = middle;
                break;
            }
        }

        System.out.println(target);

        //System.out.println(Z);
    }

}
