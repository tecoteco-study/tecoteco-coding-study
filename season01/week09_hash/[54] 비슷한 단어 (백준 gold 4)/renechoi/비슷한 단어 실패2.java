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
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		ArrayList<단어> 단어들 = new ArrayList<>();

		for(int i = 0; i<N;i++){
			단어들.add(new 단어(br.readLine(), i));
		}

		Collections.sort(단어들);

		int 가장높은유사도 = -1;
		HashSet<단어> 대상단어들 = new HashSet<>();

		for (int i = 0; i < N - 1; i++) {
			단어 처음단어 = 단어들.get(i);
			단어 두번째단어 = 단어들.get(i + 1);

			if(처음단어.equals(두번째단어)){
				continue;
			}

			int 인접한단어의유사도 = 처음단어.get유사도(두번째단어);

			if (인접한단어의유사도 > 가장높은유사도) {
				가장높은유사도 = 인접한단어의유사도;
				대상단어들.clear();
				대상단어들.add(처음단어);
				대상단어들.add(두번째단어);
			} else if (인접한단어의유사도 == 가장높은유사도) {
				대상단어들.add(처음단어);
				대상단어들.add(두번째단어);
			}
		}


		List<단어> 대상단어들인덱스기준정렬 = new ArrayList<>(대상단어들);
		대상단어들인덱스기준정렬.sort(Comparator.comparingInt(단어 -> 단어.index));

		System.out.println(대상단어들인덱스기준정렬.get(0).value);
		System.out.println(대상단어들인덱스기준정렬.get(1).value);
	}



	public static class 단어 implements Comparable<단어> {
		String value;
		int index;

		public 단어(String value, int index) {
			this.value = value;
			this.index = index;
		}

		@Override
		public int compareTo(단어 o) {
			return this.value.compareTo(o.value);
		}

		public int get유사도(단어 other){
			int minLen = Math.min(this.value.length(), other.value.length());
			int 접두사길이 = 0;

			for (int i = 0; i < minLen; i++) {
				if (this.value.charAt(i) == other.value.charAt(i)) {
					접두사길이++;
				} else {
					break;
				}
			}
			return 접두사길이;
		}
	}


}


