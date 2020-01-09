package com.wby.leetcode.algorithm;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2020/1/8
 * package_name: com.wby.leetcode.algorithm
 * <p>
 * description:
 */
public class BinarySearch {

    public int binarySearch(int[] nums, int target){
        int length = nums.length;
        if (nums[length-1] < target) return length;

        int left = 0;
        int right = length;

        // 目前取左中位数 首先
        // 如果去右中位数
        // 由于最后一定有left==right 所以最后返回left\right都可以
        while (left < right){

            // 取左中位数
            int mid = (left + right) >>> 1;
            // 取右中位数
            // int mid = (left + right + 1) >>> 1;

            // 通过夹逼法，最后可以得到唯一值，不需要每次循环时判断，所以省略
            // if (nums[mid] == target) return mid;

            // 排除中位数逻辑 ，根据需求具体设计，此处是中位数小于target
            // 需要注意判断后的执行，如果出现死循环，更换左右中位数
            // 思考死循环出现逻辑
            if (nums[mid] < target) {   // 左中位数小于target,进入右区间
                // 则左边界排除中位数
                left = mid + 1;
                System.out.println("right：" + left + " : " + right + " : " + mid);
            } else {    // 左中位数大于于等于target，进入左区间
                // 则右边界不要排除中位数
                right = mid;
                System.out.println("left：" + left + " : " + right + " : " + mid);
            }
        }

        return left;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,7,9};
        int target = 8;
        int i = new BinarySearch().binarySearch(nums, target);

        System.out.println(i);
    }
}
