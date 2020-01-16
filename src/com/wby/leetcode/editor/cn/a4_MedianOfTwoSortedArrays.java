//给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。 
//
// 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 示例 1: 
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法


package com.wby.leetcode.editor.cn;

/**
 * author: wbyz
 * email: agsggdhip@gmail.com
 * date:
 * description :
 * 方法一：
 *      初始化一个boolean类型变量 flag = false
 *      初始化一个int类型变量len，值为 ( nums1.length + nums2.length + 1 ) / 2
 *      若值有小数 ;  flag = true ;
 *
 *      初始化指针 i = 0, j = 0, k = 0;
 *      初始化 max1，max2
 *      i和j分别对应两个数字指针，k为总次数
 *      while(k <= len){
 *          伪代码
 *          取出最大的两个值
 *          依次比较最大值直到跳出循环
 *      }
 *
 *      取出 max1，max2
 *      如果 flag = true 取两个数平均值
 *      如果 flag = false 取最大值max1
 *
 * 方法二：
 * 方法：递归法
 * 为了解决这个问题，我们需要理解 “中位数的作用是什么”。在统计中，中位数被用来：
 *      将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。
 *
 * 如果理解了中位数的划分作用，我们就很接近答案了。
 *
 * 首先，让我们在任一位置 i 将 A 划分成两个部分：
 *           left_A             |        right_A
 *     A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
 *
 * 由于 A 中有 mm 个元素， 所以我们有 m+1m+1 种划分的方法（i = 0 \sim mi=0∼m）。 我们知道：
 *      len(left_A) = i, right_A = m - i. 注意：当 i = 0 时，left_A 为空集， 而当 i = mi=m 时, right_A 为空集。
 *
 * 采用同样的方式，我们在任一位置 jj 将 B 划分成两个部分：
 *           left_B             |        right_B
 *     B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
 *
 * 将 left_A 和 left_B 放入一个集合，并将 right_A 和 right_B 放入另一个集合。 再把这两个新的集合分别命名为 left_part 和 right_part：
 *           left_part          |        right_part
 *     A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
 *     B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
 *
 * 如果我们可以确认：
 *      len(left_part)=len(right_part)
 *      max(left_part)≤min(right_part)
 *
 * 那么，我们已经将 {A,B} 中的所有元素划分为相同长度的两个部分，且其中一部分中的元素总是大于另一部分中的元素。那么：
 *      median=(max(left_part)+min(right_part))/2
 *
 * 要确保这两个条件，我们只需要保证：
 *      i + j = m - i + n - j（或：m - i + n - j + 1）
 * 如果 n≥m，只需要使 i = 0 ~ m, j = ( m + n + 1 ) / 2 −i , B[j−1]≤A[i] 以及 A[i−1]≤B[j]
 *
 * ps.1 为了简化分析，我假设 A[i−1] , B[j−1] , A[i],B[j] 总是存在，哪怕出现 i=0，i=m，j=0，或是 j=n 这样的临界条件。
 *      我将在最后讨论如何处理这些临界值。
 * ps.2 为什么n≥m？由于0≤i≤m 且 j = ( m + n + 1 ) / 2 - i ，我必须确保 j 不是负数。如果 n < mn<m，那么 j 将可能是负数，而这会造成错误的答案。
 *
 * 所以，我们需要做的是：
 *      在 [0，m] 中搜索并找到目标对象 i，以使：
 *          B[j−1]≤A[i] 且 A[i−1]≤B[j], 其中 j = ( m + n + 1 ) / 2 -i
 *
 *      接着，我们可以按照以下步骤来进行二叉树搜索：
 *          1. 设 imin=0，imax=m, 然后开始在 [imin,imax] 中进行搜索。
 *          2. 令 i = ( imin + imax ) / 2 ， j = ( m + n + 1 ) / 2 - i
 *
 *      现在我们有 len(left_part)=len(right_part)。 而且我们只会遇到三种情况：
 *          B[j−1]≤A[i] 且 A[i−1]≤B[j]：   这意味着我们找到了目标对象 i，所以可以停止搜索。
 *
 *          B[j−1]>A[i]：                 这意味着 A[i] 太小，我们必须调整 i 以使  B[j−1]≤A[i]。
 *          我们可以增大 i 吗？ 是的，因为当 i 被增大的时候，j 就会被减小。
 *              因此 B[j−1] 会减小，而 A[i] 会增大，那么 B[j−1]≤A[i] 就可能被满足。
 *          我们可以减小 i 吗？ 不行，因为当 i 被减小的时候，j 就会被增大。
 *              因此 B[j−1] 会增大，而 A[i] 会减小，那么  B[j−1]≤A[i] 就可能不满足。
 *          所以我们必须增大 i。也就是说，我们必须将搜索范围调整为 [i+1,imax]。
 *              因此，设 imin = i + 1，并转到步骤 2。
 *
 *          A[i−1]>B[j]：                 这意味着 A[i−1] 太大，我们必须减小 i 以使 A[i−1]≤B[j]。
 *              也就是说，我们必须将搜索范围调整为 [imin,i−1]。
 *              因此，设 imax=i−1，并转到步骤 2。
 *
 *      当找到目标对象 i 时，中位数为：
 *          max(A[i−1],B[j−1]) , 当 m + n 为奇数时
 *          ( max(A[i−1],B[j−1]) + min(A[i],B[j]) ) / 2 , 当 m + nm+n 为偶数时
 *
 * 现在，让我们来考虑这些临界值 i=0,i=m,j=0,j=n，此时 A[i−1],B[j−1],A[i],B[j] 可能不存在。
 * 其实这种情况比你想象的要容易得多。
 *
 * 我们需要做的是确保 max(left_part)≤min(right_part)。 因此，如果 i 和 j 不是临界值（这意味着 A[i−1],B[j−1],A[i],B[j] 全部存在）, 那么我们必须同时检查  B[j−1]≤A[i] 以及  A[i−1]≤B[j] 是否成立。
 * 但是如果 A[i−1],B[j−1],A[i],B[j] 中部分不存在，那么我们只需要检查这两个条件中的一个（或不需要检查）。
 * 举个例子，如果 i = 0，那么 A[i−1] 不存在，我们就不需要检查  A[i−1]≤B[j] 是否成立。
 * 所以，我们需要做的是：
 *
 * 在 [0，m][0，m] 中搜索并找到目标对象 i，以使：
 *
 * (j = 0 or i = m or B[j−1]≤A[i]) 或是 (i = 0 or j = n or A[i−1]≤B[j]), 其中 j = ( m + n + 1 ) / 2 - i
 *
 * 在循环搜索中，我们只会遇到三种情况：
 *
 * (j = 0 or i = m or B[j−1]≤A[i]) 或是 (i = 0 or j = n or A[i−1]≤B[j])，这意味着 i 是完美的，我们可以停止搜索。
 * j > 0 and i < m and B[j−1]>A[i] 这意味着 i 太小，我们必须增大它。
 * i > 0 and j < n and A[i−1]>B[j] 这意味着 i 太大，我们必须减小它。
 * 感谢 @Quentin.chen 指出：i < m  ⟹ j > 0 以及 i > 0 ⟹ j < n 始终成立，这是因为：
 * m≤n, i<m ⟹ j= ( m+n+1 ) / 2 - i > (m+n+1)/2 - m ≥ (2m+1)/2 −m≥0
 *
 * m≤n, i>0⟹j= (m+n+1)/2 −i < (m+n+1)/2 ≤ (2n+1)/2 ≤ n
 *
 * 所以，在情况 2 和 3中，我们不需要检查 j > 0j>0 或是 j < nj<n 是否成立。
 *
 */
public class a4_MedianOfTwoSortedArrays{
  public static void main(String[] args) {
       Solution solution = new a4_MedianOfTwoSortedArrays().new Solution();
      System.out.println(solution.findMedianSortedArrays(
              new int[]{1,3},
              new int[]{2}));
  }


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}