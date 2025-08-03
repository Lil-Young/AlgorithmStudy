import java.io.*;
import java.util.*;

public class Main {
	static class tree{
		int value, speed;
		tree(int value, int speed){
			this.value=value;
			this.speed=speed;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] arr2 = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		tree[] trees = new tree[N];
		for (int i = 0; i < N; i++) {
			trees[i] = new tree(arr[i], arr2[i]);
		}
		Arrays.sort(trees, (o1, o2) -> o1.speed - o2.speed);
		
		long result = 0;
		for (int i = 0; i < N; i++) {
			result+=trees[i].value+(trees[i].speed*i);
		}
		System.out.println(result);
	}
}
