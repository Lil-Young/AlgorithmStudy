import java.util.*;
import java.io.*;

public class Main {
	static int N, power_a, power_b, result;
	static int[] arr;
	static int[] sel;
	static int[][] skills;
	static List<int[]> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		sel = new int[N/2];
		for (int i = 0; i < N; i++) {
			arr[i] = i;
		}
		skills = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				skills[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		list = new ArrayList<int[]>();
		result = Integer.MAX_VALUE;
		combination(0, 0);
		for(int i=0; i<list.size()/2; i++) {
			int[] a = list.get(i);
			boolean[] check = new boolean[N];
			for(int val : a) {
				check[val] = true;
			}
			int idx=0;
			int[] b = new int[a.length];
			for(int j=0; j<check.length; j++) {
				if(!check[j]) {
					b[idx++] = j;
				}
			}
			power_a = 0;
			power_b = 0;
			combination2(a, new int[2], 0, 0, 1);
			combination2(b, new int[2], 0, 0, 2);
			result = Math.min(result, Math.abs(power_a - power_b));
		}
		System.out.println(result);
	}
	private static void combination2(int[] arr, int[] sel, int idx, int k, int team) {
		if(k==sel.length) {
			int r = sel[0];
			int c = sel[1];
			if(team == 1) {
				power_a += skills[r][c];
				power_a += skills[c][r];	
			}else {
				power_b += skills[r][c];
				power_b += skills[c][r];
			}
			return;
		}
		if(idx==arr.length) return;
		
		
		sel[k] = arr[idx];
		combination2(arr, sel, idx+1, k+1, team);
		combination2(arr, sel, idx+1, k, team);
	}
	
	private static void combination(int idx, int k) {
		if(k==sel.length) {
			int[] temp = new int[sel.length];
			for(int i=0; i<sel.length; i++) {
				temp[i] = sel[i];
			}
			list.add(temp);
			return;
		}
		if(idx==arr.length)return;
		
		sel[k] = arr[idx];
		combination(idx+1, k+1);
		combination(idx+1, k);
	}
}
