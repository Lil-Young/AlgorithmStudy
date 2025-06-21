import java.util.*;
import java.io.*;

public class Main {
	// 1부터 N개 중에서 M개를 고르는데, 같은 수를 여러번 골라도된다(중복), 비내림차순(순서가 중요하지 않다 -> 조합)
	// 중복조합 인거같습니다.
	static int N, M;
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i=1; i<N+1; i++) {
			arr[i-1] = i;
		}
		sel = new int[M];
		v = new boolean[N];
		
		repeat_combination(0, 0);
	}
	
	private static void repeat_combination(int idx, int k) { 
		if(sel.length == k) {
			for(int val : sel) {
				System.out.print(val + " ");
			}
			System.out.println();
			return;
		}
		if(arr.length == idx) return;
		
		sel[k] = arr[idx];
		repeat_combination(idx, k+1);
		repeat_combination(idx+1, k);
	}
}
