package com.wby.leetcode.problem;

import java.util.HashMap;
import java.util.Stack;

/**
 * author: wbyz
 * email: witzel.wang@jollycorp.com
 * date: 2019/12/11 10:58
 * Description:
 * 本体思路：
 *      初始化栈 S。
 *      一次处理表达式的每个括号。
 *      如果遇到开括号，我们只需将其推到栈上即可。这意味着我们将稍后处理它，让我们简单地转到前面的 子表达式。
 *      如果我们遇到一个闭括号，那么我们检查栈顶的元素。如果栈顶的元素是一个 相同类型的 左括号，那么我们将它从栈中弹出并继续处理。否则，这意味着表达式无效。
 *      如果到最后我们剩下的栈中仍然有元素，那么这意味着表达式无效。
 *
 * 健壮分析：
 *      若字符串为空值，返回false
 *      因为括号都是成对出现，若字符长度不为2的倍数返回false
 */
public class a20_valid_parentheses {
    public static void main(String[] args) {
        Solution solution = new a20_valid_parentheses().new Solution();
        String s = "()";
        boolean valid = solution.isValid(s);
        System.out.println(valid);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private HashMap<Character, Character> mappings;

        public boolean isValid(String s) {
            if (s == null) return false;
            if ((s.length() & 1) == 1) return false;

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // 判断c是否是map中的key
                if (this.mappings.containsKey(c)){
                    // 判断stack是否为空，为空赋值'#'，不为空弹出顶部字符并赋值给topElement
                    char topElement = stack.empty() ? '#' : stack.pop();
                    // 判断topElement和map中key对应的value是否相同，不同返回false
                    if (topElement != this.mappings.get(c)) return false;
                } else stack.push(c);
            }

            return stack.isEmpty();
        }

        public Solution() {
            this.mappings = new HashMap<>();
            this.mappings.put(')','(');
            this.mappings.put('}','{');
            this.mappings.put(']','[');
        }
    }
}
