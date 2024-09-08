import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, result;
	static int[][] arr;
	static int[] papers = {0, 5, 5, 5, 5, 5};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 10;
		result = Integer.MAX_VALUE;
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		if(result==Integer.MAX_VALUE) {
			result=-1;
		}
		System.out.println(result);
	}
	static void dfs(int cnt) {
		// basis part
		int sr=-1, sc=-1;
		L:for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j]==1) {
					sr=i;
					sc=j;
					break L;
				}
			}
		}
		if(sr==-1&&sc==-1) {
			result = Math.min(result, cnt);
			return;
		}
		int size = getPaperSize(sr, sc);
		
		for (int i = 1; i < size+1; i++) {
			if(papers[i]>0) {
				for (int r = sr; r < sr+i; r++) {
					for (int c = sc; c < sc+i; c++) {
						arr[r][c]=0;
					}
				}
				papers[i]--;
				dfs(cnt+1);
				for (int r = sr; r < sr+i; r++) {
					for (int c = sc; c < sc+i; c++) {
						arr[r][c]=1;
					}
				}
				papers[i]++;
			}
		}
		
	}
	static int getPaperSize(int sr, int sc) {
		int SIZE = 5;
		for (int size = 5; size > 0; size--) {
			boolean flag=true;
			L:for (int r = sr; r < sr+size; r++) {
				for (int c = sc; c < sc+size; c++) {
					if(r<0 || r>=N || c<0 || c>=N || arr[r][c]==0) {
						flag=false;
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


