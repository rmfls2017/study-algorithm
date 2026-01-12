# Linked List

연결 리스트 문제 풀이 모음

## 핵심 패턴

- **Fast & Slow Pointer**: 속도 차이로 중간/사이클 찾기
- **간격 유지 (Gap)**: 두 포인터 사이 k칸 유지
- **Dummy Node**: Edge case 처리 단순화
- **Reverse**: 연결 방향 뒤집기

## Two Pointer 정리

```
중간 찾기        → 속도 차이 (2:1)
뒤에서 k번째    → 간격 유지 (k칸)
사이클 찾기     → 속도 차이 (결국 만남)
```

## 문제 목록

- [2095. Delete the Middle Node of a Linked List](./2095-delete-middle-node/) - Fast & Slow, Dummy Node