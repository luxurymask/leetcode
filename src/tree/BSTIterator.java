package tree;

import java.util.Deque;
import java.util.LinkedList;

public class BSTIterator {
	private Deque<TreeNode> stack;
	private TreeNode current;
	private int nextVal;
	
	public BSTIterator(TreeNode root) {
        stack = new LinkedList<TreeNode>();
        current = root;
        while(current != null){
        	stack.push(current);
        	current = current.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || current != null;
    }

    /** @return the next smallest number */
    public int next() {
    	if(!stack.isEmpty()) current = stack.pop();
    	nextVal = current.val;
    	current = current.right;
    	while(current != null){
    		stack.push(current);
    		current = current.left;
    	}
    	return nextVal;
    }
}
