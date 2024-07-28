import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	int find = Integer.parseInt(st.nextToken());
    	int[][] arr = new int[N][N];
    	int a=0, b=0; // 찾고자 하는 값의 좌표
    	
    	int val = 1;
    	int move = 1;
    	int x = N/2;
    	int y = N/2;
    	while(true) {
    		for (int i = 0; i < move; i++) {
    			if(val==find) {
    				a = x+1;
    				b = y+1;
    			}
				arr[x--][y] = val;
				val++;
			}
    		if(val == N*N+1) {
    			break;
    		}
    		
    		for (int i = 0; i < move; i++) {
    			if(val==find) {
    				a = x+1;
    				b = y+1;
    			}
    			arr[x][y++] = val;
				val++;
			}
    		move++;
    		for (int i = 0; i < move; i++) {
    			if(val==find) {
    				a = x+1;
    				b = y+1;
    			}
    			arr[x++][y] = val;
				val++;
			}
    		
    		for (int i = 0; i < move; i++) {
    			if(val==find) {
    				a = x+1;
    				b = y+1;
    			}
    			arr[x][y--] = val;
				val++;
			}
    		move++;
    		
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j]==find) {
					a=i+1;
					b=j+1;
				}
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
    	sb.append(a+ " " + b);
    	System.out.println(sb.toString());
    }
}