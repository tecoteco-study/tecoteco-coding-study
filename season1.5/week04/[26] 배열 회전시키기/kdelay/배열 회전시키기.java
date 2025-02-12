class Solution {
    public int[] solution(int[] numbers, String direction) {
        if ("right".equals(direction)) rotate(numbers, true);
        else if ("left".equals(direction)) rotate(numbers, false);
        return numbers;
    }

    private void rotate(int[] numbers, boolean isRight) {
        int len = numbers.length;

        if (isRight) {
            int last = numbers[len - 1];
            for (int i = len - 1; i > 0; i--) numbers[i] = numbers[i - 1];
            numbers[0] = last;
        } else {
            int first = numbers[0];
            for (int i = 0; i < len - 1; i++) numbers[i] = numbers[i + 1];
            numbers[len - 1] = first;
        }
    }
}