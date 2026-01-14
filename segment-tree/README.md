# Segment Tree

구간 쿼리와 값 수정을 빠르게 처리하는 자료구조

## 왜 필요한가?

배열에서 반복적으로 이런 작업을 해야 할 때:
- 구간 [i, j]의 합 구하기
- 특정 인덱스 값 수정하기

| 방법 | 구간 합 조회 | 값 수정 |
|------|-------------|---------|
| 단순 반복 | O(n) | O(1) |
| 누적합 | O(1) | O(n) |
| **Segment Tree** | **O(log n)** | **O(log n)** |

→ 둘 다 빠르게 처리 가능

## 핵심 아이디어

배열을 반으로 나눠서 트리로 만들고, 각 노드가 해당 구간의 합을 저장

```
배열: [3, 7, 2, 5]

               [17]         ← 구간 [0~3]의 합
              /    \
          [10]      [7]     ← 구간 [0~1], [2~3]의 합
          /  \     /   \
        [3]  [7]  [2]  [5]  ← 개별 원소
```

## 트리를 배열로 저장

```
tree = [_, 17, 10, 7, 3, 7, 2, 5]
인덱스:  0   1   2  3  4  5  6  7

규칙:
- 부모 인덱스 i의 왼쪽 자식: 2*i
- 부모 인덱스 i의 오른쪽 자식: 2*i + 1
```

## 기본 연산

### 1. build: 트리 생성

```java
void build(int[] arr, int node, int start, int end) {
    if (start == end) {
        tree[node] = arr[start];
        return;
    }
    int mid = (start + end) / 2;
    build(arr, node * 2, start, mid);
    build(arr, node * 2 + 1, mid + 1, end);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}
```

리프 노드에 원본 값 → 부모로 올라가며 합 계산

### 2. query: 구간 합 조회

```java
int query(int node, int start, int end, int left, int right) {
    // 전혀 안 겹침
    if (end < left || start > right) return 0;
    
    // 완전히 포함됨
    if (left <= start && end <= right) return tree[node];
    
    // 일부만 겹침 → 자식으로 내려가기
    int mid = (start + end) / 2;
    return query(node * 2, start, mid, left, right)
         + query(node * 2 + 1, mid + 1, end, left, right);
}
```

3가지 경우:
- 노드 구간이 찾는 구간과 전혀 안 겹침 → 0 반환
- 노드 구간이 찾는 구간에 완전히 포함 → 노드 값 반환
- 일부만 겹침 → 자식 탐색

### 3. update: 값 수정

```java
void update(int node, int start, int end, int index, int val) {
    if (start == end) {
        tree[node] = val;
        return;
    }
    int mid = (start + end) / 2;
    if (index <= mid) {
        update(node * 2, start, mid, index, val);
    } else {
        update(node * 2 + 1, mid + 1, end, index, val);
    }
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}
```

리프 노드까지 내려가서 수정 → 올라오면서 부모 갱신

## 복잡도

| 연산 | 시간복잡도 | 공간복잡도 |
|------|-----------|-----------|
| build | O(n) | O(n) |
| query | O(log n) | - |
| update | O(log n) | - |

## 문제 목록

- [307. Range Sum Query - Mutable](./307-range-sum-query-mutable/) - Segment Tree 기본

## 응용

Segment Tree는 합 외에도 다양한 연산에 적용 가능:
- 구간 최솟값/최댓값
- 구간 곱
- 구간에서 특정 조건을 만족하는 개수
- Lazy Propagation (구간 업데이트)
