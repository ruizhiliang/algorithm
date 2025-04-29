package org.example.logic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目描述
 * 现有两个整数数组，需要你找出两个数组中同时出现的整数，并按照如下要求输出:
 * 1、有同时出现的整数时，先按照同时出现次数(整数在两个数组中都出现并且出现次数较少的那个)进行归类，然后按照出现次数从小到大依次按行输出。
 * 2、没有同时出现的整数时，输出NULL。
 * 输入描述
 * 第一行为第一个整数数组、第二行为第二个整数数组，每行数据中整数与整数之间以英文逗号分隔、整数的取值范围为[-200.2001，数组长度只 的范围为[1,10000]之间的整数。
 * 输出描述
 * 按照出现次数从小到大依次按行输出，每行输出的格式为:出现次数:该出现次数下的整数 升序排序只的结果格式中的":"为英文冒号，整数间以英文逗号分隔。
 *
 * @author zlrui
 * @since 1.0
 */
public class SameNumAppearance {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        TreeMap<Integer, Integer> map1 = new TreeMap<>();
        for (String item : str1.split(",")) {
            int i = Integer.parseInt(item);
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }

        TreeMap<Integer, Integer> map2 = new TreeMap<>();
        for (String item : str2.split(",")) {
            int i = Integer.parseInt(item);
            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }

        TreeMap<Integer, List<Integer>> result = new TreeMap<>();
        Iterator<Map.Entry<Integer, Integer>> iterator = map1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            int key = next.getKey();
            if (map2.containsKey(key)) {
                int count1 = next.getValue();
                int count2 = map2.get(key);
                int cnt = count2 > count1 ? count1 : count2;
                if (result.containsKey(cnt)) {
                    List<Integer> list = result.get(cnt);
                    list.add(key);
                } else {
                    List<Integer> list = new LinkedList<>();
                    list.add(key);
                    result.put(cnt, list);
                }
            }
        }

        if (result.isEmpty()) {
            System.out.println("NULL");
        } else {

            Iterator<Map.Entry<Integer, List<Integer>>> it = result.entrySet().iterator();
            while (it.hasNext()) {
                StringBuilder sb = new StringBuilder();
                Map.Entry<Integer, List<Integer>> next = it.next();
                sb.append(next.getKey()).append(":").append(next.getValue().stream().map(String::valueOf).collect(Collectors.joining(",")));
                System.out.println(sb);
            }
        }
    }

}
