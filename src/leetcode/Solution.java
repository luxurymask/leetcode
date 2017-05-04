package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class Solution {
	public static boolean cups(int x, int y, int z) {
		int a = x >= y ? x : y, b = x <= y ? x : y;
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}

		if (z <= x + y && z >= (x + y) % a) {
			return true;
		} else {
			return false;
		}
	}

	public static int findMinArrowShots(int[][] points) {
		List<int[]> batches = new ArrayList<int[]>();
		if (points.length > 0) {
			batches.add(points[0]);
		}
		while (true) {
			boolean breakFlag = false;
			for (int j = 0; j < points.length; j++) {
				if (breakFlag == true) {
					break;
				}
				for (int i = 0; i < batches.size(); i++) {
					int[] point = batches.get(i);
					if ((point[1] >= points[j][0] && point[1] <= points[j][1])
							|| (point[0] >= points[j][0] && point[0] <= points[j][1])
							|| (points[j][0] >= point[0] && points[j][0] <= point[1])
							|| (points[j][1] >= point[0] && points[j][1] <= point[1])) {
						if (points[j][1] - points[j][0] < point[1] - point[0]) {
							batches.set(i, points[j]);
							breakFlag = true;
						}
						break;
					}
					if (i == batches.size() - 1) {
						batches.add(points[j]);
					}
				}
				if (j == points.length - 1) {
					return batches.size();
				}
			}
		}
	}

	public static int[] countBits(int num) {
		int[] counts = new int[num + 1];
		for (int i = 1; i < num + 1; i++) {
			int count = counts[i - 1];
			int preNum = i - 1;
			while (preNum >= 0) {
				if ((preNum & 1) == 0) {
					counts[i] = count + 1;
					break;
				} else {
					preNum >>>= 1;
					count--;
				}
			}
		}
		return counts;
	}

	// O(n)
	public static int[] countBits2(int num) {
		int[] result = new int[num + 1];
		for (int i = 1; i < num + 1; i++) {
			result[i] = result[i - (int) Math.pow(2, (int) (Math.log(i) / Math.log(2)))] + 1;
		}
		return result;
	}

	public int integerBreak(int n) {
		int[] myBreak = new int[n];
		myBreak[2] = 2;
		myBreak[3] = 3;
		for (int i = 4; i < n; i++) {
			int max = 0;
			for (int j = 2; j <= n / 2; j++) {
				int breakNow = myBreak[j] * myBreak[i - j];
				if (breakNow > max) {
					max = breakNow;
				}
			}
			myBreak[i] = max;
		}
		return myBreak[n];
	}

	public static int numberOfArithmeticSlices(int[] A) {
		int result = 0;
		int[] differenceA = new int[A.length - 1];
		for (int i = 0; i < A.length - 1; i++) {
			differenceA[i] = A[i + 1] - A[i];
		}
		int count = 1;
		for (int i = 0; i < differenceA.length; i++) {
			if (i != differenceA.length - 1 && differenceA[i] == differenceA[i + 1]) {
				count++;
			} else if (count >= 2) {
				result += count * (count - 1) / 2;
				count = 1;
			}
		}
		return result;
	}

	public static int combinationSum4(int[] nums, int target) {
		int[] ways = new int[target + 1];
		ways[0] = 1;
		for (int i = 1; i < ways.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i - nums[j] >= 0) {
					ways[i] += (ways[i - nums[j]]);
				}
			}
		}
		return ways[target];
	}

	public static int lengthOfLIS(int[] nums) {

		int length = 0;
		// 以第i个数为结尾的LIS长度
		int[] lisLength = new int[nums.length];
		if (lisLength.length != 0) {
			length = (lisLength[0] = 1);
		}
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] <= nums[i] && lisLength[j] > lisLength[i] - 1) {
					lisLength[i] = lisLength[j] + 1;
				}
			}
			if (lisLength[i] > length) {
				length = lisLength[i];
			}
		}

		return length;
	}

	public static int minPathSum(int[][] grid) {
		int m = 0, n = 0;
		if (grid.length > 0) {
			m = grid.length;
			if (grid[0].length > 0) {
				n = grid[0].length;
			}
		}
		int[][] pathSum = new int[m + 1][n + 1];
		pathSum[0][0] = grid[0][0];
		for (int i = 1; i < m; i++) {
			pathSum[i][0] = pathSum[i - 1][0] + grid[i][0];
		}

		for (int j = 1; j < n; j++) {
			pathSum[0][j] = pathSum[0][j - 1] + grid[0][j];
		}
		Map<Integer, Integer> m2 = new HashMap<>();

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (pathSum[i - 1][j] > pathSum[i][j - 1]) {
					pathSum[i][j] = grid[i][j] + pathSum[i][j - 1];
				} else {
					pathSum[i][j] = grid[i][j] + pathSum[i - 1][j];
				}
			}
		}
		return pathSum[m - 1][n - 1];
	}

	public static int getSum(int a, int b) {
		int sum = 0;
		int carry = 0;
		for (int i = 0; i < 32; i++) {
			sum |= (((a & 1) ^ (b & 1)) ^ carry);
			if ((((a & 1) & (b & 1)) == 1) || ((carry & (b & 1)) == 1) || ((a & 1) & carry) == 1) {
				carry = 1;
			} else {
				carry = 0;
			}
			int temp = (sum & 1);
			sum >>>= 1;
			sum |= (temp << 31);
			a >>>= 1;
			b >>>= 1;
		}
		return sum;
	}

	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> resultSet = new HashSet<Integer>();
		for (int n : nums1) {
			set1.add(n);
		}

		for (int n : nums2) {
			if (set1.contains(n)) {
				resultSet.add(n);
			}
		}
		int size = resultSet.size();
		int[] result = new int[size];
		int i = 0;
		for (int num : resultSet) {
			result[i++] = num;
		}
		return result;
	}

	public static int majorityElement(int[] nums) {
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length + 1; i++) {
			if (i == nums.length) {
				break;
			}
			int count = 0;
			if (!counts.containsKey(nums[i])) {
				counts.put(nums[i], 1);
			} else {
				count = counts.get(nums[i]);
				counts.put(nums[i], ++count);
			}
			if (counts.get(nums[i]) > (nums.length / 2)) {
				return nums[i];
			}
		}
		return -1;
	}

	public static boolean containsDuplicate(int[] nums) {
		Set<Integer> exists = new HashSet<Integer>();
		for (int num : nums) {
			if (exists.contains(num)) {
				return false;
			} else {
				exists.add(num);
			}
		}
		return true;
	}

	public static int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> num1 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> num2 = new HashMap<Integer, Integer>();
		List<Integer> resultList = new ArrayList<Integer>();
		for (int num : nums1) {
			if (num1.containsKey(num)) {
				num1.put(num, num1.get(num) + 1);
			} else {
				num1.put(num, 1);
			}
		}
		for (int num : nums2) {
			if (num2.containsKey(num)) {
				num2.put(num, num2.get(num) + 1);
			} else {
				num2.put(num, 1);
			}
		}
		for (Map.Entry<Integer, Integer> entry : num1.entrySet()) {
			int num = entry.getKey();
			int count = entry.getValue();
			if (num2.containsKey(num)) {
				for (int i = 0; i < (num2.get(num) < count ? num2.get(num) : count); i++) {
					resultList.add(num);
				}
			}
		}
		int[] result = new int[resultList.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = resultList.get(i);
		}
		return result;
	}

	public static String addStrings(String num1, String num2) {
		int carry = 0;
		StringBuffer sum = new StringBuffer();
		for (int i = num1.length() - 1, j = num2.length() - 1, k = 0; k < Math.max(num1.length(),
				num2.length()); i--, j--, k++) {
			int pNum1 = 0;
			int pNum2 = 0;
			int pSum = 0;
			if (i >= 0) {
				pNum1 = num1.charAt(i) - '0';
			}
			if (j >= 0) {
				pNum2 = num2.charAt(j) - '0';
			}
			pSum = pNum1 + pNum2 + carry;
			if (pSum >= 10) {
				pSum -= 10;
				carry = 1;
			} else {
				carry = 0;
			}
			sum.append(pSum);
		}
		return carry == 0 ? sum.reverse().toString() : sum.append(carry).reverse().toString();
	}

	public static List<Integer> findDisappearedNumbers(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		List<Integer> result = new ArrayList<Integer>();
		for (int i : nums) {
			set.add(i);
		}
		for (int i = 1; i <= nums.length; i++) {
			if (!set.contains(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode result = new ListNode(0);
		ListNode head = result;
		ListNode currentNode;
		while (l1 != null || l2 != null || carry != 0) {
			int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
			carry = sum / 10;
			sum = sum % 10;
			currentNode = new ListNode(sum);
			result.next = currentNode;
			result = currentNode;
			l1 = l1 == null ? l1 : l1.next;
			l2 = l2 == null ? l2 : l2.next;
		}
		return head.next;
	}

	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		int carry = 0;

		while (l1 != null) {
			stack1.push(l1.val);
			l1 = l1.next;
		}

		while (l2 != null) {
			stack2.push(l2.val);
			l2 = l2.next;
		}

		ListNode head = new ListNode(0);
		ListNode currentNode;
		while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
			int sum = (stack1.isEmpty() ? 0 : stack1.pop()) + (stack2.isEmpty() ? 0 : stack2.pop()) + carry;
			carry = sum / 10;
			sum = sum % 10;
			currentNode = new ListNode(sum);
			currentNode.next = head.next;
			head.next = currentNode;
		}
		return head.next;
	}

	public static ListNode oddEvenList(ListNode head) {
		if (head.next == null) {
			return head;
		}

		ListNode odd = head;
		ListNode evenHead = head.next;
		int k = 1;
		ListNode current = evenHead;
		ListNode pre;
		while (current != null) {
			current = evenHead;
			pre = odd;
			for (int i = 0; i < k; i++) {
				if (current == null) {
					break;
				}
				current = current.next;
				pre = pre.next;
			}
			if (current == null) {
				break;
			}
			pre.next = current.next;
			current.next = evenHead;
			odd.next = current;
			odd = current;
			k++;
		}
		return head;
	}

	public static void switchSingleListNode(ListNode a, ListNode b, ListNode c, ListNode d, ListNode e, ListNode f) {
		d.next = b;
		b.next = f;
		a.next = e;
		e.next = (b == d ? b : c);
	}

	public static ListNode oddEvenList2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode p1 = head;
		ListNode evenHead = head.next;
		ListNode p2 = evenHead;
		int count = 1;
		ListNode tail = new ListNode(0);

		while (p2 != null) {
			p1.next = p2.next;
			if (p2.next == null) {
				tail = ((count % 2 != 0) ? p1 : p2);
			}
			p1 = p2;
			p2 = p2.next;
			count++;
		}

		tail.next = evenHead;
		return head;
	}

	public static String reverseWord(String word) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = word.length() - 1; i >= 0; i--) {
			char c = word.charAt(i);
			stringBuilder.append(c);
		}
		return stringBuilder.toString();
	}

	public static String reverseWords(String s) {
		String[] words = s.split(" ");
		if (s.length() == 0 || words.length == 0) {
			return s;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : words) {
			stringBuilder.append(reverseWord(string)).append(" ");
		}
		if (!s.equals("") && s.charAt(s.length() - 1) != ' ') {
			stringBuilder.deleteCharAt(s.length());
		}
		return stringBuilder.toString();
	}

	public static String reverseWords2(String s) {
		Deque<Character> stack = new LinkedList<Character>();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				while (!stack.isEmpty()) {
					stringBuilder.append(stack.pop());
				}
				stringBuilder.append(' ');
			} else {
				stack.push(c);
			}
		}
		while (!stack.isEmpty()) {
			stringBuilder.append(stack.pop());
		}
		return stringBuilder.toString();
	}

	public static ListNode deleteDuplicates(ListNode head) {
		if (head.next == null) {
			return head;
		}

		ListNode pre = head;
		ListNode current = head.next;
		ListNode next = current.next;

		if (pre.val == current.val) {
			return null;
		}

		while (next != null) {
			while (current.val == next.val) {
				current = current.next;
				next = next.next;
				if (next == null) {
					break;
				}
			}
			pre.next = next;
			if (next == null) {
				return head;
			}
			current = current.next;
			next = next.next;
			pre = pre.next;
		}
		return head;
	}

	public static ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null || k == 0) {
			return head;
		}
		ListNode p = head;
		ListNode tail;
		ListNode newHead;
		int length = 1;
		// 计算长度，顺便设置tail。
		while (p.next != null) {
			p = p.next;
			length++;
		}
		tail = p;
		tail.next = head;

		// 查找剩余链表，顺便设置newHead。
		p = head;
		for (int i = 0; i <= length % k; i++) {
			p = p.next;
		}
		newHead = p.next;
		p.next = null;
		tail.next = head;
		return newHead;
	}

	public static ListNode rotateRight2(ListNode head, int k) {
		if (head == null || head.next == null || k == 0) {
			return head;
		}
		ListNode p1 = head;
		ListNode p2 = p1;
		ListNode tail = null;
		ListNode newHead;
		for (int i = 1; i <= k; i++) {
			if (p2.next == null) {
				tail = p2;
				p2.next = head;
			}
			p2 = p2.next;
		}

		while ((tail == null) ? (p2.next == null) : (p2 != tail)) {
			p1 = p1.next;
			p2 = p2.next;
		}
		newHead = p1.next;
		p2.next = head;
		p1.next = null;
		return newHead;
	}

	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummyNode = new ListNode(0);
		dummyNode.next = head;
		ListNode pre = dummyNode;
		ListNode groupHead;
		ListNode groupCurrent;
		ListNode groupPre = null;
		ListNode groupTail = null;
		ListNode current = head;
		Queue<ListNode> queue = new LinkedList<ListNode>();

		while (current != null) {
			for (int i = 0; i < k && current != null; i++) {
				queue.add(current);
				current = current.next;
			}
			int queueSize = queue.size();

			if (queueSize < k) {
				return dummyNode.next;
			}

			for (int i = 0; i < queueSize; i++) {
				if (i == 0) {
					groupTail = queue.poll();
					groupTail.next = current;
					groupPre = groupTail;
				} else if (i == (queueSize - 1)) {
					groupHead = queue.poll();
					groupHead.next = groupPre;
					pre.next = groupHead;
				} else {
					groupCurrent = queue.poll();
					groupCurrent.next = groupPre;
					groupPre = groupCurrent;
				}
			}
			pre = groupTail;
		}
		return dummyNode.next;
	}

	public static ListNode mergeKLists(ListNode[] lists) {
		Map<Integer, ListNode> nodeMap = new TreeMap<Integer, ListNode>();
		for (ListNode head : lists) {
			while (head != null) {
				int key = head.val;
				ListNode p = head;
				if (nodeMap.containsKey(key)) {
					ListNode node = nodeMap.get(key);
					head = head.next;
					p.next = node;
					nodeMap.replace(key, p);
				} else {
					head = head.next;
					p.next = null;
					nodeMap.put(key, p);
				}
			}
		}

		Iterator<Map.Entry<Integer, ListNode>> iterator = nodeMap.entrySet().iterator();
		ListNode node = null;
		if (iterator.hasNext()) {
			node = iterator.next().getValue();
		}
		ListNode head = node;
		while (node != null && node.next != null) {
			node = node.next;
		}
		while (iterator.hasNext()) {
			ListNode next = iterator.next().getValue();
			node.next = next;
			while (next.next != null) {
				next = next.next;
			}
			node = next;
		}
		return head;
	}

	public static ListNode mergeKLists2(ListNode[] lists) {
		Comparator<ListNode> valComparator = new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		};

		Queue<ListNode> priorityQueue = new PriorityQueue<ListNode>(lists.length, valComparator);
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] == null) {
				continue;
			}
			priorityQueue.add(lists[i]);
		}
		if (priorityQueue.isEmpty()) {
			return null;
		}
		ListNode head = priorityQueue.poll();
		ListNode current = head;
		if (current.next != null) {
			current = current.next;
			priorityQueue.add(current);
		}
		ListNode pre = head;
		while (!priorityQueue.isEmpty()) {
			current = priorityQueue.poll();
			pre.next = current;
			pre = current;
			if (current.next != null) {
				current = current.next;
				priorityQueue.add(current);
			}
		}
		return head;
	}

	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pOdd = head;
		ListNode pEven = head.next;
		ListNode newHead = pEven;
		ListNode pre = new ListNode(0);
		while (pEven != null) {
			pOdd.next = pEven.next;
			pEven.next = pOdd;
			pre.next = pEven;
			pre = pOdd;
			pOdd = pOdd.next;
			pEven = (pOdd == null ? null : pOdd.next);
		}
		return newHead;
	}

	public static ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode after = dummy;
		for (int i = 1; i <= n; i++) {
			if (i < m) {
				pre = pre.next;
			}
			after = after.next;
		}
		ListNode current = pre.next;
		while (pre.next != after) {
			pre.next = current.next;
			current.next = after.next;
			after.next = current;
			current = pre.next;
		}
		return dummy.next;
	}

	public static ListNode merge(ListNode head1, ListNode head2) {
		ListNode head = new ListNode(0);
		ListNode p = head;

		while (head1 != null && head2 != null) {
			if (head1.val < head2.val) {
				p.next = head1;
				head1 = head1.next;
			} else {
				p.next = head2;
				head2 = head2.next;
			}
			p = p.next;
		}

		if (head1 != null) {
			p.next = head1;
		}

		if (head2 != null) {
			p.next = head2;
		}

		return head.next;
	}

	/**
	 * LeetCode No.148 链表归并排序，时间复杂度O(nlogn)
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pre = null;
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			pre = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		pre.next = null;

		// sortList(head);
		// sortList(slow); //没用ListNode接收，slow指向的节点5。
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);
		return merge(l1, l2);
	}

	public static ListNode partition(ListNode head, int x) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode less = dummy;
		ListNode pre;
		ListNode current;
		while (less.next != null && less.next.val < x) {
			less = less.next;
		}
		pre = less;
		current = pre.next;
		while (current != null) {
			if (current.val >= x) {
				pre = pre.next;
				current = current.next;
			} else {
				pre.next = current.next;
				current.next = less.next;
				less.next = current;
				less = current;
				current = pre.next;
			}
		}
		return dummy.next;
	}

	public static TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode slow = head;
		ListNode fast = head;
		ListNode pre = null;
		while (fast != null && fast.next != null) {
			pre = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		TreeNode root = new TreeNode(slow.val);
		if (pre != null) {
			pre.next = null;
			root.left = sortedListToBST(head);
			root.right = sortedListToBST(slow.next);
		}

		return root;
	}

	public static ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode p = dummy;
		ListNode pre = head;
		ListNode current = pre.next;
		while (current != null) {
			while (p.next != current) {
				if (p.next.val <= current.val) {
					p = p.next;
				} else {
					pre.next = current.next;
					current.next = p.next;
					p.next = current;
					current = pre.next;
					break;
				}
			}
			if (p.next == current) {
				p = p.next;
				current = current.next;
				pre = pre.next;
			}
		}
		return dummy.next;
	}

	public static void reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		ListNode p = head;
		ListNode pre = null;
		ListNode tail = null;
		while (p != null && p.next != null && p.next.next != null) {
			pre = p.next;
			tail = p.next.next;
			while (tail.next != null) {
				tail = tail.next;
				pre = pre.next;
			}
			pre.next = null;
			tail.next = p.next;
			p.next = tail;
			p = p.next.next;
		}
	}

	public static void reorderList2(ListNode head) {
		if(head == null || head.next == null){
			return;
		}
		//取一半。
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}
		
		//不用断链。
		//倒置后半段
		ListNode upperHead = inverseLinkedList(slow);
		
		//合并
		ListNode p = head;
		ListNode next;
		while(upperHead.next != null){
			next = upperHead.next;
			upperHead.next = p.next;
			p.next = upperHead;
			p = upperHead.next;
			upperHead = next;
		}
	}
	
	public static ListNode inverseLinkedList(ListNode head){
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode p1 = head;
		ListNode p2 = p1.next;
		ListNode p3 = p2.next;
		head.next = null;
		
		while(p3 != null){
			p2.next = p1;
			p1 = p2;
			p2 = p3;
			p3 = p3.next;
		}
		p2.next = p1;
		return p2;
	}
	
	public static ListNode getHead() {
		int[] input = new int[] { 1, 2, 3, 4, 5};
		ListNode dummy = new ListNode(0);
		ListNode p = dummy;
		for (int i = 0; i < input.length; i++) {
			p.next = new ListNode(input[i]);
			p = p.next;
		}
		return dummy.next;
	}
	
	/**
	 * LeetCode No.142
	 * 
	 * my solution is like this: using two pointers, one of them one step at a time. 
	 * another pointer each take two steps. Suppose the first meet at step k,
	 * the length of the Cycle is r. so..2k-k=nr,k=nr.
	 * Now, the distance between the start node of list and the start node of cycle is s. 
	 * the distance between the start of list and the first meeting node is k
	 * (the pointer which wake one step at a time waked k steps).
	 * the distance between the start node of cycle and the first meeting node is m, 
	 * so...s=k-m, s=nr-m=(n-1)r+(r-m),here we takes n = 1..so, 
	 * using one pointer start from the start node of list, 
	 * another pointer start from the first meeting node, 
	 * all of them wake one step at a time, the first time they meeting each other is the start of the cycle.
	 * @param head
	 * @return
	 */
	public static ListNode detectCycle(ListNode head) {
		ListNode fast = head;
        ListNode slow = head;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        if(fast == null || fast.next == null){
        	return null;
        }
        slow = head;
        while(fast != slow){
        	slow = slow.next;
        	fast = fast.next;
        }
        return slow;
    }
	
	public static String multiply(String num1, String num2) {
        long sum = 0;
        for(int i = num2.length() - 1;i >= 0;i--){
            sum = sum + Integer.parseInt("" + num2.charAt(i)) * Long.valueOf(num1).longValue() * (long)Math.pow(10, num2.length() - 1 - i);
        } 
        return sum + "";
    }
	
	public RandomListNode copyRandomList(RandomListNode head) {
		if(head == null) return null;
        RandomListNode newHead = new RandomListNode(head.label);
        RandomListNode p = head, q = newHead;
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        while(p != null){
        	map.put(p, q);
        	q.next = (p.next == null ? null : new RandomListNode(p.next.label));
        	p = p.next;
        	q = q.next;
        }
        
        p = head;
        q = newHead;
        while(p != null){
        	q.random = map.get(p.random);
        	p = p.next;
        	q = q.next;
        }
        return newHead;
    }
	
	public static void main(String[] args) {
		ListNode head = getHead();
		reorderList2(head);
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
