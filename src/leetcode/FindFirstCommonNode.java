package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class FindFirstCommonNode {
	public static ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		Deque<ListNode> stack1 = new LinkedList<ListNode>();
		Deque<ListNode> stack2 = new LinkedList<ListNode>();
 		while(pHead1 != null){
 			stack1.push(pHead1);
 			pHead1 = pHead1.next;
 		}
 		
 		while(pHead2 != null){
 			stack2.push(pHead2);
 			pHead2 = pHead2.next;
 		}
 		
 		ListNode current;
 		ListNode last = null;
 		while(!stack1.isEmpty() && !stack2.isEmpty()){
 			if((current = stack1.pop()) == stack2.pop()){
 				last = current;
 			}else{
 				return last;
 			}
 		}
 		return last;
    }
	
	public static void main(String[] args){
		ListNode pHead1 = new ListNode(1);
		pHead1.next = new ListNode(2);
		pHead1.next.next = new ListNode(3);
		pHead1.next.next.next = new ListNode(6);
		pHead1.next.next.next.next = new ListNode(7);
		
		ListNode pHead2 = new ListNode(4);
		pHead2.next = new ListNode(5);
		pHead2.next.next = pHead1.next.next.next;
		pHead2.next.next.next = pHead1.next.next.next.next;
		
		ListNode current = null;
		System.out.println((current = findFirstCommonNode(pHead1, pHead2)) == null ? "null" : current.val);
	}
}
