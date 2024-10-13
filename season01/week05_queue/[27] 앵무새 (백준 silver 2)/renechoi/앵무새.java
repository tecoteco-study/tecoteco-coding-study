package template.Boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int 앵무새개수 = Integer.parseInt(br.readLine());

		ArrayList<String> 앵무새가말한문장들 = new ArrayList<>();
		while (앵무새개수-->0){
			앵무새가말한문장들.add(br.readLine());
		}

		String 검증해야하는문장 = br.readLine();

		System.out.println(가능여부판단하기(앵무새가말한문장들, 검증해야하는문장));
	}



	// private static String 가능여부판단하기(ArrayList<String> 앵무새가말한문장들, String 검증해야하는문장) {
	//
	//      ArrayDeque<String>[] 앵무새별큐 = new ArrayDeque[앵무새가말한문장들.size()];
	//      for(int i = 0; i<앵무새별큐.length; i++){
	//          앵무새별큐[i]=new ArrayDeque<>();
	//      }
	//
	//     for (int i = 0; i < 앵무새가말한문장들.size(); i++ ){
	//         StringTokenizer stringTokenizer = new StringTokenizer(앵무새가말한문장들.get(i));
	//
	//         while(stringTokenizer.hasMoreTokens()){
	//             앵무새별큐[i].add(stringTokenizer.nextToken());
	//         }
	//     }
	//
	//     StringTokenizer stringTokenizer = new StringTokenizer(검증해야하는문장);
	//
	//     while (stringTokenizer.hasMoreTokens()) {
	//         String 단어 = stringTokenizer.nextToken();
	//
	//         boolean 단어match = false;
	//         for (int i = 0; i < 앵무새별큐.length; i++) {
	//             if (단어.equals(앵무새별큐[i].peek())) {
	//                 앵무새별큐[i].pop();
	//                 단어match = true;
	//                 break;
	//             }
	//         }
	//
	//         if (!단어match) {
	//             return "Impossible";
	//         }
	//     }
	//
	//     // 모든 앵무새의 큐가 비었는지 확인
	//     for (ArrayDeque<String> 큐 : 앵무새별큐) {
	//         if (!큐.isEmpty()) {
	//             return "Impossible";
	//         }
	//     }
	//
	//
	//     return "Possible";
	// }

	private static String 가능여부판단하기(ArrayList<String> 앵무새가말한문장들, String 검증해야하는문장) {

		int N = 앵무새가말한문장들.size();

		List<String>[] 앵무새문장 = new List[N];
		int[] 앵무새인덱스 = new int[N];

		for (int i = 0; i < N; i++) {
			String[] words = 앵무새가말한문장들.get(i).split("\\s+");
			앵무새문장[i] = new ArrayList<>(Arrays.asList(words));
			앵무새인덱스[i] = 0;
		}

		// 현재 각 앵무새가 말할 수 있는 단어를 해시맵에 저장하기
		Map<String, Integer> 단어별앵무새맵 = new HashMap<>();

		for (int i = 0; i < N; i++) {
			if (앵무새문장[i].size() > 0) {
				String word = 앵무새문장[i].get(0);
				단어별앵무새맵.put(word, i);
			}
		}

		StringTokenizer st = new StringTokenizer(검증해야하는문장);

		while (st.hasMoreTokens()) {
			String 단어 = st.nextToken();

			if (단어별앵무새맵.containsKey(단어)) {
				int 앵무새번호 = 단어별앵무새맵.get(단어);

				앵무새인덱스[앵무새번호]++;

				단어별앵무새맵.remove(단어);

				// 앵무새가 말할 다음 단어가 있다면 해시맵에 추가
				if (앵무새인덱스[앵무새번호] < 앵무새문장[앵무새번호].size()) {
					String 다음단어 = 앵무새문장[앵무새번호].get(앵무새인덱스[앵무새번호]);
					단어별앵무새맵.put(다음단어, 앵무새번호);
				}
			} else {
				return "Impossible";
			}
		}

		for (int i = 0; i < N; i++) {
			if (앵무새인덱스[i] != 앵무새문장[i].size()) {
				return "Impossible";
			}
		}

		return "Possible";
	}



	@Test
	public void testEdgeCases() {
		// 예제 입력 1: 가능한 경우
		ArrayList<String> 앵무새문장1 = new ArrayList<>();
		앵무새문장1.add("i want to see you");
		앵무새문장1.add("next week");
		앵무새문장1.add("good luck");
		String 검증문장1 = "i want next good luck week to see you";
		Assertions.assertEquals("Possible", 가능여부판단하기(앵무새문장1, 검증문장1));

		// 예제 입력 2: 불가능한 경우
		ArrayList<String> 앵무새문장2 = new ArrayList<>();
		앵무새문장2.add("i found");
		앵무새문장2.add("an interesting cave");
		String 검증문장2 = "i found an cave interesting";
		Assertions.assertEquals("Impossible", 가능여부판단하기(앵무새문장2, 검증문장2));

		// 예제 입력 3: 불가능한 경우
		ArrayList<String> 앵무새문장3 = new ArrayList<>();
		앵무새문장3.add("please");
		앵무새문장3.add("be careful");
		String 검증문장3 = "pen pineapple apple pen";
		Assertions.assertEquals("Impossible", 가능여부판단하기(앵무새문장3, 검증문장3));

		// 추가 테스트 케이스 1: 가능한 경우
		ArrayList<String> 앵무새문장4 = new ArrayList<>();
		앵무새문장4.add("hello world");
		앵무새문장4.add("java programming");
		String 검증문장4 = "hello java world programming";
		Assertions.assertEquals("Possible", 가능여부판단하기(앵무새문장4, 검증문장4));

		// 추가 테스트 케이스 2: 불가능한 경우 (순서가 다름)
		ArrayList<String> 앵무새문장5 = new ArrayList<>();
		앵무새문장5.add("start");
		앵무새문장5.add("coding now");
		String 검증문장5 = "start now coding";
		Assertions.assertEquals("Impossible", 가능여부판단하기(앵무새문장5, 검증문장5));

		// 추가 테스트 케이스 3: 가능한 경우 (단어가 정확히 일치)
		ArrayList<String> 앵무새문장6 = new ArrayList<>();
		앵무새문장6.add("go");
		앵무새문장6.add("to the");
		앵무새문장6.add("store");
		String 검증문장6 = "go to the store";
		Assertions.assertEquals("Possible", 가능여부판단하기(앵무새문장6, 검증문장6));

		// 추가 테스트 케이스 4: 불가능한 경우 (단어가 하나 누락)
		ArrayList<String> 앵무새문장7 = new ArrayList<>();
		앵무새문장7.add("find");
		앵무새문장7.add("the missing link");
		String 검증문장7 = "find missing link";
		Assertions.assertEquals("Impossible", 가능여부판단하기(앵무새문장7, 검증문장7));

		// 추가 테스트 케이스 5: 불가능한 경우 (앵무새가 말한 단어를 모두 사용하지 않음)
		ArrayList<String> 앵무새문장8 = new ArrayList<>();
		앵무새문장8.add("hello world this is");
		앵무새문장8.add("a test");
		String 검증문장8 = "hello world a test";
		Assertions.assertEquals("Impossible", 가능여부판단하기(앵무새문장8, 검증문장8));
	}


}