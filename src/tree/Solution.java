package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
        if(root == null) return new ArrayList();
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
        List<List<Integer>> resultList = new ArrayList(stack);
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
	
	public static void main(String[] args){
		Solution solution = new Solution();
		TreeNode root = new TreeNode(new Integer[]{3,9,20,null,null,15,7});
		System.out.println(solution.levelOrderBottom(root));
	}
}
