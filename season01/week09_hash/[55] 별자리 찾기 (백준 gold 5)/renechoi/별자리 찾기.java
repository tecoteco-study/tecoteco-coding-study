import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int m = Integer.parseInt(br.readLine());

		ArrayList<Point> 찾고자하는별들 = new ArrayList<>();

		while(m-->0){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			찾고자하는별들.add(new Point(x, y));
		}

		int n = Integer.parseInt(br.readLine());
		HashSet<Point> 주어진별들 = new HashSet<>();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			주어진별들.add(new Point(x, y));
		}

		System.out.println(이동해야하는좌표찾기(찾고자하는별들, 주어진별들));


	}

	private static String 이동해야하는좌표찾기(ArrayList<Point> 찾고자하는별들, HashSet<Point> 주어진별들) {
		Point 첫번째별 = 찾고자하는별들.get(0);

		for (Point 사진의별 : 주어진별들) {
			이동벡터 이동벡터 = 첫번째별에대해서이동벡터구하기(첫번째별, 사진의별);

			boolean 모두존재 = true;
			for (Point 별자리별 : 찾고자하는별들) {
				Point 이동한별 = 별자리별.이동(이동벡터);

				if (이동한별이주어진별에없다면(주어진별들, 이동한별)) {
					모두존재 = false;
					break;
				}
			}

			if (모두존재) {
				return 이동벡터.x + " " + 이동벡터.y;
			}
		}

		return "not exist";
	}

	private static boolean 이동한별이주어진별에없다면(HashSet<Point> 주어진별들, Point 이동한별) {
		return !주어진별들.contains(이동한별);
	}

	private static 이동벡터 첫번째별에대해서이동벡터구하기(Point 첫번째별, Point point) {
		return 첫번째별.거리구하기(point);
	}

	public static class 이동벡터{
		int x;
		int y;

		public 이동벡터(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Point{
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public 이동벡터 거리구하기(Point that) {
			return new 이동벡터(that.x - this.x, that.y - this.y);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Point))
				return false;
			Point point = (Point)o;
			return x == point.x && y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		public Point 이동(이동벡터 이동벡터) {
			return new Point(this.x + 이동벡터.x, this.y + 이동벡터.y);
		}
	}

}

