package org.example.logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zlrui
 * @since 1.0
 */
public class MatchedTuple {

    public static void main(String[] args) {

        //int[] dataA = {1,2,4,4,2,1};
        //int[] dataB = {1,3,2};

        Scanner sc = new Scanner(System.in);
        String num1 = sc.nextLine();
        String num2 = sc.nextLine();
        int[] dataA = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dataB = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Integer, Integer> counterMap = new HashMap<>();
        for (int x : dataA) {
            counterMap.put(x, counterMap.getOrDefault(x, 0)+1);
        }

        int result = 0;
        for (int y : dataB) {
            if (counterMap.containsKey(y)) {
                result += counterMap.get(y);
            }
        }
        System.out.println(result);
    }

}
