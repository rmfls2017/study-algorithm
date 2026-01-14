# LeetCode 307: Range Sum Query - Mutable

## 문제

정수 배열이 주어지고, 두 가지 연산을 처리:
1. `update(index, val)`: nums[index]를 val로 변경
2. `sumRange(left, right)`: nums[left] ~ nums[right]의 합 반환

```
예시: nums = [1, 3, 5]

sumRange(0, 2) → 1 + 3 + 5 = 9
update(1, 2)   → nums = [1, 2, 5]
sumRange(0, 2) → 1 + 2 + 5 = 8
```

---

## 풀이 과정

### 왜 Segment Tree인가?

단순한 방법들의 한계:

```
방법 1: 단순 반복
- sumRange: O(n) → 느림
- update: O(1)

방법 2: 누적합
- sumRange: O(1)
- update: O(n) → 느림
```

Segment Tree는 둘 다 O(log n)으로 처리 가능

### 구조 설계

```
배열: [1, 3, 5]

          [9]         ← 구간 [0~2]
         /   \
       [4]   [5]      ← 구간 [0~1], [2~2]
       / \
     [1] [3]          ← 개별 원소
```

### 핵심 함수 3개

**1. build**: 트리 생성 (아래 → 위)
- 리프 노드에 원본 값 저장
- 부모 = 왼쪽 자식 + 오른쪽 자식

**2. query**: 구간 합 조회
- 노드 구간이 찾는 구간에 완전 포함 → 값 반환
- 전혀 안 겹침 → 0 반환
- 일부 겹침 → 자식으로 내려가기

**3. update**: 값 수정 (리프 → 루트)
- index가 있는 방향으로만 내려감
- 수정 후 올라오면서 부모 갱신

---

## 코드

→ [Solution.java](./Solution.java)

---

## 복잡도

- 시간
  - 생성: O(n)
  - update: O(log n)
  - sumRange: O(log n)
- 공간: O(n)

---

## 배운 점

1. **설명 이해 ≠ 구현 능력**
   - 듣고 이해하는 것과 직접 코드로 쓰는 건 다른 스킬
   - 직접 손으로 작성해봐야 체득됨

2. **파라미터 구분 중요**
   - `node`: 트리 인덱스
   - `start`, `end`: 노드가 담당하는 구간
   - `index`: 원본 배열 인덱스
   - `left`, `right`: 찾는 구간

3. **재귀 패턴**
   - base case 먼저 (start == end)
   - mid로 구간 분할
   - 자식 처리 후 현재 노드 갱신

---

## 관련 문제

- 303: Range Sum Query - Immutable (누적합)
- 304: Range Sum Query 2D - Immutable
- 308: Range Sum Query 2D - Mutable
