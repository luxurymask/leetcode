package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {
	
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
	 * member of 563.
	 * @author compton_scatter.
	 */
	private static int tilt = 0;

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
	 * @author compton_scatter. 
	 * @param root
	 * @return
	 */
	public static int findTilt3(TreeNode root){
		postTraverse(root);
		return tilt;
	}
	
	/**
	 * member of 543.
	 */
	private int depth = 0;
	
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
	 * members of 501.
	 */
	private static int max = 1;
	private static int current = 0;
	private static int lastAccess;
    private static Set<Integer> resultSet = new HashSet<Integer>();
    
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
	 * 112. Path Sum
	 * @author  boy27910230.
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == sum) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
	
	/**
	 * tool for 113.
	 * @param list
	 * @return
	 */
	public int sumList(List<Integer> list){
		int sum = 0;
		for(int i : list){
			sum += i;
		}
		return sum;
	}
	
	/**
	 * tool for 113.
	 * @param list
	 * @return
	 */
	public List<Integer> copyList(List<Integer> list){
		List<Integer> copy = new ArrayList<Integer>();
		for(int i : list){
			copy.add(i);
		}
		return copy;
	}
	
	/**
	 * 113. Path Sum II.
	 * rubbish code, but at least it is my own idea.
	 * @param root
	 * @param sum
	 * @return
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		Deque<TreeNode> valStack = new LinkedList<TreeNode>();
		Deque<Integer> levelStack = new LinkedList<Integer>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		if(root == null){
			return result;
		}
		valStack.add(root);
		levelStack.add(1);
		TreeNode current;
		int level = 0;
		while(!valStack.isEmpty()){
			current = valStack.pop();
			level = levelStack.pop();
			if(list.size() >= level){
				list.set(level - 1,  current.val);
				int i = list.size() - 1;
				for(;i >= level;i--){
					list.remove(i);
				}
			}else{
				list.add(current.val);
			}
			level++;
			if(current.right != null){
				valStack.push(current.right);
				levelStack.push(level);
			}
			if(current.left != null){
				valStack.push(current.left);
				levelStack.push(level);
			}
			
			if(current.left == null && current.right == null && sumList(list) == sum){
				result.add(copyList(list));
			}
		}
		return result;
    }
	
	/**
	 * member of 113.
	 * @author xuhua.alex & wdj0xda.
	 */
	private List<List<Integer>> resultList = new ArrayList<List<Integer>>();
	
	/**
	 * tool for 113.
	 * @author xuhua.alex & wdj0xda.
	 * @param root
	 * @param sum
	 * @param stack
	 */
	public void pathSum2(TreeNode root, int sum, List<Integer> list){
		list.add(root.val);
		if(root.left == null && root.right == null && root.val == sum) resultList.add(new ArrayList<Integer>(list));
		if(root.left != null) pathSum2(root.left, sum - root.val, list);
		if(root.right != null) pathSum2(root.right, sum - root.val, list);
		list.remove(list.size() - 1);
	}
	
	/**
	 * 113. Path Sum II.
	 * @author xuhua.alex & wdj0xda.
	 * @param root
	 * @param sum
	 * @return
	 */
	public List<List<Integer>> pathSum2(TreeNode root, int sum) {
		if(root == null) return resultList;
		List<Integer> list = new ArrayList<Integer>();
		pathSum2(root, sum, list);
		return resultList;
	}
	
	public static void main(String[] args){
		TreeNode T = new TreeNode(new Integer[]{7,9,-8,-6,-4,null,null,7,-2,null,null,null,null,null,null,null,null,-6});
		Solution s = new Solution();
		List<List<Integer>> listt = s.pathSum(T, 12);
		for(List<Integer> list : listt){
			for(int i : list){
				System.out.print(i + " ");
			}
		}
	}
}
