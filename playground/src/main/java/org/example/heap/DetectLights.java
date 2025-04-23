package org.example.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * AI四识别到面板上有N(1≤N≤100)个指示灯，灯大小一样，任意两个之间无重叠。
 * 由于AI识别误差，每次别到的指示灯位置可能有差异，以4个坐标值描述AI识别的指示灯的大小和位置(左上角x1,y1，右下角x2,y2),
 * 请输出先行后列排序的指示灯的编号，排序规则:
 * 1.每次在尚未排序的灯中挑选最高的灯作为的基准灯，
 * 2.找出和基准灯属于同一行所有的灯进行排序。两个灯高低偏差不超过灯半径算同一行(即两个灯坐标的差≤灯高度的一半)。
 * 输入描述
 * 第一行为N，表示灯的个数接下来N行，每行为1个灯的坐标信息，格式为：
 * 编号 x1 y1 x2 y2
 *
 * ·编号全局唯一
 * ·1≤编号≤100
 * ·0≤x1<x2≤ 1000
 * ·0≤y1<y2≤ 1000
 *
 * 输出描述
 * 排序后的编号列表，编号之间以空格分隔
 *
 * 解题思路
 * 题目的要求是将AI识别到的多个指示灯编号进行排序。排序需要遵看以下规则:
 * 1.识别灯的位置:每个指示灯的坐标信息包含了左上角(x1,y1)和右下角(x2,y2)，即灯的边界矩形。灯的位置定义由左上角y1的高度表示，即y1越小，灯越靠上。
 * 2.排序的规则:排序应当按行顺序排列，具体方法是：
 *  行的定义:每次找出尚未排序的灯中最高的灯，作为基准灯，然后找出与该基准灯处于同一行的所有灯。
 *  同一行判定:如果两个灯的垂直位置差(y1之间的差)不迢过灯高度的一半(高度为y2-y1)，则认为它们处于同一行。
 *  行内排序:在确定的同一行中，按列(即x1从小到大)顺多对灯排序。空格分隔。
 * 3.输出格式:按照上面排序后的编号顺序输出编号，编号之间用
 *
 * @author zlrui
 * @since 1.0
 */
public class DetectLights {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());

        PriorityQueue<Light> yHeap = new PriorityQueue<>(Comparator.comparingInt(l -> l.y));
        for (int i=0; i<num; i++) {
            String s = sc.nextLine();
            String[] data = s.split(" ");
            int id = Integer.parseInt(data[0]);
            int x1 = Integer.parseInt(data[1]);
            int y1 = Integer.parseInt(data[2]);
            int x2 = Integer.parseInt(data[3]);
            int y2 = Integer.parseInt(data[4]);

            int xc = (x1 + x2) / 2;
            int yc = (y1 + y2) / 2;
            int r = (x2 - x1) / 2;
            Light light = new Light(id, xc, yc, r);
            yHeap.offer(light);
        }

        //int count = 0;
        PriorityQueue<Light> xHeap = new PriorityQueue<>(Comparator.comparingInt(l -> l.x));
        StringJoiner sj = new StringJoiner(" ");
        while (!yHeap.isEmpty()) {
            //System.out.println("第"+ (count ++) +"行");

            // 一行的基准灯
            Light ref = yHeap.poll();
            xHeap.offer(ref);

            // 需要判断是否分行（高度不超过半径不分行）
            while (!yHeap.isEmpty() && Math.abs(yHeap.peek().y - ref.y) <= ref.r) {
                xHeap.offer(yHeap.poll());
            }

            // 每行的数据按X排序输出
            while (!xHeap.isEmpty()) {
                sj.add(String.valueOf(xHeap.poll().getId()));
            }
        }

        System.out.println(sj);
    }


    static class Light {
        int id;

        int x;
        int y;

        int r;

        public Light(int id, int x, int y, int r) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        @Override
        public String toString() {
            return "Light{" +
                    "id=" + id +
                    ", x=" + x +
                    ", y=" + y +
                    ", r=" + r +
                    '}';
        }
    }

}
