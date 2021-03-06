//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 
//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学

package com.wby.leetcode.editor.cn;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2019/12/09 19:12
 * Description:
 * 本题如果不考虑溢出问题，是非常简单的。解决溢出问题有两个思路，第一个思路是通过字符串转换加try catch的方式来解决，第二个思路就是通过数学计算来解决。
 * 由于字符串转换的效率较低且使用较多库函数，所以解题方案不考虑该方法，而是通过数学计算来解决。
 * 通过循环将数字x的每一位拆开，在计算新值时每一步都判断是否溢出。
 * 溢出条件有两个，一个是大于整数最大值MAX_VALUE，另一个是小于整数最小值MIN_VALUE，设当前计算结果为ans，下一位为pop。
 * 从ans * 10 + pop > MAX_VALUE这个溢出条件来看
 * <p>
 * 当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
 * 当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
 * <p>
 * <p>
 * 从ans * 10 + pop < MIN_VALUE这个溢出条件来看
 * <p>
 * 当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
 * 当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数
 * <p>
 * 假设范围 -2^9 ~ 2^9-1 => -512 ~ 511
 * x = 125
 * 将其倒转后为 521, 溢出
 * 计算到 ans = 52 ， pop = 1 => ans > 511/10 ,溢出
 * <p>
 * x = 315
 * 将其倒转后为 513 , 溢出
 * 计算到 ans = 51 , pop = 3 => ans == 511/10 且 pop > 1 , 溢出
 * <p>
 * 数字   2^1   2^2   2^4   2^8   2^16
 * 个位   2     4      6     6     6
 * (个位)2^31 = 2^1 * 2^2 * 2^4 * 2^8 * 2^16
 * = 6 * 6 * 6 * 4 * 2
 * = 8
 */
public class a7_ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new a7_ReverseInteger().new Solution();
        int reverse = solution.reverse(-2341234);
        System.out.println(reverse);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse(int x) {
            int rev = 0;
            while (x != 0) {
                int pop = x % 10;
                x /= 10;
                if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
                if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
                rev = rev * 10 + pop;
            }
            return rev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}