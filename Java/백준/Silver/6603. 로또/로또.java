import java.util.*;
import java.io.*;

public class Main {
	// 조합
	static int N;
	static int[] arr;
	static int[] sel;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			arr = new int[N];
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			sel = new int[6];
			combination(0, 0);
			System.out.println(sb);
		}
	}
	
	private static void combination(int idx, int k) {
		if(sel.length == k) {
			for(int val : sel) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}
		if(arr.length == idx) return;
		
		sel[k] = arr[idx];
		combination(idx+1, k+1);
		combination(idx+1, k);
	}
}
