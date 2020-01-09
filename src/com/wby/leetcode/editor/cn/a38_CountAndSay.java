//「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下： 
//
// 1.     1
//2.     11
//3.     21
//4.     1211
//5.     111221
// 
//
// 1 被读作 "one 1" ("一个一") , 即 11。 
//11 被读作 "two 1s" ("两个一"）, 即 21。 
//21 被读作 "one 2", "one 1" （"一个二" , "一个一") , 即 1211。 
//
// 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。 
//
// 注意：整数序列中的每一项将表示为一个字符串。 
//
// 
//
// 示例 1: 
//
// 输入: 1
//输出: "1"
// 
//
// 示例 2: 
//
// 输入: 4
//输出: "1211"
// 
// Related Topics 字符串

  
package com.wby.leetcode.editor.cn;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date: 
 * description :
 *      解题思路：从左到右，若有连续字符，以字符分别返回出现字符次数和该字符，最后依次拼接所有字符
 */
public class a38_CountAndSay{
  public static void main(String[] args) {
      Solution solution = new a38_CountAndSay().new Solution();

      for (int i = 0; i <= 30; i++){
          System.out.println(i + " = " + solution.countAndSay(i));
      }
  }
  

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String countAndSay(int n) {
        if (n == 0) return "";
        if (n == 1) return "1";

        String str = "1";
        for (int i = 2; i <= n; i++){
            StringBuilder sb = new StringBuilder();

            char pre = str.charAt(0);
            int count = 1;

            for (int j = 1; j < str.length(); j++){
                char curr = str.charAt(j);
                if (curr == pre){
                    count++;
                } else {
                    sb.append(count).append(pre);
                    pre = curr;
                    count = 1;
                }
            }

            sb.append(count).append(pre);
            str = sb.toString();
        }

        return str;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}