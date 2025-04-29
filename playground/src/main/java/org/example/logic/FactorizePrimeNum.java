package org.example.logic;

import java.util.Scanner;

/**
 * 题目描述
 * RSA加密算法只在网络安全世界中无处不在，它利用了极大整数医数分解的困难度，数据越大，安全系数越高，给定一个32位正整数，请对其进行因数分解，找出是哪两个素数的乘积。
 * 输入描述
 * -个正整数num，0<num<=2147483647
 * 输出描述
 * 如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1，-1
 *
 * !!!为了高效分解一个32位正整数为两个素数的乘积，我们可以利用素数的分布特性，
 * 即除2和3外，所有素数都形如6k±1。通过步长6的试除，跳过明显非素数的数值，提升效率。!!!
 *
 * @author zlrui
 * @since 1.0
 */
public class FactorizePrimeNum {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();

        if (isPrime(target)) {
            System.out.println("-1 -1");
            return;
        }

        // 2的平方开始
        for (int i = 2; i <= Math.sqrt(target); i++) {
            // 如果能整除才开始检查2个因子是否为素数
            if (target % i == 0) {
                int j = target/i;
                if (isPrime(i) && isPrime(j)) {
                    System.out.println(i < j ? (i + " " + j) : (j + " " + i));
                    return;
                }
            }
        }
        // 都不能整除
        System.out.println("-1 -1");
    }


    private static boolean isPrime (int num) {
        if (num <= 1) {
            return false;
        }

        if (num == 2 || num == 3) {
            return true;
        }

        // 优先看是否2和3的倍数
        if (num % 2 == 0 || num %  3 ==0) {
            return false;
        }

        int i = 5;
        // 检查6k±1的因数，直到sqrt(n)
        while (i <= num / i) {
            // 6K-1 || 6K+1
            if (num % i == 0 || num % (i+2) == 0) {
                // 如果能被整除就不是素数
                return false;
            }
            i += 6;
        }
        // 剩下的都是素数了
        return true;
    }


}
