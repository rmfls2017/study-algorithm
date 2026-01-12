/**
 * LeetCode 2095: Delete the Middle Node of a Linked List
 * 
 * 접근법: Fast & Slow Pointer (One-Pass)
 * 
 * 핵심 아이디어:
 * - fast는 2칸씩, slow는 1칸씩 이동
 * - fast가 끝에 도달하면 slow는 중간에 위치
 * - dummy node를 사용해 slow가 중간의 "이전" 노드에서 멈추도록 함
 * 
 * 시간복잡도: O(n) - 한 번의 순회
 * 공간복잡도: O(1) - 포인터만 사용
 */
public class Solution {
    
    public ListNode deleteMiddle(ListNode head) {
        // Edge case: 노드가 1개면 삭제 후 빈 리스트
        if (head.next == null) {
            return null;
        }
        
        // Dummy node를 사용해 slow가 middle 직전에 위치하도록
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = head;
        
        // fast가 끝에 도달할 때까지 이동
        // - fast != null: fast가 유효한 노드인지 확인
        // - fast.next != null: fast가 2칸 이동할 수 있는지 확인
        while (fast != null && fast.next != null) {
            slow = slow.next;      // 1칸 이동
            fast = fast.next.next; // 2칸 이동
        }
        
        // slow.next가 middle 노드 → 건너뛰기로 삭제
        slow.next = slow.next.next;
        
        return head;
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    
    ListNode() {}
    
    ListNode(int val) {
        this.val = val;
    }
    
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}