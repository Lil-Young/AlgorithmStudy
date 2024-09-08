import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N = 10;
	static int arr[][];
	static int[] papers = {0, 5, 5, 5, 5, 5};
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		/*
		 * 1x1 ~ 5x5 색종이는 5개씩 있다.
		 * 1이 적힌 칸은 모두 색종이로 덮여져야 함
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// solving : dfs
		dfs(0); // 색종이를 몇 번 붙였는지
		if(result==Integer.MAX_VALUE) {
			result = -1;
		}
		System.out.println(result);

	}
	static void dfs(int cnt) {
		// basis part
		int sr = -1, sc = -1;
		L:for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j]==1) {
					sr = i;
					sc = j;
					break L;
				}
			}
		}
		if(sr==-1&&sc==-1) {
			// 더 이상 붙일 곳이 없다.
			result = Math.min(result, cnt);
		}
		
		// 색종이를 붙일 크기를 구한다.
		int size = getPaperSize(sr, sc);
		
		for (int i = 1; i < size+1; i++) {
			if(papers[i]>0) {
				for (int r = 0; r < i; r++) {
					for (int c = 0; c < i; c++) {
						arr[sr+r][sc+c] = 0;
					}
				}
				papers[i]--;
				dfs(cnt+1);
				for (int r = 0; r < i; r++) {
					for (int c = 0; c < i; c++) {
						arr[sr+r][sc+c] = 1;
					}
				}
				papers[i]++;
			}
		}
		
	}
	static int getPaperSize(int sr, int sc) {
		int SIZE = 5;
		for (int size = SIZE; size > 0; size--) {
			boolean flag = true;
			L:for (int r= sr; r < sr+size; r++) {
				for (int c = sc; c < sc+size; c++) {
					if(r<0 || r>= N || c<0 || c>=N || arr[r][c]==0) {
						flag = false;
						break L;
					}
				}
			}
			if(flag) {
				return size;
			}
		}
		return -1;
	}
}



//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 1 1 1 1 1 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0