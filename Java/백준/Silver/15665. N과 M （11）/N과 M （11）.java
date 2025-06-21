import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	static Set<String> set;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		sel = new int[M];
		v = new boolean[N];
		set = new LinkedHashSet<>();
		repeat_permutation(0);
		System.out.println(sb);
	}
	
	private static void repeat_permutation(int k) {
		if(sel.length == k) {
//			System.out.println(Arrays.toString(sel));
			StringBuilder temp = new StringBuilder();
			for(int val : sel) {
				temp.append(val).append(" ");
			}
			String s = temp.toString();
			
			if(!set.contains(s)) {
				set.add(s);
				sb.append(s).append('\n');
			}
			return;
		}
		
		for(int i=0; i<arr.length; i++) { 
			sel[k] = arr[i];
			repeat_permutation(k+1);
		}
	}
}
