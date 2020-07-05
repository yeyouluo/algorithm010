//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 请找出其中最小的元素。 
//
// 你可以假设数组中不存在重复元素。 
//
// 示例 1: 
//
// 输入: [3,4,5,1,2]
//输出: 1 
//
// 示例 2: 
//
// 输入: [4,5,6,7,0,1,2]
//输出: 0 
// Related Topics 数组 二分查找


package leetcode.editor.cn;

public class P153FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new P153FindMinimumInRotatedSortedArray().new Solution();
        // TODO
        System.out.println(solution.findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(solution.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMin(int[] nums) {
            int left = 0, right = nums.length - 1, middle = 0;
            while (left < right) {
                middle = (left + right) / 2;
                if (nums[middle] > nums[right]) left = middle + 1;
                else right = middle;
            }
            return nums[left];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}