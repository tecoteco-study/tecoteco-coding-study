package template.Boj;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<단어> 단어들 = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			단어들.add(new 단어(br.readLine(), i));
		}

		int 가장높은유사도 = -1;
		단어 결과단어1 = null;
		단어 결과단어2 = null;

		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				단어 첫번째단어 = 단어들.get(i);
				단어 두번째단어 = 단어들.get(j);

				int 현재유사도 = get유사도(첫번째단어.value, 두번째단어.value);

				if(현재유사도 > 가장높은유사도) {
					가장높은유사도 = 현재유사도;
					결과단어1 = 첫번째단어;
					결과단어2 = 두번째단어;
				}
				else if(현재유사도 == 가장높은유사도) {
					if(첫번째단어.index < 결과단어1.index || (첫번째단어.index == 결과단어1.index && 두번째단어.index < 결과단어2.index)) {
						결과단어1 = 첫번째단어;
						결과단어2 = 두번째단어;
					}
				}
			}
		}

		System.out.println(결과단어1.value);
		System.out.println(결과단어2.value);
	}

	static class 단어 {
		String value;
		int index;

		public 단어(String value, int index) {
			this.value = value;
			this.index = index;
		}
	}

	static int get유사도(String str1, String str2) {
		int minLen = Math.min(str1.length(), str2.length());
		int 접두사길이 = 0;

		for(int i = 0; i < minLen; i++) {
			if(str1.charAt(i) == str2.charAt(i)) {
				접두사길이++;
			} else {
				break;
			}
		}
		return 접두사길이;
	}

	@Test
	void testCase1() throws IOException {
		String input = "9\nnoon\nis\nlunch\nfor\nmost\nnoone\nwaits\nuntil\ntwo\n";
		String expectedOutput = "noon\nnoone";

		assert runTest(input).equals(expectedOutput) : "테스트 실패!";
	}

	@Test
	void testCase2() throws IOException {
		String input = "6\napple\napplication\nappetite\nappend\nbanana\nband\n";
		String expectedOutput = "apple\napplication";  // 가장 긴 접두사는 "appl"로 두 단어 선택
		assert runTest(input).equals(expectedOutput) : "테스트 실패!";
	}

	@Test
	void testCase3() throws IOException {
		String input = "5\ncar\nbike\nplane\ntrain\nboat\n";
		String expectedOutput = "bike\nboat"; // 최소 길이 접두사, 순서에 따라
		assert runTest(input).equals(expectedOutput) : "테스트 실패!";
	}

	@Test
	void testCase4() throws IOException {
		String input = "4\nabcd\nabc\nab\na\n";
		String expectedOutput = "abcd\nabc";  // 가장 긴 접두사는 "abc"
		assert runTest(input).equals(expectedOutput) : "테스트 실패!";
	}

	@Test
	void testCase6() throws IOException {
		String input = "6\nbc\naa\nab\nba\nbd\nbb\n";
		String expectedOutput = "bc\nba";  // 최대 접두사는 "b", 가장 빠른 순서의 인접 단어 쌍은 "bb"와 "bc"
		assert runTest(input).equals(expectedOutput) : "테스트 실패!";
	}

	private String runTest(String input) throws IOException {
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		Main.main(new String[] {});

		return out.toString().trim();
	}

}


