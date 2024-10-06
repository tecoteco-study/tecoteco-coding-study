import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {

        //빈도 저장 Map
        Map<Integer, Integer> frequency = new HashMap<>();

        //실패율 저장 Map
        Map<Integer, Double> failure = new HashMap<>();

        //초기화
        for (int i = 1; i <= N; i++) {
            frequency.put(i, 0);
            failure.put(i, 0.0);
        }

        //스테이지 별 빈도
        int users = stages.length;
        for (int i = 0; i < users; i++) {
            if (frequency.containsKey(stages[i])) {
                frequency.put(stages[i], frequency.get(stages[i]) + 1);
            }
        }

        //스테이지 별 실패율
        for (int i = 1; i <= N; i++) {
            if (users > 0) {
                failure.put(i, (frequency.get(i) / (double) users));
                users -= frequency.get(i);
            }
        }

        List<Map.Entry<Integer, Double>> list = new LinkedList<>(failure.entrySet());
        list.sort((o1, o2) -> {
            //실패율 동일한 경우, 스테이지 번호 오름차순 정렬
            if (o1.getValue().compareTo(o2.getValue()) == 0) {
                return o1.getKey() - o2.getKey();
            }
            //빈도율 내림차순 정렬
            return o2.getValue().compareTo(o1.getValue());
        });
        return list.stream().mapToInt(Map.Entry::getKey).toArray();
    }
}