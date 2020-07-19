//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。 
//
// 示例: 
//
// 输入: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4 
// Related Topics 动态规划


package leetcode.editor.cn;

public class P221MaximalSquare{
    public static void main(String[] args) {
         Solution solution = new P221MaximalSquare().new Solution();
         // TODO
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (null == matrix || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        int maxSize = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
                    }
                }
                maxSize = Math.max(maxSize, dp[i][j]);
            }
        }
        return maxSize * maxSize;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}