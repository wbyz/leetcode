//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找

  
package com.wby.leetcode.editor.cn;

import com.wby.leetcode.algorithm.BinarySearch;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 
 * description :
 * target : 2 \ 8
 * int[] :  1 3 | 5 | 7 9 11
 * index :  0 1 | 2 | 3 4 5
 *
 *      返回大于等于 target 的第 1 个数的索引，此时应该返回 具体逻辑见{@link BinarySearch 二分查找法}
 */
public class a35_SearchInsertPosition{
  public static void main(String[] args) {
       Solution solution = new a35_SearchInsertPosition().new Solution();

       int[] nums = new int[]{1,3,5,7,9,11};
       int target = 6;

      System.out.println(solution.searchInsert(nums, target));
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        // 非正常情况判断
        int len = nums.length;
        if (len == 0 || target > nums[len-1]){
            return len;
        }

        // 二分查找法   因为上面已经判断target大于最大值情况，right可以为len或者len-1
        // 若没有判断，则根据条件可能有大于最大值情况，需要返回等于数组长度值的索引（大于数组最大索引），所以right = len
        int left = 0;
        int right = len;

        while (left < right){
            // 中位数  无符号按位除法
            int mid = (left + right) >>> 1;

            // 排除中位数判断逻辑: 考虑返回的是大于等于target的第一个数索引值，则如果中位数小于target，它就应该被排除
            if (nums[mid] < target) {
                // 中位数小于目标值 更新左边界 排除中位数
                left = mid + 1;
            } else {
                // 由于结果可能包含中位数，保留中位数
                right = mid;
            }
        }

        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    // 递归 占用内存较大
    class Recursive {
        public int searchInsert(int[] nums, int target) {
            int pre = 0;
            int sub = nums.length-1;

            return run(nums, target, pre, sub);
        }

        public int run(int[] nums, int target, int left, int right){
            int result = 0;
            int middle = left + (right - left) / 2;
            if (target == nums[middle]){
                result = middle;
            }
            else if (target < nums[middle]){
                if (middle == left) result = middle ;
                else result = run(nums, target, left, middle-1);
            } else {
                if (right == middle) result = middle+1;
                else result = run(nums, target, middle+1, right);
            }

            return result;

        }
    }
}