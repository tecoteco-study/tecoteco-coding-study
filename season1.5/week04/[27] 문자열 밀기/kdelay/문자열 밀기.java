class Solution {
    public int solution(String A, String B) {
        if (A.equals(B)) return 0;

        int len = A.length();
        String rotated = A;

        for (int i = 1; i < len; i++) {
            rotated = rotated.charAt(len - 1) + rotated.substring(0, len - 1);
            if (rotated.equals(B)) return i;
        }
        return -1;
    }

/**
 public int solution(String A, String B) {
     int rotation = 0, len = A.length();
     String[] aList = A.split("");

     for (int i = 0; i < len; i++) {
        if (String.join("", aList).equals(B)) return rotation;

         String last = aList[len - 1];

         for (int j = len - 1; j > 0; j--) aList[j] = aList[j - 1];
         aList[0] = last;
         rotation++;
     }
     return rotation == len ? -1 : rotation;
 }
 **/
}