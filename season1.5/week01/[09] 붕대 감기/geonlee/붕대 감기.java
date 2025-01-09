class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        //연속 성공 수
        int num = 0;

        int length = attacks.length - 1;

        int a = 0;
        for(int i = 1; i <= attacks[length][0]; i++) {
            if(i == attacks[a][0]) {
                int damage = attacks[a][1];
                answer = answer - damage;
                num = 0;
                a++;
                if(answer <= 0) return -1;
                continue;
            }

            answer += bandage[1];
            num++;
            if(num == bandage[0]) {
                answer = answer + bandage[2];
                num = 0;
            }

            if(answer > health) answer = health;
        }
        return answer;
    }
}