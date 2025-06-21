import java.util.*;
import java.io.*;

public class Main {
	//걍 중복순열이다.
	static int N, M;
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		sel = new int[M];
		v = new boolean[N];
		
		repeat_permutation(0);
		System.out.println(sb);
	}
	private static void repeat_permutation(int k) {
		if(sel.length == k) {
			for(int val : sel) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			sel[k] = arr[i];
			repeat_permutation(k+1);
		}
	}
}
