package tree;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
	
	private static int tilt = 0;
	
	public static int findTilt3(TreeNode root){
		postTraverse(root);
		return tilt;
	}
	
	public static int postTraverse(TreeNode root){
		if(root == null) return 0;
		int leftSum = postTraverse(root.left);
		int rightSum = postTraverse(root.right);
		tilt += Math.abs(leftSum - rightSum);
		return leftSum + rightSum + root.val;
	}
	
	/**
	 * 563. Binary Tree Tilt
	 * @param root
	 * @return
	 */
	public static int findTilt2(TreeNode root){
		if(root == null){
			return 0;
		}
		adjustTree(root);
		int tilt = 0;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);
		TreeNode current = null;
		while(!stack.isEmpty()){
			current = stack.pop();
			tilt += calculateTilt2(current);
			if(current.left != null) stack.push(current.left);
			if(current.right != null) stack.push(current.right);
		}
		return tilt;
	}

	/**
	 * tool for 563.
	 * @param root
	 */
	public static void adjustTree(TreeNode root){
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode current = root;
		TreeNode lastAccess = null;
		while(current != null || !stack.isEmpty()){
			while(current != null){
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			while(current != null && (current.right == null || current.right == lastAccess)){
				current.val += (((current.left == null) ? 0 : current.left.val) + ((current.right == null ? 0 : current.right.val))); 
				lastAccess = current;
				if(stack.isEmpty()){
					return;
				}
				current = stack.pop();
			}
			stack.push(current);
			current = current.right;
		}
	}
	
	/**
	 * tool for 563.
	 * @param root
	 * @return
	 */
	public static int calculateTilt2(TreeNode root){
		if(root == null){
			return 0;
		}
		int leftVal = (root.left == null) ? 0 : root.left.val;
		int rightVal = (root.right == null) ? 0 : root.right.val;
		return Math.abs(leftVal - rightVal);
	}
	
	/**
	 * 563. Binary Tree Tilt
	 * rubbish solution.
	 * @param root
	 * @return
	 */
	public static int findTilt(TreeNode root) {
		if(root == null){
			return 0;
		}
		int tilt = 0;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);
		TreeNode current = null;
		while(!stack.isEmpty()){
			current = stack.pop();
			tilt += calculateTilt(current);
			if(current.left != null) stack.push(current.left);
			if(current.right != null) stack.push(current.right);
		}
		return tilt;
    }
	
	/**
	 * tool for 563.
	 * @param root
	 * @return
	 */
	public static int calculateTilt(TreeNode root){
		if(root == null){
			return 0;
		}
		int leftVal = (root.left == null) ? 0 : sumOfChildren(root.left);
		int rightVal = (root.right == null) ? 0 : sumOfChildren(root.right);
		return Math.abs(leftVal - rightVal);
	}
	
	/**
	 * tool for 563.
	 * @param root
	 * @return
	 */
	public static int sumOfChildren(TreeNode root){
		if(root == null){
			return 0;
		}
		int sum = 0;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(root);
		TreeNode current = null;
		while(!stack.isEmpty()){
			current = stack.pop();
			sum += current.val;
			if(current.left != null) stack.push(current.left);
			if(current.right != null) stack.push(current.right);
		}
		return sum;
	}
	
	public static void main(String[] args){
		TreeNode T = new TreeNode(1);
		T.left = new TreeNode(2);
//		T.right = new TreeNode(3);
//		T.left.left = new TreeNode(4);
//		T.right.right = new TreeNode(5);
		System.out.println(findTilt3(T));
	}
}
