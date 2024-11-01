import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int L = Integer.parseInt(br.readLine());
		String 문자열 = br.readLine();

		long result = 0;
		long m = 1234567891;
		long r지수승값 = 1; // r^i 값을 저장

		for (int i = 0; i < L; i++) {
			int a = 문자열.charAt(i) - 'a' + 1;
			result = (result + a * r지수승값) % m;
			r지수승값 = (r지수승값 * 31) % m;
		}

		// 최종 누적된 값에 대해 마지막 모듈러 연산 = 괄호바깥의 모듈러 연산임
		result = result % m;

		System.out.println(result);
	}
}