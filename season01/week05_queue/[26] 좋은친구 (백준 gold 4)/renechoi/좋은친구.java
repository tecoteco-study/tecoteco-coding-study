
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int 학생수 = Integer.parseInt(stringTokenizer.nextToken());
		int 등수차이 = Integer.parseInt(stringTokenizer.nextToken());

		String[] names = new String[학생수];
		for (int i = 0; i < 학생수; i++) {
			names[i] = br.readLine();
		}

		System.out.println(getGoodFriends(names, 학생수, 등수차이));
	}

	private static long getGoodFriends(String[] names, int 학생수, int 등수차이) {
		ArrayDeque<Integer>[] queues = new ArrayDeque[21];
		for (int i = 2; i <= 20; i++) {
			queues[i] = new ArrayDeque<>();
		}

		long goodFriendCount = 0;

		for (int i = 0; i < 학생수; i++) {
			int nameLength = names[i].length();

			// 큐의 맨 앞에 있는 학생과 등수 차이가 K보다 크면 큐에서 제거
			while (!queues[nameLength].isEmpty() && i - queues[nameLength].peekFirst() > 등수차이) {
				queues[nameLength].pollFirst();
			}

			// 현재 큐의 크기만큼 좋은 친구 쌍이 가능
			goodFriendCount += queues[nameLength].size();

			// 현재 학생의 등수를 큐에 추가
			queues[nameLength].offerLast(i);
		}

		// 좋은 친구 쌍의 수 출력
		return goodFriendCount;
	}
}