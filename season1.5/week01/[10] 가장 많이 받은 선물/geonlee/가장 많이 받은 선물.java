import java.util.*;

class Solution {


    public static int solution(String[] friends, String[] gifts) {

        int len = friends.length;
        Map<String, Integer> giftScore = new HashMap<>();
        Map<String, Map<String, Integer>> giftMap = new HashMap<>();

        for(String gift: gifts) {
            String[] child = gift.split(" ");
            String from = child[0];
            String to = child[1];
            int fromScore = giftScore.getOrDefault(from, 0);
            int toScore = giftScore.getOrDefault(to, 0);
            giftScore.put(from, ++fromScore);
            giftScore.put(to, --toScore);


            Map<String, Integer> fromGiftMap = giftMap.getOrDefault(from, new HashMap<>());
            Map<String, Integer> toGiftMap = giftMap.getOrDefault(to, new HashMap<>());
            int fromMapScore = fromGiftMap.getOrDefault(to, 0);
            int toMapScore = toGiftMap.getOrDefault(from, 0);
            fromGiftMap.put(to, ++fromMapScore);
            toGiftMap.put(from, --toMapScore);
            giftMap.put(from, fromGiftMap);
            giftMap.put(to, toGiftMap);
        }
        int answer = 0;

        for(int i = 0; i < len; i++) {
            int giftCount = 0;
            String me = friends[i];
            int myScore = giftScore.getOrDefault(me, 0);
            Map<String, Integer> myGiftMap = giftMap.getOrDefault(me, new HashMap<>());
            for(int j = 0; j < len; j++) {
                if(i == j) continue;
                String friend = friends[j];
                int friendScore = giftScore.getOrDefault(friend, 0);
                int crossScore = myGiftMap.getOrDefault(friend, 0);

                if(crossScore > 0 || (crossScore == 0 && myScore > friendScore))
                    giftCount++;
            }
            answer = Math.max(answer, giftCount);
        }

        return answer;
    }
}