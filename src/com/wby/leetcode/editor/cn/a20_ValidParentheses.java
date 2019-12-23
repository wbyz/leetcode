//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串

package com.wby.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Stack;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
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
public class a20_ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new a20_ValidParentheses().new Solution();
        String s = "()";
        boolean valid = solution.isValid(s);
        System.out.println(valid);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean isValid(String s) {
            if (s.isEmpty())
                return true;
            Stack<Character> stack = new Stack<Character>();
            for (char c : s.toCharArray()) {
                if (c == '(')
                    stack.push(')');
                else if (c == '{')
                    stack.push('}');
                else if (c == '[')
                    stack.push(']');
                else if (stack.empty() || c != stack.pop())
                    return false;
            }
            if (stack.empty())
                return true;
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        private HashMap<Character, Character> mappings;

        public boolean isValid(String s) {
            if (s == null) return false;
            if ((s.length() & 1) == 1) return false;

            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // 判断c是否是map中的key
                if (this.mappings.containsKey(c)) {
                    // 判断stack是否为空，为空赋值'#'，不为空弹出顶部字符并赋值给topElement
                    char topElement = stack.empty() ? '#' : stack.pop();
                    // 判断topElement和map中key对应的value是否相同，不同返回false
                    if (topElement != this.mappings.get(c)) return false;
                } else stack.push(c);
            }

            return stack.isEmpty();
        }

        public Solution2() {
            this.mappings = new HashMap<>();
            this.mappings.put(')', '(');
            this.mappings.put('}', '{');
            this.mappings.put(']', '[');
        }
    }
}