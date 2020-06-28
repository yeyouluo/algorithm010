//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法


package leetcode.editor.cn;

import java.util.*;

public class P47PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new P47PermutationsIi().new Solution();
        // TODO
        List<List<Integer>> list = solution.permuteUnique(new int[]{1, 1, 2});
        System.out.println(list);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }

            // 排序是剪枝的前提
            Arrays.sort(nums);

            boolean[] used = new boolean[len];
            Deque<Integer> path = new ArrayDeque<>(len);
            dfs(nums, len, 0, used, path, res);
            return res;
        }

        private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
            if (depth == len) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < len; ++i) {
                if (used[i]) {
                    continue;
                }

                // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }

                path.addLast(nums[i]);
                used[i] = true;

                dfs(nums, len, depth + 1, used, path, res);
                // 回溯
                used[i] = false;
                path.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}