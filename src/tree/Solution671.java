package tree;

public class Solution671 {
	public int findSecondMinimumValue(TreeNode root) {
		if(root.left == null || root.right == null) return -1;
		int leftVal = root.left.val;
		int rightVal = root.right.val;
		int leftResult, rightResult;
        if(leftVal < rightVal) {
        	leftResult = findSecondMinimumValue(root.left);
        	if(leftResult == -1) return rightVal;
        	return Math.min(leftResult, rightVal);
        }else if(leftVal > rightVal) {
        	rightResult = findSecondMinimumValue(root.right);
        	if(rightResult == -1) return leftVal;
        	return Math.min(rightResult, leftVal);
        }else {
        	leftResult = findSecondMinimumValue(root.left);
        	rightResult = findSecondMinimumValue(root.right);

        	if(leftResult == -1) return rightResult;
        	if(rightResult == -1) return leftResult;
        	return Math.min(leftResult, rightResult);
        }
    }
}
