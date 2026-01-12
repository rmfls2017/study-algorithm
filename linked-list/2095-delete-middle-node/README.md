# LeetCode 2095: Delete the Middle Node of a Linked List

## 문제

연결 리스트의 head가 주어지면 중간 노드를 삭제하고 head를 반환.

중간 노드 = `n/2` 번째 (0-based, 내림)

```
n = 1, 2, 3, 4, 5 → 중간 인덱스 = 0, 1, 1, 2, 2
```

예시:
```
입력:  1 → 3 → 4 → 7 → 1 → 2 → 6  (n=7, 중간=3)
출력:  1 → 3 → 4 → 1 → 2 → 6
```

---

## 풀이 과정

### 첫 번째 생각

"중간을 찾으려면 길이를 알아야 한다"

```
1. 전체 순회해서 길이 n 구하기
2. n/2 위치까지 다시 이동
3. 삭제
```

→ 동작은 하는데 두 번 순회해야 함 → [TwoPassSolution.java](./TwoPassSolution.java)


### 개선 시도

질문: 길이를 모르는 상태에서 중간을 찾을 수 있을까?

마라톤 비유가 떠올랐다:
- 2배 빠른 사람이 끝에 도달하면
- 나는 절반 지점에 있다

이게 Fast & Slow Pointer:
```
slow: 1칸씩
fast: 2칸씩
→ fast가 끝에 도달하면 slow는 중간
```

시각화:
```
1 → 3 → 4 → 7 → 1 → 2 → 6 → null
S   F                           (시작)
    S       F                   (1회)
        S           F           (2회)
            S               F   (3회, 종료)
            ↑
          중간!
```


### 삭제 문제

slow가 중간을 가리키면 삭제를 어떻게 하지?

Singly Linked List에서 삭제하려면 이전 노드가 필요하다:
```
prev.next = prev.next.next
```

해결: slow가 중간의 한 칸 전에서 멈추게 하면 된다

방법: Dummy Node 사용
```java
ListNode dummy = new ListNode(0, head);
ListNode slow = dummy;  // 한 칸 뒤에서 시작
ListNode fast = head;
```

→ [Solution.java](./Solution.java)


### 복잡도

| 방식 | 시간 | 공간 | 순회 |
|------|------|------|------|
| Two-Pass | O(n) | O(1) | ~1.5n |
| Fast & Slow | O(n) | O(1) | ~0.5n |

---

## 확장: 뒤에서 k번째 삭제

처음 생각: "뒤에서부터 가면 되지 않나?"

문제: Singly Linked List는 next만 있어서 뒤로 못 간다

새로운 접근: 간격 k를 유지하면 된다
```
first가 k칸 먼저 출발 → 함께 이동 → first가 끝나면 second는 끝에서 k칸 전
```

시각화 (k=2):
```
[1] → [2] → [3] → [4] → [5] → null
 S           F                      (간격 2)
       S           F                (함께 이동)
             S           F          (함께 이동)
                   S           F    (종료)
                   ↑
              뒤에서 2번째
```

패턴 비교:
```
중간 찾기      → 속도 차이 (fast가 2칸씩)
뒤에서 k번째  → 간격 유지 (first가 k칸 먼저)
```

---

## 심화: Doubly Linked List

Doubly면 prev가 있어서 양방향 이동 가능.

삭제 로직:
```java
target.prev.next = target.next  // A → B
target.next.prev = target.prev  // B ← A
```

Edge case:
- target이 head면 → target.prev가 null
- target이 tail이면 → target.next가 null

해결 1: 조건부 실행
```java
if (target.prev != null) target.prev.next = target.next;
if (target.next != null) target.next.prev = target.prev;
```

해결 2: Dummy Node
```
[dummyHead] ⇄ [1] ⇄ [2] ⇄ [3] ⇄ [dummyTail]
```
→ 모든 노드가 양쪽에 뭔가 있어서 조건 분기 불필요

---

## 관련 문제

- 876: Middle of the Linked List - Fast & Slow 기초
- 19: Remove Nth Node From End - 간격 k 유지
- 141: Linked List Cycle - 사이클 탐지
- 142: Linked List Cycle II - 사이클 시작점