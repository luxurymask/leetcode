package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	public class CatchNode {
		CatchNode before;
		CatchNode after;
		int key;
		int value;
	}

	private CatchNode head;
	private CatchNode tail;
	private int size;
	private int capacity;
	private Map<Integer, CatchNode> keyNodeMap;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		head = new CatchNode();
		tail = new CatchNode();
		head.after = tail;
		tail.before = head;
		size = 0;
		keyNodeMap = new HashMap<Integer, CatchNode>();
	}

	public int get(int key) {
		CatchNode currentNode;
		if (keyNodeMap.containsKey(key)) {
			currentNode = keyNodeMap.get(key);
			currentNode.after.before = currentNode.before;
			currentNode.before.after = currentNode.after;
			currentNode.after = tail;
			currentNode.before = tail.before;
			tail.before.after = currentNode;
			tail.before = currentNode;
			return currentNode.value;
		} else {
			return -1;
		}
	}

	public void put(int key, int value) {
		CatchNode node;
		if(!keyNodeMap.containsKey(key)){
			node = new CatchNode();
			node.key = key;
			node.value = value;
			if (size == capacity) {
				keyNodeMap.remove(head.after.key);
				head = head.after;
			} else {
				size++;
			}
			node.after = tail;
			node.before = tail.before;
			tail.before.after = node;
			tail.before = node;
			keyNodeMap.put(key, node);
		}else{
			node = keyNodeMap.get(key);
			node.value = value;
			node.after.before = node.before;
			node.before.after = node.after;
			node.after = tail;
			node.before = tail.before;
			tail.before.after = node;
			tail.before = node;
		}
	}
	
	public static void main(String[] args){
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		cache.put(3, 3);
		System.out.println(cache.get(2));
		cache.put(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */