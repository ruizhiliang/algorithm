package org.example.logic;

import java.util.*;

/**
 * 题目描述
 * 有一个特异性的 双端队列只，该队列可以从头部或尾部添加数据，但是只能从头部移出数据,小A依次执行2n个指令往队列中添加数据和移出数据。其中n个指令是添加数据(可能从头部添加、也可能从尾部添加)，依次添加1到n;n个指令是移出数据。
 * 现在要求移除数据的顺序为1到n。
 * 为了满足最后输出的要求，小A可以在任何时候调整队列中数据的顺序请问 小A 最少需要调整几次才能够满足移除数据的顺序正好是1到n。
 * 输入描述
 * 第一行一个数据n，表示数据的范围,
 * 接下来的2n行，其中有n行为添加数据，指令为:
 * head add x表示从头部添加数据x
 * tail add x 表示从尾部添加数据x,。
 * 另外n行为移出数据指令，指令为:remove的形式，表示移出1个数据;1sns3*10^5.
 * 所有的数据均合法。
 *
 * @author zlrui
 * @since 1.0
 */
public class MockDeque {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();//数据的范围
        scanner.nextLine();

        Queue<Integer> queue = new LinkedList<>();//模拟双端队列
        boolean in_order = true;//是否按顺序删除
        int result = 0;//最小的调整顺序次数

        for (int i = 0; i < 2 * number; i++) {
            String input_str = scanner.nextLine();
            String[] operation = input_str.split(" ");
            if (operation[0].equals("head")) {//从头部添加元素
                if (!queue.isEmpty() && in_order) {//不按顺序删除
                    in_order = false;
                }
                queue.add(Integer.parseInt(operation[2]));
            } else if (operation[0].equals("tail")) {//从尾部添加元素
                queue.add(Integer.parseInt(operation[2]));
            } else {//删除元素
                if (queue.isEmpty()) {
                    continue;
                }
                if (!in_order) {//不按顺序删除
                    result++;
                    in_order = true;
                }
                queue.poll();
            }
        }

        System.out.println(result);//输出最小的调整顺序次数


    }
}

