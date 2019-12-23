//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表

package com.wby.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2019/12/03 13:11
 * Description:
 * 新建一个HashMap集合 map
 * 遍历数组nums，判断差值是否存在于map中，
 * 不存在，将元素存入map，回到循环继续遍历
 * 存在，返回差值元素和当前元素
 */
public class a1_TwoSum {
    public static void main(String[] args) {
        Solution solution = new a1_TwoSum().new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] out = solution.twoSum(nums, target);
        System.out.println(Arrays.toString(out));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
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
    }
//leetcode submit region end(Prohibit modification and deletion)

}