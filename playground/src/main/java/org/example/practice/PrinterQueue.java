package org.example.practice;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 题目描述
 * 有5台打印机打印文件，每台打印机有自己的待打印队列。
 * 因为打印的文件内容有轻重缓急之分，所以队列中的文件有1~10不同的代先级，其中数字越大优先级越高。
 * 打印机会从自己的待打印队列中选择 优先级最高 的文件来打印。
 * 如果存在两个优先级一样的文件，则选择最早进入队列 的那个文件。
 * 现在请你来模拟这5台打印机的打印过程。
 * 输入描述
 * 每个输入包含1个测试用例,
 * 每个测试用例第一行给出发生事件的数量N(0<N<1000)接下来有 N 行，分别表示发生的事件。共有如下两种事件:
 * 1.“INP NUM”，表示有一个拥有优先级 NUM 的文件放到了打印机 P的待打印队列中。 (0<P<= 5,0<NUM <= 10);
 * 2.“OUT P”，表示打印机P进行了一次文件打印，同时该文件从待打印队列中取出。(0<P<= 5)。
 * 输出描述
 * 对于每个测试用例，每次”OUT P”事件，请在一行中输出文件的编号。如果此时没有文件可以打印，请输出"NULL"
 * 文件的编号定义为”IN P NUM”事件发生第x次，此处待打印文件的编号为x。编号从1开始。
 *
 *  输入示例：
 * 7
 * IN 1 1
 * IN 1 2
 * IN 1 3
 * IN 2 1
 * OUT 1
 * OUT 2
 * OUT 2
 *
 * 输出示例：
 * 3
 * 4
 * NULL
 *
 * @author zlrui
 * @since 1.0
 */
public class PrinterQueue {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int eventCnt = Integer.parseInt(sc.nextLine());
        // Integer[] ==> 二维数组，用于映射 文件id 和 优先级
        PriorityQueue<Integer[]>[] printers = new PriorityQueue[5];
        for (int i = 0; i < printers.length; i++) {
            printers[i] = new PriorityQueue<>((o1, o2)-> o2[1] - o1[1]);
        }
        int eventCount = 0;

        while (eventCnt-- > 0 && sc.hasNext()) {
            String event = sc.nextLine();
            String[] param = event.split(" ");
            switch (param[0]){
                case "IN":
                    // 注意下标
                    processPrintInTask(printers[Integer.parseInt(param[1])-1], Integer.parseInt(param[2]), ++eventCount);
                    break;
                case "OUT":
                    processPrintOutTask(printers[Integer.parseInt(param[1])-1]);
                    break;
                default:
            }
        }
    }


    public static void processPrintInTask (PriorityQueue<Integer[]> printerQueue, int priority, int fileId) {
        Integer[] item = new Integer[2];
        item[0] = fileId;
        item[1] = priority;
        printerQueue.offer(item);
    }

    public static void processPrintOutTask (PriorityQueue<Integer[]> printerQueue) {
        if (printerQueue.isEmpty()) {
            System.out.println("NULL");
        } else {
            Integer[] poll = printerQueue.poll();
            System.out.println(poll[0]);
        }
    }

}
