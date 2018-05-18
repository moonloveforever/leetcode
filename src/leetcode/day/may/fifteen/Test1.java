package leetcode.day.may.fifteen;

/**
 * 
 * ���������ǿ���������ʾ�����Ǹ�������λ����������ʽ�洢�����ǵ�ÿ���ڵ�ֻ�洢�������֡���������ӷ���һ���µ����� ����Լ���������� 0
 * ֮�⣬���������ֶ��������㿪ͷ�� ʾ���� ���룺(2 -> 4 -> 3) + (5 -> 6 -> 4) �����7 -> 0 -> 8 ԭ��342 +
 * 465 = 807
 * 
 * @author Administrator
 *
 */
public class Test1 {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		ListNode l1next = new ListNode(4);
		l1next.next = new ListNode(3);
		l1.next = l1next;

		ListNode l2 = new ListNode(5);
		ListNode l2next = new ListNode(6);
		l2next.next = new ListNode(4);
		l2.next = l2next;

		ListNode answer = addTwoNumbers(l1, l2);
		System.out.println(answer.val + "" + answer.next.val + "" + answer.next.next.val);

		ListNode l3 = new ListNode(1);
		l3.next = new ListNode(8);

		ListNode l4 = new ListNode(0);

		answer = addTwoNumbers(l3, l4);
		// ���ѵ����
		answer = addTwoNumbers(new ListNode(5), new ListNode(5));

		System.out.println(answer.val + "" + answer.next.val);
	}

	/**
	 * my grade at 40ms
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode p = head;
		int val1, val2;
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				val1 = l1.val;
				l1 = l1.next;
			} else {
				val1 = 0;
			}
			if (l2 != null) {
				val2 = l2.val;
				l2 = l2.next;
			} else {
				val2 = 0;
			}
			int sum = val1 + val2;
			// ��λ����
			if (p.val >= 10) {
				p.next = new ListNode(sum + 1);
				p.val = p.val - 10;
			} else {
				p.next = new ListNode(sum);
			}
			p = p.next;
		}

		// ����[5],[5]���������
		if (p.val >= 10) {
			p.next = new ListNode(1);
			p.val = p.val - 10;
			p = p.next;
		}

		return head.next;
	}
	
	/**
	 * the best realize at 31ms
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbersBest(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int carry = sum/10;
        ListNode result = new ListNode(sum%10);
        l1 = l1.next;
        l2 = l2.next;
        ListNode cache = result;
        while (l1 != null || l2 != null || carry > 0) {
            sum = carry;
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }
            cache.next = new ListNode(sum%10);
            carry = sum/10;
            cache = cache.next;
            if (l1 !=null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }            
        }
        return result;
    }
}
