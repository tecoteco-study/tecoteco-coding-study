package template.Boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("fundamentals/src/test/java/template/Boj/input.txt"));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer 라인정보 = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(라인정보.nextToken());
		int K = Integer.parseInt(라인정보.nextToken());

		int[][] students = new int[6][2];  // 0 베이스 -> students[학년-1][성별] -> 0: 여학생, 1: 남학생

		while (N-->0){
			라인정보 = new StringTokenizer(bufferedReader.readLine());
			int 성별 = Integer.parseInt(라인정보.nextToken());
			int 학년 = Integer.parseInt(라인정보.nextToken());

			students[학년-1][성별]++;
		}

		int 필요한방의수 = 0;

		for (int i =0; i<6; i++){
			if (여학생방이비어있지않다면(students[i])){
				필요한방의수 += 올림계산을이용한여학생방의수구하기(K, students[i]);
			}

			if (남학생방이비어있지않다면(students[i])) {
				필요한방의수 += 올림계산을이용한남학생방의수구하기(K, students[i]);
			}
		}

		System.out.println(필요한방의수);
	}



	private static int 올림계산을이용한여학생방의수구하기(int K, int[] students) {
		return (i학년의여학생수(students) + K - 1 ) / K;
	}

	private static int 올림계산을이용한남학생방의수구하기(int K, int[] students) {
		return (i학년의남학생수(students) + K - 1 ) / K;
	}

	private static int i학년의여학생수(int[] students) {
		return students[0];
	}

	private static int i학년의남학생수(int[] students) {
		return students[1];
	}

	private static boolean 여학생방이비어있지않다면(int[] students) {
		return i학년의여학생수(students) > 0;
	}

	private static boolean 남학생방이비어있지않다면(int[] students) {
		return i학년의남학생수(students) > 0;
	}

}