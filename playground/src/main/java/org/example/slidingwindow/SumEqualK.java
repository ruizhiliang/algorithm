package org.example.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 子数组和为 k（Subarray Sum Equals K）
 *
 * 给定一个整数数组，找出其和为 k 的子数组的数量。
 * 解法：使用滑动窗口 和 前缀和。
 *
 * 首先，我需要考虑如何高效地计算这个问题。直接的方法可能是暴力枚举所有可能的子数组，然后计算它们的和，看看是否等于k。这种方法的时间复杂度是O(n²)，对于较大的数组来说可能不够高效。比如，如果数组长度是n，那么子数组的数量是n*(n+1)/2，这在n很大的情况下会很慢。因此，我需要寻找更高效的方法。
 * 接下来，我应该想到是否有更优的算法。这时候，我想到前缀和的概念。前缀和数组可以记录数组从开始到当前位置的和，这样计算子数组的和就变得容易。例如，假设前缀和数组为prefix，那么子数组从i到j的和就是prefix[j] - prefix[i-1]。如果这个差值等于k，那么说明存在这样的子数组。因此，问题可以转化为寻找有多少对(i,j)使得prefix[j] - prefix[i-1] =k。
 * 这样，问题就转化为在前缀和数组中寻找有多少个这样的差值等于k。这时候，可以考虑使用哈希表来记录前缀和出现的次数。具体来说，我们可以遍历数组，计算当前的前缀和，并检查之前是否出现过当前前缀和减去k的值，如果存在的话，说明存在对应的子数组，数量就是之前出现的次数。同时，我们需要将当前的前缀和存入哈希表中，以便后续的计算。
 * 不过，这里需要注意一些细节。比如，初始时，前缀和为0的情况需要考虑。因为当当前前缀和等于k时，说明从开始到当前位置的子数组就是一个解。因此，初始时哈希表中应该存入一个{0:1}，表示前缀和为0出现了一次。
 * 举个例子，假设数组是[3,4,7,2,-3,1,4,2]，k=7。我们需要逐步计算前缀和，并记录每个前缀和出现的次数。初始时，哈希表里有{0:1}。然后：
 * 第一个元素3，前缀和是3。我们需要找是否存在3-7= -4，哈希表中没有，所以不增加计数。然后将3存入哈希表，此时哈希表是{0:1,3:1}。
 * 第二个元素4，前缀和是7。此时，7-7=0，哈希表中存在0，出现次数是1，所以计数加1。然后将7存入哈希表，此时哈希表是{0:1,3:1,7:1}。
 * 第三个元素7，前缀和是14。14-7=7，哈希表中有7出现1次，所以计数加1。然后将14存入哈希表。
 * 以此类推。
 *
 * @author zlrui
 * @since 1.0
 */
public class SumEqualK {

    public static void main(String[] args) {

        int[] data = {3,4,7,2,-3,1,4,2};
        int[] data2 = {3,4,3};
        int[] data3 = {1, -1, 1, -1, 7};
        int k = 7;

        int res1 = subarraySumFromQianwen(data2, k);
        System.out.println("from Qwen：" + res1);

        int res2 = solutionBySelf(data2, k);
        System.out.println("from Self：" + res2);

    }

    public static int solutionBySelf (int[] data, int k) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        // 没有任何数的时候，和为0，出现1次
        prefixSum.put(0, 1);

        int countK = 0;
        int currentSum = 0;

        for (int i = 0; i < data.length; i++) {
            currentSum += data[i];

            Integer appearTime = prefixSum.getOrDefault(currentSum - k, 0);
            countK += appearTime;

            prefixSum.put(currentSum, prefixSum.getOrDefault(currentSum, 0) + 1);
        }

        return countK;
    }

    public static int subarraySumFromQianwen (int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // 初始化前缀和为0的情况

        int count = 0;
        int currentSum = 0;

        for (int num : nums) {
            currentSum += num;

            // 检查是否存在前缀和为currentSum - k的记录
            count += prefixSumCount.getOrDefault(currentSum - k, 0);

            // 将当前前缀和存入哈希表
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

}
