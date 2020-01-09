//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window


package com.wby.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date:
 * description :
 * 方法一： 字符表可以用数组也可以用hashmap
 * 一个字符表对应每个字符最后的索引位置
 * 一个头指针指向每次无重复子串第一个索引位置
 * 一个尾指针指向每次移动位置
 * 一个int变量保存长度最大值
 * 当尾指针遇到第一个重复字符，比较长度最大值，头指针移动到上一个重复字符的下一位索引，
 * 尾指针每次移动更新字符表索引位置
 * 尾指针到最后结束循环
 * <p>
 * 方法二：
 * 通过使用 HashSet 作为滑动窗口。
 * 循环判断 条件tail<s.length()
 * 左移tail指针，若set存在key，先比较max和set长度，取最大值，然后移动head知道set中没有该key，然后在插入该key
 * 若set不存在key，插入该key
 * 退出循环在取一次max和set长度最大值
 * 返回max
 * <p>
 * 方法三：
 * 通过使用 HashSet 作为滑动窗口，我们可以用 O(1) 的时间来完成对字符是否在当前的子字符串中的检查。
 * <p>
 * 滑动窗口是 [数组/字符串] 问题中常用的抽象概念。
 * 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i, j)（左闭，右开）。
 * 而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。
 * 例如，我们将 [i, j) 向右滑动 1 个元素，则它将变为 [i+1, j+1)（左闭，右开）。
 * <p>
 * 回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)（最初 j = i）中。
 * 然后我们向右侧滑动索引 j，如果它不在 HashSet 中，我们会继续滑动 j。
 * 直到 s[j] 已经存在于 HashSet 中。此时，我们找到的没有重复字符的最长子字符串将会以索引 i 开头。
 * 如果我们对所有的 i 这样做，就可以得到答案。
 * 时间复杂度：O(2n) = O(n)O(2n)=O(n)，在最糟糕的情况下，每个字符将被 ii 和 jj 访问两次。
 * <p>
 * 空间复杂度：O(min(m, n))O(min(m,n))，与之前的方法相同。滑动窗口法需要 O(k)O(k) 的空间，其中 kk 表示 Set 的大小。而 Set 的大小取决于字符串 nn 的大小以及字符集 / 字母 mm 的大小。
 * <p>
 * 方法四：（官方） 同方法一
 */
public class a3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new a3_LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbcde"));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length(), ans = 0;
            Map<Character, Integer> map = new HashMap<>(); // current index of character
            // try to extend the range [i, j]
            for (int j = 0, i = 0; j < n; j++) {
                if (map.containsKey(s.charAt(j))) {
                    i = Math.max(map.get(s.charAt(j)), i);
                }
                ans = Math.max(ans, j - i + 1);
                map.put(s.charAt(j), j + 1);
            }
            return ans;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 方法三：
     */
    class Solution3 {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            Set<Character> set = new HashSet<>();
            int ans = 0, i = 0, j = 0;
            while (i < n && j < n) {
                // try to extend the range [i, j]
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j++));
                    ans = Math.max(ans, j - i);
                } else {
                    set.remove(s.charAt(i++));
                }
            }
            return ans;

        }
    }

    /**
     * 方法二：最好成绩
     * 解答成功:
     * 执行耗时:14 ms,击败了47.00% 的Java用户
     * 内存消耗:37.5 MB,击败了92.27% 的Java用户
     */
    class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            if (s == null) return 0;
            if (s.length() == 0) return 0;

            int head = 0, tail = 0, max = 0;
            Set<Character> set = new HashSet<>();

            while (tail < s.length()) {
                char t = s.charAt(tail);

                if (tail != head && set.contains(t)) {
                    max = max < set.size() ? set.size() : max;
                    while (set.contains(t)) {
                        set.remove(s.charAt(head++));
                    }
                }
                set.add(t);
                tail++;
            }
            return max < set.size() ? set.size() : max;
        }
    }

    /**
     * 方法1：最好成绩
     * 解答成功:
     * 执行耗时:10 ms,击败了75.87% 的Java用户
     * 内存消耗:37.2 MB,击败了93.98% 的Java用户
     */
    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            if (s == null) return 0;
            if (s.length() == 0) return 0;
            Map<Character, Integer> map = new HashMap<>();
            int head = 0, tail = 0, max = 0;

            while (tail < s.length()) {
                char t = s.charAt(tail);

                if (tail != head && map.containsKey(t)) {
                    if (map.get(t) >= head) {
                        if (max < (tail - head)) {
                            max = tail - head;
                        }
                        head = map.get(t) + 1;
                    }
                }
                map.put(t, tail);
                tail++;
            }
//            if (head != 0) head += 1;
            if (max < (tail - head)) {
                max = tail - head;
            }
            return max;
        }
    }
}