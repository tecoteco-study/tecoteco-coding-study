class Solution {
    public int solution(String A, String B) {

        int lenA = A.length();
        char[] chars = B.toCharArray();
        StringBuilder sb = new StringBuilder(B);
        
        for (int i = 0; i < lenA; i++) {
            if (A.equals(sb.toString())) return i;
            sb.insert(lenA, sb.charAt(0));
            sb.delete(0, 1);
        }
        return -1;
    }

    /*
    public int solution(String A, String B) {
        int lenA = A.length();
        char[] chars = B.toCharArray();
        char[] rotate = new char[lenA];
        int answer = 0;
        while (answer < lenA) {
            if (A.equals(String.valueOf(chars))) return answer;
            for (int i = 0; i < lenA; i++) {
                rotate[i] = chars[(i + 1) % lenA];
            }
            answer++;
            chars = Arrays.copyOfRange(rotate, 0, lenA);
        }
        return -1;
    }
    */
}