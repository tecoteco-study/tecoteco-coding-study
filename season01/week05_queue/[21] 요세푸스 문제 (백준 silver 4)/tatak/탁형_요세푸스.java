import java.util.ArrayDeque;
import java.util.Queue;

 class Solution {
    Queue<Integer> solution(int N, int K)
    {
        Queue<Integer> requestQueue = new ArrayDeque<>();
        Queue<Integer> responseQueue = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            requestQueue.add(i);
        }
        while(!requestQueue.isEmpty())
        {
            for(int i = 1; i < K; i++){
                requestQueue.add(requestQueue.poll());
            }
            responseQueue.add(requestQueue.poll());
        }
        return responseQueue;
    }
}