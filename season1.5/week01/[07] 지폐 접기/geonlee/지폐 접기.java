class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        while(isBig(wallet, bill)) {
            if(bill[0] > bill[1]) {
                bill[0] /=2;
            } else {
                bill[1] /=2;
            }
            answer++;
        }
        return answer;
    }

    public boolean isBig(int[] wallet, int[] bill) {
        int wMax = Math.max(wallet[0], wallet[1]);
        int wMin = Math.min(wallet[0], wallet[1]);
        int bMax = Math.max(bill[0], bill[1]);
        int bMin = Math.min(bill[0], bill[1]);
        return wMax < bMax || wMin < bMin;
    }
}