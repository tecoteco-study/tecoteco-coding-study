import java.util.HashMap;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 1. (string) mm:ss -> (int) mm * 60 + ss 변경
        int total = convertMmSsTo60s(video_len);
        int current = convertMmSsTo60s(pos);
        int start = convertMmSsTo60s(op_start);
        int end = convertMmSsTo60s(op_end);
        
        // 2. 커맨드맵 생성
        HashMap<String, Integer> commandsMap = new HashMap<>();
        commandsMap.put("next", 10);
        commandsMap.put("prev", -10);
        
        for (String command : commands) {
            // 3. 커맨드마다 오프닝 구간에 속하는지 파악 필요
            if (start <= current && current < end) {
                current = end;
            }
            current = Math.min(total, Math.max(0, current + commandsMap.get(command)));
        }
        
        // 4. 구간 최종 확인
        current = start <= current && current < end ? end : current;
        
        // 5. (int) seconds -> (string) mm:ss 변환
        return convert60sToMmSs(current);
    }
    
    // (string) mm:ss -> (int) seconds 변환
    public int convertMmSsTo60s(String mmSs) {
        String[] mmAndSs = mmSs.split(":");
        return Integer.parseInt(mmAndSs[0]) * 60 + Integer.parseInt(mmAndSs[1]);
    }

    // (int) seconds -> (string) mm:ss 변환
    public String convert60sToMmSs(int seconds) {
        String mm = padLeft(2, seconds / 60);
        String ss = padLeft(2, seconds % 60);
        return mm + ":" + ss;
    }
    
    public String padLeft(int n, int i) {
        return String.format("%0" + n + "d", i);
    }
}