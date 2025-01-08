import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int[] arr = new int[1000000];
		int idx = 1;
		for (int i = 1; i < end+1; i++) {
			for (int j = 0; j < i; j++) {
				arr[idx++] = i;
			}
		}
		int result = 0;
		for (int i = start; i < end+1; i++) {
			result+=arr[i];
		}
		System.out.println(result);
	}
}
