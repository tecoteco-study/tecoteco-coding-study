class Solution {
    public int solution(int[] numbers, int k) {
        // 등차수열 (a + (n - 1) * d)
        return 1 + (k - 1) * 2 % numbers.length;
    }
}