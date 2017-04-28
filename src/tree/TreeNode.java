package tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){ val = x; }
	
	TreeNode(Integer[] array){
		if(array.length == 0){
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		val = array[0];
		queue.add(this);
		for(int i = 0;i <= array.length / 2 - 1;i = i * 2 + 1){
			for(int j = i;j <= i * 2 && j <= array.length / 2 - 1;j++){
				TreeNode node = queue.poll();
				if(node == null) continue;
				node.left = (array[j * 2 + 1] == null) ? null : new TreeNode(array[j * 2 + 1]);
				if(j == array.length / 2 - 1) break;
				node.right = (array[j * 2 + 2] == null) ? null : new TreeNode(array[j * 2 + 2]);
				queue.add(node.left);
				queue.add(node.right);
			}
		}
	}
	
	//TODO optimize count of null. (make full tree unnecessary.)
}
