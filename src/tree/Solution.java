package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
	
	private int leftLeavesSum = 0;
	
	public void checkLeft(TreeNode root){
		if(root.left == null && root.right == null){
			leftLeavesSum += root.val;
		}
		if(root.left != null) checkLeft(root.left);
		if(root.right != null) checkRight(root.right);
	}
	
	public void checkRight(TreeNode root){
		if(root.left != null) checkLeft(root.left);
		if(root.right != null) checkRight(root.right);
	}
	
	/**
	 * 404. Sum of Left Leaves
	 * @param root
	 * @return
	 */
	public int sumOfLeftLeaves(TreeNode root){
		if(root == null) return 0;
		if(root.left != null) checkLeft(root.left);
		if(root.right != null) checkRight(root.right);
		return leftLeavesSum;
	}
	
	/**
	 * tool for 257
	 * @author liuxl vimukthi.
	 * @param root
	 * @param path
	 * @param resultList
	 */
	public void binaryTreePaths(TreeNode root, String path, List<String> resultList){
		if(root.left == null && root.right == null) resultList.add(path + root.val);
		if(root.left != null) binaryTreePaths(root.left, path + root.left.val + "->", resultList);
		if(root.right != null) binaryTreePaths(root.right, path + root.right.val + "->", resultList);
	}
	
	/**
	 * 257. Binary Tree Paths
	 * @author vimukthi.
	 * @param root
	 * @return
	 */
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> resultList = new ArrayList<String>();
		if(root != null) binaryTreePaths(root, "", resultList);
		return resultList;
    }
	
	/**
	 * 226. Invert Binary Tree
	 * This problem was inspired by this original tweet by Max Howell: 
	 * Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.
	 * @author liuxl
	 * @param root
	 * @return
	 */
	public TreeNode invertTree(TreeNode root) {
		if(root == null) return null;
		TreeNode temp;
		if(root.left != null || root.right != null){
			temp = root.left;
			root.left = root.right;
			root.right = temp;
		}
		if(root.left != null) invertTree(root.left);
		if(root.right != null) invertTree(root.right);
		return root;
	}
	
	/**
	 * 100. Same Tree
	 * @author micheal.zhou.
	 * 聪明的写法。
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree2(TreeNode p, TreeNode q){
		if(p == null && q == null){
			return true;
		}
		
		if(p == null || q == null){
			return false;
		}
		
		return p.val == q.val && isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);
	}
	
	/**
	 * 100. Same Tree
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null){
            if(q != null) return false;
            return true;
        }
        
        if(q == null){
        	if(p != null) return false;
        }
        
        if(p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }else{
            return false;
        }
    }
	
	/**
	 * Tool for 102.
	 * TLE.
	 * @param queue
	 * @return
	 */
	public boolean containsOnlyNull(Queue<TreeNode> queue){
		Iterator<TreeNode> iterator = queue.iterator();
		while(iterator.hasNext()){
			TreeNode node = iterator.next();
			if(node != null){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 102. Binary Tree Level Order Traversal
	 * TLE.
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> list;
		queue.add(root);
		for(int i = 0;!containsOnlyNull(queue);i++){
			list = new ArrayList<Integer>();
			for(int j = 1;j <= Math.pow(2, i) && !queue.isEmpty();j++){
				TreeNode current = queue.poll();
				if(current != null){
					list.add(current.val);
					queue.add(current.left);
					queue.add(current.right);
				}else{
					queue.add(null);
					queue.add(null);
				}
			}
			if(list.size() != 0) resultList.add(list);
		}
        return resultList;
    }
	
	/**
	 * 102. Binary Tree Level Order Traversal
	 * @author SOY
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrder2(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> list;
		queue.add(root);
		while(!queue.isEmpty()){
			list = new ArrayList<Integer>();
			int levelSize = queue.size();
			for(int j = 0;j < levelSize;j++){
				TreeNode current = queue.poll();
				if(current != null){
					list.add(current.val);
					queue.add(current.left);
					queue.add(current.right);
				}
			}
			if(list.size() != 0) resultList.add(list);
		}
        return resultList;
    }
	
	/**
	 * 515. Find Largest Value in Each Tree Row
	 * similar as 102.
	 * @param root
	 * @return
	 */
	public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> resultList = new ArrayList<Integer>();
        queue.add(root);
        while(!queue.isEmpty()){
        	int rowSize = queue.size();
        	int max = Integer.MIN_VALUE;
        	TreeNode current = null;
        	boolean flag = false;
        	for(int i = 0;i < rowSize;i++){
        		current = queue.poll();
        		if(current != null){
        			flag = true;
        			queue.add(current.left);
        			queue.add(current.right);
        			if(current.val > max){
        				max = current.val;
        			}
        		}
        	}
        	if(flag == true) resultList.add(max);
        }
        return resultList;
    }
	
	/**
	 * 235. Lowest Common Ancestor of a Binary Search Tree
	 * @author FrankBian
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        if(root.val > Math.max(p.val, q.val)) return lowestCommonAncestor(root.left, p, q);
        if(root.val < Math.min(p.val, q.val)) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
	
	/**
	 * tool for 110 isBalancedRubbish.
	 * @param root
	 * @return
	 */
	public int height(TreeNode root){
		if(root == null) return 0;
		return Math.max(height(root.left), height(root.right)) + 1;
	}
	
	/**
	 * 110. Balanced Binary Tree
	 * rubbish, too much revisit.
	 * @param root
	 * @return
	 */
	public boolean isBalancedRubbish(TreeNode root) {
		if(root == null) return true;
        return (Math.abs(height(root.left) - height(root.right)) <= 1) && isBalancedRubbish(root.left) && isBalancedRubbish(root.right);
    }
	
	/**
	 * tool for 110.
	 * @author !liuxl
	 * 思想：求高度的时候带入不平衡信息并且一路传回root，最后在调用处只需判断root而不需要进行重复遍历。
	 * @param root
	 * @return
	 */
	public int dfsHeight(TreeNode root){
		if(root == null) return 0;
		int leftHeight = dfsHeight(root.left);
		if(leftHeight == -1) return -1;
		int rightHeight = dfsHeight(root.right);
		if(rightHeight == -1) return -1;
		if(Math.abs(leftHeight - rightHeight) > 1) return -1;
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	/**
	 * 110. Balanced Binary Tree
	 * @author !liuxl.
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root){
		if(root == null) return true;
		return (dfsHeight(root) != -1);
	}
	
	/**
	 * 101. Symmetric Tree
	 * @author liuxl
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> leftStack = new LinkedList<TreeNode>();
        Deque<TreeNode> rightStack = new LinkedList<TreeNode>();
        if(root == null || (root.left == null && root.right == null)) return true;
        if(root.left == null || root.right == null) return false;
        leftStack.push(root.left);
        rightStack.push(root.right);
        while(!leftStack.isEmpty() && !rightStack.isEmpty()){
        	TreeNode left = leftStack.pop();
        	TreeNode right = rightStack.pop();
        	if(left.val != right.val) return false;
        	if((left.left == null && right.right != null) || (left.right == null && right.left != null) || (left.left != null && right.right == null) || (left.right != null && right.left == null)) return false;
        	if(left.left != null) leftStack.push(left.left);
        	if(right.right != null) rightStack.push(right.right);
        	if(left.right != null) leftStack.push(left.right);
        	if(right.left != null) rightStack.push(right.left);
        }
        if(!leftStack.isEmpty() || !rightStack.isEmpty()) return false;
        return true;
    }
	
	/**
	 * tool for 101.
	 * @author !liuxl
	 * @param left
	 * @param right
	 * @return
	 */
	public boolean helper(TreeNode left, TreeNode right){
		if(left == null || right == null) return left == right;
		if(left.val != right.val) return false;
		return helper(left.left, right.right) && helper(left.right, right.left);
	}
	
	/**
	 * 101. Symmetric Tree
	 * @author !liuxl
	 * @param root
	 * @return
	 */
	public boolean isSymmetric2(TreeNode root){
		if(root == null) return true;
		return helper(root.left, root.right);
	}
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Deque<List<Integer>> stack = new LinkedList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root == null) return new ArrayList<>();
        queue.add(root);
        TreeNode current;
    	List<Integer> list;
        while(!queue.isEmpty()){
        	int layerSize = queue.size();
        	list = new ArrayList<Integer>();
        	for(int i = 0;i < layerSize;i++){
            	current = queue.poll();
            	list.add(current.val);
            	if(current.left != null) queue.add(current.left);
            	if(current.right != null) queue.add(current.right);
        	}
        	stack.push(list);
        }
        List<List<Integer>> resultList = new ArrayList<>(stack);
        return resultList;
    }
	
	/**
	 * 236. Lowest Common Ancestor of a Binary Tree
	 * @author issac3
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
		if(root == null || root == p || root == q) return root;
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		return (left != null && right != null) ? root : ((left == null) ? right : left);
	}
	
	/**
	 * tool for 572.
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean hasSameStructure(TreeNode s, TreeNode t){
		if(s == null && t == null) return true;
		if(s == null || t == null || s.val != t.val) return false;
		return hasSameStructure(s.left, t.left) && hasSameStructure(s.right, t.right);
	}
	
	/**
	 * 572. Subtree of Another Tree
	 * almost same as poketheoffer HasSubTree.
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isSubtree(TreeNode s, TreeNode t) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode current;
        queue.add(s);
        while(!queue.isEmpty()){
        	current = queue.poll();
        	if(current.val == t.val && hasSameStructure(current, t)) return true;
        	if(current.left != null) queue.add(current.left);
        	if(current.right != null) queue.add(current.right);
        }
        return false;
    }
	
	/**
	 * 572. Subtree of Another Tree
	 * 递归实现。
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isSubtreeRecursively(TreeNode s, TreeNode t){
		if(t == null) return true;
		if(s == null) return false;
		if(s.val == t.val && hasSameStructure(s, t)) return true;
		return isSubtreeRecursively(s.left, t) || isSubtreeRecursively(s.right, t);
	}
	
	/**
	 * 513. Find Bottom Left Tree Value
	 * @author liuxl
	 * @param root
	 * @return
	 */
	public int findBottomLeftValue(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		TreeNode current = null;
		while(!queue.isEmpty()){
			current = queue.poll();
			if(current.right != null) queue.add(current.right);
			if(current.left != null) queue.add(current.left);
		}
		return current.val;
    }
	
	/**
	 * 144. Binary Tree Preorder Traversal
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        if(root == null) return resultList;
        stack.push(root);
        TreeNode current;
        while(!stack.isEmpty()){
        	current = stack.pop();
        	resultList.add(current.val);
        	if(current.right != null) stack.push(current.right);
        	if(current.left != null) stack.push(current.left);
        }
        return resultList;
    }
	
	/**
	 * 103. Binary Tree Zigzag Level Order Traversal
	 * @author liuxl
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        List<Integer> list = null;
        int count = 0;
        Deque<Integer> stack = null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root == null) return resultList;
        queue.add(root);
        TreeNode current;
        while(!queue.isEmpty()){
        	int layerSize = queue.size();
        	if(count % 2 == 0){
        		list = new ArrayList<Integer>();
        		for(int i = 0;i < layerSize;i++){
        			current = queue.poll();
        			list.add(current.val);
        			if(current.left != null) queue.add(current.left);
        			if(current.right != null) queue.add(current.right);
        		}
        	}else{
        		stack = new LinkedList<Integer>();
        		for(int i = 0;i < layerSize;i++){
        			current = queue.poll();
        			stack.push(current.val);
        			if(current.left != null) queue.add(current.left);
        			if(current.right != null) queue.add(current.right);
        		}
        		list = new ArrayList<Integer>(stack);
        	}
        	count++;
        	resultList.add(list);
        }
        return resultList;
    }
	
	/**
	 * member of 230.
	 */
	private int count = 0;
	private int result = -1;
	
	/**
	 * tool for 230.
	 * @param root
	 * @param k
	 */
	public void helper(TreeNode root, int k){
		if(root.left != null) helper(root.left, k);
        if(++count == k) {
        	result = root.val;
        }
        if(root.right != null) helper(root.right, k);
	}
	
	/**
	 * 230. Kth Smallest Element in a BST
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return result;
    }
	
	/**
	 * 111. Minimum Depth of Binary Tree
	 * @author liuxl
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        if(root.left == null) return minDepth(root.right) + 1;
        if(root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
	
	public TreeNode findMin(TreeNode root){
		while(root != null && root.left != null) root = root.left;
		return root;
	}
	
	/**
	 * 450. Delete Node in a BST
	 * Binary Search Tree的删除.
	 * @param root
	 * @param key
	 * @return
	 */
	public TreeNode deleteNode(TreeNode root, int key) {
		if(root == null) return root;
		if(key > root.val){
			root.right = deleteNode(root.right, key);
		}else if(key < root.val){
			root.left = deleteNode(root.left, key);
		}else if(root.left != null && root.right != null){
			root.val = findMin(root.right).val;
			root.right = deleteNode(root.right, root.val);
		}else{
			root = (root.left == null) ? root.right : root.left;
		}
		return root;
    }
	
	/**
	 * 582. Kill Process
	 * rubbish way.
	 * @author liuxl
	 * @param pid
	 * @param ppid
	 * @param kill
	 * @return
	 */
	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> ppidPidMap = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        for(int i = 0;i < ppid.size();i++){
        	int ppidNum = ppid.get(i);
        	List<Integer> list = ppidPidMap.getOrDefault(ppidNum, new ArrayList<>());
    		list.add(pid.get(i));
        	ppidPidMap.put(ppidNum, list);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(kill);
        int current;
        while(!queue.isEmpty()){
        	current = queue.poll();
        	resultList.add(current);
        	if(ppidPidMap.containsKey(current)){
        		List<Integer> list = ppidPidMap.get(current);
        		for(int i : list){
            		queue.add(i);
            	}
        	}
        }
        return resultList;
    }

	/**
	 * 582. Kill Process
	 * Genius way.(But TLE when there are too many leaves.)
	 * @author !liuxl
	 * @param pid
	 * @param ppid
	 * @param kill
	 * @return
	 */
	public List<Integer> killProcessRecursively(List<Integer> pid, List<Integer> ppid, int kill) {
		List<Integer> result = new ArrayList<Integer>();
		if(kill == 0) return pid;
		result.add(kill);
		for(int i = 0;i < ppid.size() && ppid.contains(kill);i++){
			if(ppid.get(i) == kill) result.addAll(killProcessRecursively(pid, ppid, pid.get(i)));
		}
		return result;
	}
	
	/**
	 * 98. Validate Binary Search Tree
	 * infixOrderTraverse.
	 * @param root
	 * @return
	 */
	public boolean isValidBSTNoneRecursively(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode current = root;
        TreeNode pre = null;
        while(current != null || !stack.isEmpty()){
        	while(current != null){
        		stack.push(current);
        		current = current.left;
        	}
        	
        	if(!stack.isEmpty()){
            	current = stack.pop();
            	if(pre != null && pre.val >= current.val) return false;
            	pre = current;
            	current = current.right;
        	}
        }
        return true;
    }
	
	/**
	 * tool for 98.
	 * @author !liuxl
	 * @param root
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public boolean isValidBST(TreeNode root, long minValue, long maxValue){
		if(root == null) return true;
		if(root.val <= minValue || root.val >= maxValue) return false;
		return isValidBST(root.left, minValue, root.val) && isValidBST(root.right, root.val, maxValue);
	}
	
	/**
	 * 98. Validate Binary Search Tree
	 * recursive solution.
	 * @author !liuxl
	 * @param root
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}
	
	/**
	 * tool for 108.
	 * @author liuxl
	 * @param nums
	 * @param start
	 * @param end
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
		if(start > end) return null;
        int mid = start + (end - start) / 2;
        int val = nums[mid];
        TreeNode root = new TreeNode(val);
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }
	
	/**
	 * 108. Convert Sorted Array to Binary Search Tree
	 * @author liuxl
	 * @param nums
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if(nums.length == 0) return null;
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }
	
	/**
	 * tool for 508.
	 * rubbish solution!!!!!
	 * @author liuxl
	 * @param root
	 * @return
	 */
	public TreeNode adjustTreeToSumRubbish(TreeNode root){
		if(root == null || (root.left == null && root.right == null)) return root;
		TreeNode left, right;
		root.val += ((((left = adjustTreeToSumRubbish(root.left)) == null) ? 0 : left.val) + (((right = adjustTreeToSumRubbish(root.right)) == null) ? 0 : right.val));
		return root;
	}
	
	/**
	 * 508. Most Frequent Subtree Sum
	 * rubbish solution!!!!!!!!!!!!!!
	 * @author liuxl
	 * @param root
	 * @return
	 */
	public int[] findFrequentTreeSumRubbish(TreeNode root) {
        root = adjustTreeToSumRubbish(root);
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root == null) return new int[0];
        queue.add(root);
        TreeNode current;
        while(!queue.isEmpty()){
        	current = queue.poll();
        	frequencyMap.put(current.val, frequencyMap.getOrDefault(current.val, 0) + 1);
        	if(current.left != null) queue.add(current.left);
        	if(current.right != null) queue.add(current.right);
        }
        List<Integer> list = new ArrayList<>();
        int maxFrequency = 0;
        for(Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()){
        	int sum = entry.getKey();
        	int frequency = entry.getValue();
        	if(frequency == maxFrequency){
        		list.add(sum);
        	}else if(frequency > maxFrequency){
        		list.clear();
        		list.add(sum);
        		maxFrequency = frequency;
        	}
        }
        
        int[] result = new int[list.size()];
        for(int i = 0;i < list.size();i++){
        	result[i] = list.get(i);
        }
        return result;
    }

	public static void main(String[] args){
		Solution solution = new Solution();
		TreeNode root = new TreeNode(new Integer[]{1, null, 2});
		System.out.println(solution.kthSmallest(root, 2));
	}
}
