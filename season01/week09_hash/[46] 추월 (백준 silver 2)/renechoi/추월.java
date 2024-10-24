import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		ArrayDeque<String> 들어간차량들 = new ArrayDeque<>();
		for (int i =0; i<N;i++){
			들어간차량들.add((br.readLine()));
		}

		HashSet<String>  반드시추월한차량해시셋 = new HashSet<>();
		for (int i = 0; i<N;i++){
			String 나온차량 = br.readLine();
			while (반드시추월한차량해시셋.contains(들어간차량들.getFirst())){
				들어간차량들.remove();
			}

			if(나온차량.equals(들어간차량들.getFirst())){
				들어간차량들.remove();
			} else{
				반드시추월한차량해시셋.add(나온차량);
			}
		}
		System.out.println(반드시추월한차량해시셋.size());
	}
}