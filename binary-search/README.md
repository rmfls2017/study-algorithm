# Binary Search

이분 탐색 문제 풀이 모음

## 핵심 패턴

- **값 탐색**: 정렬된 배열에서 특정 값 찾기
- **조건 탐색**: 조건을 만족하는 최소/최대값 찾기
- **실수 탐색**: 정밀도 기반으로 실수 범위 좁히기

## 언제 Binary Search를 떠올려야 하나?

```
1. 값이 증가하면 결과도 단조 증가 (또는 감소)
2. 특정 조건을 만족하는 경계값을 찾아야 함
3. 범위가 너무 커서 전부 탐색 불가능
```

## 기본 템플릿

```java
// 조건을 만족하는 최솟값 찾기
while (lo < hi) {
    int mid = (lo + hi) / 2;
    if (조건(mid)) {
        hi = mid;
    } else {
        lo = mid + 1;
    }
}

// 실수 범위 탐색 (정밀도 기반)
while (hi - lo > 1e-5) {
    double mid = (lo + hi) / 2;
    if (조건(mid)) {
        hi = mid;
    } else {
        lo = mid;
    }
}
```

## 문제 목록

- [3453. Separate Squares I](./3453-separate-squares-i/) - 실수 범위 Binary Search
