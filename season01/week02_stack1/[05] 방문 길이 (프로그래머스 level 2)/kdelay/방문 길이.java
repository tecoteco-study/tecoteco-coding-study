import java.util.*;

class Solution {

    private static final Map<Character, int[]> location = new HashMap<>();

    private void initLocation() {
        location.put('U', new int[]{0, 1});
        location.put('D', new int[]{0, -1});
        location.put('L', new int[]{-1, 0});
        location.put('R', new int[]{1, 0});
    }

    private boolean isValidMove(int dx, int dy) {
        return dx >= 0 && dx <= 10 && dy >= 0 && dy <= 10;
    }

    public int solution(String dirs) {

        //방문 노드(중복X)
        Set<String> set = new HashSet<>();

        //초기 위치
        int x = 5, y = 5;
        initLocation();

        for (int i = 0; i < dirs.length(); i++) {
            //방향 및 이동 위치 설정
            int[] loc = location.get(dirs.charAt(i));
            int dx = x + loc[0];
            int dy = y + loc[1];

            //움직일 수 있는지 검증
            if (!isValidMove(dx, dy)) continue;

            //양방향
            set.add(x + " " + y + " " + dx + " " + dy);
            set.add(dx + " " + dy + " " + x + " " + y);

            //이동
            x = dx; y = dy;
        }
        return set.size() / 2;
    }
}