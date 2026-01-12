/**
 * LeetCode 2095: Delete the Middle Node of a Linked List
 * 
 * 접근법: Two-Pass (직관적 해결책)
 * 
 * 핵심 아이디어:
 * - Pass 1: 전체 순회하여 길이 n 계산
 * - Pass 2: n/2 위치의 이전 노드까지 이동
 * - 중간 노드를 건너뛰어 삭제
 * 
 * 장점: 이해하기 쉽고 직관적
 * 단점: 리스트를 두 번 순회해야 함 (약 1.5n 노드 방문)
 * 
 * 시간복잡도: O(n)
 * 공간복잡도: O(1)
 */
public class TwoPassSolution {
    
    public ListNode deleteMiddle(ListNode head) {
        // Edge case: 노드가 1개면 삭제 후 빈 리스트
        if (head.next == null) {
            return null;
        }
        
        // Pass 1: 길이 계산
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        
        // Pass 2: 중간 노드의 이전까지 이동
        // 중간 인덱스 = length / 2 (0-based)
        // 이전 노드 인덱스 = (length / 2) - 1
        int prevIndex = (length / 2) - 1;
        curr = head;
        for (int i = 0; i < prevIndex; i++) {
            curr = curr.next;
        }
        
        // 중간 노드 삭제 (건너뛰기)
        curr.next = curr.next.next;
        
        return head;
    }
}

/**
 * Definition for singly-linked list.
 * (Solution.java에 정의된 ListNode 클래스 사용)
 */