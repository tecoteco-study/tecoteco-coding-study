import java.util.*;

class Solution {

    static Map<String, String> relationMap; //다단계 관계
    static Map<String, Integer> money; //이익 금액

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        //초기 값
        relationMap = new HashMap<>();
        money = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            relationMap.put(enroll[i], referral[i]);
            money.put(enroll[i], 0);
        }

        //추천인에게 각각 10%씩 이익 분배
        for (int i = 0; i < seller.length; i++) {
            divisionAmount(seller[i], amount[i] * 100);
        }

        //결과 배열 생성
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = money.get(enroll[i]);
        }
        return answer;
    }

    private void divisionAmount(String name, int amount) {

        //이익 분배
        int tenPercent = amount / 10;
        money.put(name, (money.get(name) + amount - tenPercent));

        //종료 조건: "-" 이거나, 금액이 1원 미만이면 종료
        if (relationMap.get(name).equals("-") || amount < 1) return;

        //최상단까지 재귀 진행
        divisionAmount(relationMap.get(name), tenPercent);
    }
}