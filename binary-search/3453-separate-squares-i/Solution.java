/**
 * LeetCode 3453: Separate Squares I
 * 
 * 접근법: Binary Search on Real Numbers
 * 
 * 핵심 아이디어:
 * - Y가 증가하면 아래 면적도 단조 증가 → Binary Search 가능
 * - 각 정사각형에서 Y 아래 면적을 계산하고 합산
 * - 합이 총 면적의 절반이 되는 Y를 찾음
 * 
 * 시간복잡도: O(n × log(범위 / 정밀도))
 * 공간복잡도: O(1)
 */
public class Solution {
    
    public double separateSquares(int[][] squares) {
        // 1. 초기화: totalArea, lo, hi 계산
        double totalArea = 0;
        double lo = Double.MAX_VALUE;
        double hi = 0;
        
        for (int[] sq : squares) {
            int y = sq[1];
            int l = sq[2];
            totalArea += (double) l * l;
            lo = Math.min(lo, y);
            hi = Math.max(hi, y + l);
        }
        
        double target = totalArea / 2.0;
        
        // 2. Binary Search
        while (hi - lo > 1e-5) {
            double mid = (lo + hi) / 2.0;
            double areaBelow = 0;
            
            // 모든 정사각형에 대해 mid 아래 면적 계산
            for (int[] sq : squares) {
                int y = sq[1];
                int l = sq[2];
                
                if (mid <= y) {
                    // 선이 정사각형 아래 → 면적 0
                    areaBelow += 0;
                } else if (mid >= y + l) {
                    // 선이 정사각형 위 → 전체 면적
                    areaBelow += (double) l * l;
                } else {
                    // 선이 정사각형 통과 → 부분 면적
                    areaBelow += (double) l * (mid - y);
                }
            }
            
            // 범위 조정
            if (areaBelow < target) {
                lo = mid;  // 면적 부족 → Y 올림
            } else {
                hi = mid;  // 면적 충분 → Y 내림
            }
        }
        
        // 3. 결과 반환
        return lo;
    }
}
