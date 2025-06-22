import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		
		int start=0, end=N-1, count=0;
		while(start<end) {
			int sum = arr[start] + arr[end];
			if(sum == x) {
				count++;
				start++;
				end--;
			}else if(sum < x) {
				start++;
			}else {
				end--;
			}
		}
		System.out.println(count);
	}
}
