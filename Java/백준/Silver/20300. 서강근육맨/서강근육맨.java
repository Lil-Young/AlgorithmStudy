import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<Long> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Long.parseLong(st.nextToken()));
		}
		Collections.sort(list);

		long max = 0;
		if (N % 2 == 1) {
			max = list.get(N - 1);
			N--;
		}
		for (int i = 0; i < N / 2; i++) {
			max = Math.max(max, list.get(i) + list.get(N - 1 - i));
		}
		System.out.println(max);
	}
}
