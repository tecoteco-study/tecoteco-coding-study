import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MAX_SIZE = 10000;
    static int front = -1, rear = -1;
    static int length = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        int[] data = new int[MAX_SIZE];

        for (int i = 0; i < size; i++) {
            String[] command = br.readLine().split(" ");
            switch (command[0]) {
                case "push":
                    if (!isFull()) {
                        rear++;
                        length++;
                        data[rear] = Integer.parseInt(command[1]);
                    }
                    break;
                case "pop":
                    if (!isEmpty()) {
                        front++;
                        length--;
                        System.out.println(data[front]);
                    } else System.out.println(-1);
                    break;
                case "front":
                    System.out.println(isEmpty() ? -1 : data[front + 1]);
                    break;
                case "back":
                    System.out.println(isEmpty() ? -1 : data[rear]);
                    break;
                case "size":
                    System.out.println(isEmpty() ? 0 : length);
                    break;
                case "empty":
                    System.out.println(isEmpty() ? 1 : 0);
                    break;
            }
        }
    }

    private static boolean isFull() {
        return rear == MAX_SIZE;
    }

    private static boolean isEmpty() {
        return front == rear;
    }
}