class Solution {
    public int solution(int[] mats, String[][] park) {
        int n = park.length;
        int m = park[0].length;
        int[][] dp = new int[n][m];
        
        // 계산 편하게 하기 위해 String -> int 변환
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < m; j++) {
                // -1 찾기
                dp[i][j] = park[i][j].equals("-1") ? 1 : 0;
            }
        }
        
        int maxLength = 0;
        for (int i = 1;i < n; i++) {
            for (int j = 1;j < m; j++) {
                if (dp[i][j] > 0) {
                    // 좌, 상, 좌상 확인하고 +1 해주기
                    int min = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    dp[i][j] = min + 1;
                    // maxLength 갱신
                    maxLength = Math.max(maxLength, min + 1);
                }
            }
        }

        int answer = -1;
        for (int mat : mats) {
            // answer < mat <= maxLength 일 경우 answer 갱신
            if (answer < mat && mat <= maxLength) {
                answer = mat;
            }
        }
        return answer;
    }
}