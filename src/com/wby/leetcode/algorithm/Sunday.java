package com.wby.leetcode.algorithm;

import java.util.Arrays;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 2020/1/7
 * package_name: com.wby.leetcode.algorithm
 * <p>
 * description:
 * 相似的查找算法有 KMP，BM，Horspool
 *
 * 一、Sunday 匹配机制
 * 1、匹配机制非常容易理解：
 *
 *      ·目标字符串 String
 *      ·模式串 Pattern
 *      ·当前查询索引 idx （初始为 0）
 *      ·待匹配字符串 str_cut : String [ idx : idx + len(Pattern) ]
 *
 * 2、每次匹配都会从 [[目标字符串中]] 提取 [[待匹配字符串]] 与 [[模式串]] 进行匹配：
 *
 *      ·若匹配，则返回当前 idx
 *      ·不匹配，则查看 [[待匹配字符串与]] 的后一位字符 c：
 *          若c存在于Pattern中，则 idx = idx + 偏移表[c]
 *          否则，idx = idx + len(pattern)
 *
 * Repeat Loop 直到 idx + len(pattern) > len(String)
 *
 * 二、偏移表
 * 偏移表的作用是存储每一个在 [[模式串]] 中出现的字符，
 * 在 [[模式串]] 从左往右最后一次出现的位置到尾部的距离 +1，
 * 或者 [[模式串]] 长度减去从左往右最后一次出现的位置 （len(pattern) - i）
 *
 * 例如 baab：
 *
 *      ·a 的偏移位就是 2 :
 *          最后一个a出现的位置距离尾部距离+1 => 1+1 = 2
 *          len(pattern) - i => 4-2 = 2
 *          
 *      ·b 的偏移位就是 1 :
 *          0+1 = 1
 *          4-3 = 1
 *
 *      ·其他的均为 5 :
 *          4+1 = 5
 *          5-0 = 5
 *
 * 三、举例
 * String: checkthisout
 * Pattern: this
 *
 * Step 1:
 *
 *      idx = 0
 *      待匹配字符串为：chec
 *      因为 chec != this
 *      所以查看 chec 的下一个字符 k
 *      k 不在 Pattern 里
 *      所以查看 偏移表，idx = idx + 5
 *
 * Step 2:
 *
 *      idx = 5
 *      待匹配字符串为：this
 *      因为 this == this
 *      匹配，所以返回 55
 *
 * 四、算法分析
 *      最坏情况：O(nm)O(nm)
 *      平均情况：O(n)O(n)
 */
public class Sunday {
    // 计算偏移表
    public int[] calShiftMat(String st){
        int[] dic = new int[127];
        Arrays.fill(dic, -1);
        int length = st.length();
        for (int i = length -1; i >= 0; i--){
            char c = st.charAt(i);
            System.out.println(c + "\t: " + (int)c + "\t: " + dic[c]);
            if (dic[c] == -1){
                dic[c] = length - i;
            }
        }

        return dic;
    }

    public int sunday(String haystack, String needle){
        // 其他情况判断
        if (needle == null || needle.length() > haystack.length()) return -1;
        if (needle.equals("")) return 0;

        // 初始化偏移表 和 当前索引头指针 和 下一个字符的索引指针(当前索引尾指针+1)
        int[] dic = calShiftMat(needle);
        int subIdx = 0;
        int preIdx = subIdx+needle.length();
        String str_cut = "";

        // run
        while (preIdx <= haystack.length()){
            str_cut = haystack.substring(subIdx, preIdx);
            if (needle.equals(str_cut)){
                return subIdx;
            }

            // 边界处理 注意是[[大于等于]]！！！
            if (preIdx >= haystack.length()){
                return -1;
            }
            // 不匹配情况下，根据下一个字符在偏移表中是否存在偏移(!=-1)，根据偏移移动idx
            char c = haystack.charAt(preIdx);
            if (dic[c] != -1){
                subIdx += dic[c];
            } else {
                subIdx = preIdx + 1;
            }

            preIdx = subIdx + needle.length();
        }
        return -1;
    }
    public static void main(String[] args) {
        String st = "ads gas gg";
        String pt = "gas g";

        int i = new Sunday().sunday(st, pt);
        System.out.println(i);
    }
}
