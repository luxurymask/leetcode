package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeSolutions {
	//145. Binary Tree Postorder Traversal
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode current = root;
        TreeNode lastAccess = null;
        while(current != null || !stack.isEmpty()){
        	//一路向左。
        	while(current != null){
        		stack.push(current);
        		current = current.left;
        	}
        	current = stack.pop();
        	while(current != null && (current.right == null || current.right == lastAccess)){
        		result.add(current.val);
        		lastAccess = current;
        		if(stack.isEmpty()){
        			return result;
        		}
        		//栈顶元素只能是current的父节点。
        		//如果current是栈顶元素的左孩子，当栈顶元素出栈并赋值给current以后，current需要重新入栈并向右孩子前进一步。
        		//如果current是栈顶元素的右孩子，当栈顶元素出栈并赋值给current以后，current可以被访问并重新接收栈顶元素以验证其父节点的可访问性。
        		current = stack.pop();
        	}
        	stack.push(current);
        	current = current.right;
        }
        return result;
    }
}
