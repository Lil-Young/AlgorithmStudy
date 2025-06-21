import java.util.*;
import java.io.*;

public class Main {
	static int N, S, result;
	static int[] arr;
	static boolean[] sel;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
//		System.out.println(Arrays.toString(arr));
		sel = new boolean[arr.length];
		result = 0;
		recursive(0);
		// S가 0이면 공집합은 빼야됨
		if(S == 0) {
			System.out.println(result-1);
		}
		// S가 0이 아니면 공집합은 안빼야됨
		else {
			System.out.println(result);
		}
	}
	private static void recursive(int idx) {
		if(arr.length == idx) {
			int sum = 0;
			for(int i=0; i<arr.length; i++) {
				if(!sel[i]) {
					sum+=arr[i];
				}
			}
			if(sum==S) result++;
			return;
		}

		sel[idx] = true;
		recursive(idx+1);
		sel[idx] = false;
		recursive(idx+1);
	}
}
