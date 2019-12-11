package com.wby.leetcode.problem;


import java.util.Arrays;
import java.util.HashMap;

/**
 * author: wbyz
 * email: witzel.wang@jollycorp.com
 * date: 2019/12/03 13:11
 * Description:
 * 新建一个HashMap集合 map
 * 遍历数组nums，判断差值是否存在于map中，
 * 不存在，将元素存入map，回到循环继续遍历
 * 存在，返回差值元素和当前元素
 */
public class a01_two_sum {
    private static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;

        int[] solution = twoSum(nums, target);

        System.out.println(Arrays.toString(solution));

    }
}
