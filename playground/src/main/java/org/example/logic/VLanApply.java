package org.example.logic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zlrui
 * @since 1.0
 */
public class VLanApply {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String all = scanner.nextLine();
        int target = Integer.parseInt(scanner.nextLine());

        // 构建TreeMap
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for (String item : all.split(",")) {
            if (item.contains("-")) {
                String[] split = item.split("-");
                treeMap.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            } else {
                int i = Integer.parseInt(item);
                treeMap.put(i, i);
            }
        }

        //System.out.println(treeMap.keySet());

        /*Set<Integer> set = treeMap.keySet();
        int left = 0;
        int right = treeMap.size();
        while (left < right) {
            int middle = left + (right - left) / 2;

        }*/

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        // 可以在第一次遍历时就处理掉
        Iterator<Map.Entry<Integer, Integer>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            int start = next.getKey();
            int end = next.getValue();
            if (start <= target && end >= target) {
                if (start == end) {
                    iterator.remove();
                    break;
                } else {
                    // 拆分这个
                    iterator.remove();
                    if (start == target) {
                        //extra = (start + 1) + "," + end;
                        //treeMap.put(start + 1, end);
                        list.add(new AbstractMap.SimpleEntry<>(start+1, end));
                    } else if(end == target) {
                        //extra = start+ "," + (end-1);
                        //treeMap.put(start, end-1);
                        list.add(new AbstractMap.SimpleEntry<>(start, end-1));
                    } else {
                        //extra = (start + 1) + "," + (end-1);
                        //treeMap.put(start, target-1);
                        //treeMap.put(target+1, end);
                        list.add(new AbstractMap.SimpleEntry<>(start, target-1));
                        list.add(new AbstractMap.SimpleEntry<>(target+1, end));
                    }
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : list) {
            treeMap.put(entry.getKey(), entry.getValue());
        }

        List<String> result = new ArrayList<>();
        iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            // 注意要转成
            if (next.getValue().intValue() == next.getKey().intValue()) {
                result.add(String.valueOf(next.getKey()));
            } else {
                result.add(next.getKey() + "-" + next.getValue());
            }
        }
        String collect = result.stream().collect(Collectors.joining(","));
        System.out.println(collect);
    }

}
