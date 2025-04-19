package org.example.practice;

import java.util.*;

/**
 * @author zlrui
 * @since 1.0
 */
public class PokerLongestChain {

    public static void main(String[] args) {

        // 索引0 == 空
        // 索引1 == 空
        // 索引2 == 空
        // 索引3 == 牌面3
        // 索引4 == 牌面4
        // 索引5 == 牌面5
        // ...
        // 索引14 == 牌面A
        // 索引15 == 牌面2 ==>其实不需要
        // 索引16 == 小王 ==>其实不需要
        // 索引17 == 大王 ==>其实不需要

        int[] pokerNum = {0, 0, 0,  4,4,4,4,4,4,4,4,4,4,4,4,  0, 0, 0};

        Scanner scanner = new Scanner(System.in);
        String selfPokers = scanner.nextLine();
        String passed = scanner.nextLine();

        for (String item :selfPokers.split("-")) {
            pokerNum[convertToInt(item)] --;
        }

        for (String item :passed.split("-")) {
            pokerNum[convertToInt(item)] --;
        }

        // 如果不看长度，只看最大的
        // onlyRequireMax(pokerNum);

        int maxLen = 0;
        int maxLeft = -1;

        List<String> result = new LinkedList<>();
        // left作为左侧可用的第一张牌
        int left = -1;
        for (int right=3; right<=15; right++) {
            // 中断了 或者 结束了
            if (pokerNum[right] <= 0 || right == 15) {
                if (left != -1 && right - left >= 5) {
                    // 存在顺子
                    //result.add(collectCards(left, right-1));

                    // ** best : 遍历时解决比较的问题 **
                    if (right - left > maxLen) {
                        maxLen = right - left;
                        maxLeft = left;
                    } else if (right - left == maxLen && maxLeft < left) {
                        // 更新最大的索引
                        maxLeft = left;
                    }
                    // ** end **
                }
                left = -1;
            } else {
                if (left == -1) {
                    left = right;
                }
            }
        }


        //System.out.println("MaxLen:"+maxLen +", maxLeft: " + maxLeft);

        /*if (result.isEmpty()) {
            System.out.println("NO-CHAIN");
        } else {
            // 如果多个符合条件的顺子，选出最长且牌面最大的
            // 10-J-Q-K-A vs 3-4-5-6-7 是因为长度取胜了。。。结果没问题，如果要演进，最好是索引排序 或者在计算时就处理得到
            Collections.sort(result, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() != o2.length() ? o2.length() - o1.length() : o2.compareTo(o1);
                }
            });

            System.out.println(result.get(0));
        }*/

        if (maxLen == 0) {
            System.out.println("NO-CHAIN");
        } else {
            System.out.println(collectCards(maxLeft, maxLeft+maxLen-1));
        }
    }

    public static int convertToInt (String card) {
        switch (card) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            case "2":
                return 15;
            case "B":
                return 16;
            case "C":
                return 17;
            default:
                return Integer.parseInt(card);
        }
    }

    public static String convertToString (int card) {
        switch (card) {
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";
            case 15:
                return "2";
            case 16:
                return "B";
            case 17:
                return "C";
            default:
                return String.valueOf(card);
        }
    }

    public static String collectCards (int left, int right) {
        StringBuilder sb = new StringBuilder();
        for (int i=left; i<=right; i++) {
            sb.append(convertToString(i)+"-");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }


    public static void onlyRequireMax(int[] pokerNum) {
        int right = -1;
        int left = -1;
        for (int p=14; p>=3; p--) {
            left = p;
            if (pokerNum[p] > 0) {
                if (p > right) {
                    right = p;
                }
            } else {
                // 中断了
                if (right - left >= 5) {
                    System.out.println(collectCards(left+1, right));
                    return;
                } else {
                    // 重置索引
                    left = -1;
                    right = -1;
                }
            }
        }

        if (left < 0 || right - left + 1 < 5) {
            System.out.println("NO-CHAIN");
        } else {
            collectCards(left, right);
        }
    }

}
