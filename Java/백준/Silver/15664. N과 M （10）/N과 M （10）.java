import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] sel;
	static StringBuilder sb;
	static Set<String> set;
	
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
		set = new LinkedHashSet<>();
		sb = new StringBuilder();
		repeatCombination(0, 0);
		System.out.println(sb);
	}
	private static void repeatCombination(int idx, int k) {
		if(sel.length == k) {
			StringBuilder temp = new StringBuilder();
			for(int val : sel) {
				temp.append(val).append(" ");
			}
			String s = temp.toString();
			
			if(!set.contains(s)) {
				set.add(s);
				for(int val : sel) {
					sb.append(val).append(' ');
				}
				sb.append('\n');
			}
			return;
		}
		if(arr.length == idx) return;
		
		sel[k] = arr[idx];
		repeatCombination(idx+1, k+1);
		repeatCombination(idx+1, k);
	}
}