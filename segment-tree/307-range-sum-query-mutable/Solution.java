/**
 * LeetCode 307: Range Sum Query - Mutable
 * 
 * 접근법: Segment Tree
 * 
 * 핵심 아이디어:
 * - 배열을 반으로 나눠서 트리로 만듦
 * - 각 노드는 해당 구간의 합을 저장
 * - 조회/수정 모두 O(log n)
 * 
 * 시간복잡도: build O(n), update O(log n), query O(log n)
 * 공간복잡도: O(n)
 */
class NumArray {
    int[] tree;
    int n;
    
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.tree = new int[4 * n];
        build(nums, 1, 0, n - 1);
    }
    
    /**
     * 트리 생성 (재귀)
     * - 리프 노드: 원본 배열 값 저장
     * - 내부 노드: 자식들의 합
     */
    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            this.tree[node] = arr[start];
            return;
        }
        
        int mid = (start + end) / 2;
        build(arr, node * 2, start, mid);
        build(arr, node * 2 + 1, mid + 1, end);
        this.tree[node] = this.tree[node * 2] + this.tree[node * 2 + 1];
    }
    
    /**
     * 값 수정
     * - index가 있는 방향으로만 내려감
     * - 수정 후 올라오면서 부모 갱신
     */
    public void update(int index, int val) {
        update(1, 0, n - 1, index, val);
    }
    
    private void update(int node, int start, int end, int index, int val) {
        if (start == end) {
            this.tree[node] = val;
            return;
        }
        
        int mid = (start + end) / 2;
        if (index <= mid) {
            update(node * 2, start, mid, index, val);
        } else {
            update(node * 2 + 1, mid + 1, end, index, val);
        }
        this.tree[node] = this.tree[node * 2] + this.tree[node * 2 + 1];
    }
    
    /**
     * 구간 합 조회
     * - 완전 포함: 노드 값 반환
     * - 전혀 안 겹침: 0 반환
     * - 일부 겹침: 자식으로 내려가기
     */
    public int sumRange(int left, int right) {
        return query(1, 0, n - 1, left, right);
    }
    
    private int query(int node, int start, int end, int left, int right) {
        // 전혀 안 겹침
        if (end < left || start > right) {
            return 0;
        }
        // 완전히 포함됨
        if (start >= left && end <= right) {
            return this.tree[node];
        }
        // 일부만 겹침
        int mid = (start + end) / 2;
        int leftSum = query(node * 2, start, mid, left, right);
        int rightSum = query(node * 2 + 1, mid + 1, end, left, right);
        return leftSum + rightSum;
    }
}
