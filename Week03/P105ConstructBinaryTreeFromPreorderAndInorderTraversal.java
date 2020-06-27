//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class P105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        // TODO
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        private int[] preorder;
        // 使用 hash 加速中序数组
        private Map<Integer, Integer> inHash = new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // 初始化
            this.preorder = preorder;
            for (int i = 0; i < inorder.length; i++) {
                inHash.put(inorder[i], i);
            }
            return buildTreeHelper(0, preorder.length - 1, 0, inorder.length - 1);
        }

        /**
         *
         * @param preLeft 前序遍历数组左边界下标
         * @param preRight 前序遍历数组右边界下标
         * @param inLeft 中序遍历数组左边界下标
         * @param inRight 中序遍历数组右边界下标
         * @return
         */
        private TreeNode buildTreeHelper(int preLeft, int preRight, int inLeft, int inRight) {
            if (preLeft > preRight || inLeft > inRight) return null;

            // pivot
            int pivot = preorder[preLeft];
            int pivotIndex = inHash.get(pivot);
            TreeNode root = new TreeNode(pivot);
            root.left = buildTreeHelper(preLeft + 1, preLeft + pivotIndex - inLeft, inLeft, pivotIndex - 1);
            root.right = buildTreeHelper(preLeft + pivotIndex - inLeft + 1, preRight, pivotIndex + 1, inRight);

            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}