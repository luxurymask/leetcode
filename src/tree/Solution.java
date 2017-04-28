package tree;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Solution {
	
	/**
	 * member of 543.
	 */
	private int depth = 0;
	
	/**
	 * 543. Diameter of Binary Tree
	 * @author liuxl.
	 * Just like 563, compton_scatter's idea.
	 * @param root
	 * @return
	 */
	public int diameterOfBinaryTree(TreeNode root) {
        postOrderTraverse(root);
        return depth;
    }
	
	/**
	 * tool for 543.
	 * @param root
	 * @return
	 */
	public int postOrderTraverse(TreeNode root){
		if(root == null){
			return 0;
		}
		int leftDepth = postOrderTraverse(root.left);
		int rightDepth = postOrderTraverse(root.right);
		int depthSum = (leftDepth + rightDepth);
		if(depth < depthSum){
			depth = depthSum;
		}
		return (Math.max(leftDepth, rightDepth) + 1);
	}
	
	/**
	 * member of 563.
	 * @author compton_scatter.
	 */
	private static int tilt = 0;
	
	/**
	 * 563. Binary Tree Tilt
	 * @author compton_scatter. 
	 * @param root
	 * @return
	 */
	public static int findTilt3(TreeNode root){
		postTraverse(root);
		return tilt;
	}
	
	/**
	 * tool for 563.
	 * @author compton_scatter.
	 * @param root
	 */
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
	
	/**
	 * members of 501.
	 */
	private static int max = 1;
	private static int current = 0;
	private static int lastAccess;
    private static Set<Integer> resultSet = new HashSet<Integer>();
    
	/**
	 * 501. Find Mode in Binary Search Tree
	 * @param root
	 * @return
	 */
	public static int[] findMode(TreeNode root) {
		if(root == null){
			return new int[0];
		}
		lastAccess = root.val;
        infixTraverse(root);
        if(current > max){
        	resultSet.clear();
        	resultSet.add(lastAccess);
        }else if(current == max){
        	resultSet.add(lastAccess);
        }
        int[] result = new int[resultSet.size()];
        Iterator<Integer> iterator = resultSet.iterator();
        for(int i = 0;i < resultSet.size();i++){
        	result[i] = iterator.next();
        }
        return result;
    }
	
	/**
	 * tool for 501.
	 * @param root
	 */
	public static void infixTraverse(TreeNode root){
		if(root.left != null) infixTraverse(root.left);
		if(root.val == lastAccess){
			current++;
		}else if(current > max){
			resultSet.clear();
			max = current;
			current = 1;
			resultSet.add(lastAccess);
		}else if(current == max){
			resultSet.add(lastAccess);
			current = 1;
		}else{
			current = 1;
		}
		lastAccess = root.val;
		if(root.right != null) infixTraverse(root.right);
	}
	
	public static void main(String[] args){
		TreeNode T = new TreeNode(1);
		T.right = new TreeNode(2);
//		T.right = new TreeNode(3);
//		T.left.left = new TreeNode(4);
//		T.right.right = new TreeNode(5);
		for(int i : findMode(T)){
			System.out.println(i);
		}
	}
}
