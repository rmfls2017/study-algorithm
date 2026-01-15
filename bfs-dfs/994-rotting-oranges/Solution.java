import java.util.*;

/**
 * LeetCode 994: Rotting Oranges
 * 
 * 접근법: 다중 시작점 BFS (Multi-source BFS)
 * 
 * 핵심 아이디어:
 * - 모든 썩은 오렌지를 시작점으로 Queue에 넣음
 * - BFS로 동시에 퍼져나가며 신선한 오렌지를 썩게 함
 * - size를 이용해 "분 단위"로 처리
 * 
 * 시간복잡도: O(m × n)
 * 공간복잡도: O(m × n)
 */
class Solution {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        
        // 1. 썩은 오렌지는 Queue에, 신선한 오렌지는 개수 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }
        
        // 2. 신선한 오렌지가 없으면 바로 0 반환
        if (freshCount == 0) {
            return 0;
        }
        
        // 3. BFS
        int[] dx = {-1, 1, 0, 0};  // 상, 하, 좌, 우
        int[] dy = {0, 0, -1, 1};
        int minutes = 0;
        
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();  // 현재 분에 처리할 오렌지 개수
            
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                
                // 상하좌우 탐색
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    
                    // 범위 안 && 신선한 오렌지
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m 
                        && grid[nx][ny] == 1) {
                        grid[nx][ny] = 2;  // 썩게 함
                        queue.offer(new int[]{nx, ny});
                        freshCount--;
                    }
                }
            }
            
            minutes++;  // 한 레벨 처리 완료 = 1분 경과
        }
        
        // 4. 결과 반환
        return freshCount == 0 ? minutes : -1;
    }
}
