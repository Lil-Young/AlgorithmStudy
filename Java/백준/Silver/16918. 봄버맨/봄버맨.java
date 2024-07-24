import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
    	/*
    	 * 1. 가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
    	 * 2. 다음 1초 동안 봄버맨은 아무것도 하지 않는다.
    	 * 3. 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
    	 * 4. 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
    	 * 5. 3과 4를 반복한다.
    	 */
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int R = Integer.parseInt(st.nextToken());
    	int C = Integer.parseInt(st.nextToken());
    	int N = Integer.parseInt(st.nextToken());
    	
    	// Queue
    	Queue<int[]> q = new LinkedList<>();
    	
    	// R X C 배열
    	char[][] arr = new char[R][C];
    	char[][] O_arr = new char[R][C];
    	for(int i=0; i<R; i++) {
    		String str = br.readLine();
    		for(int j=0; j<C; j++) {
    			O_arr[i][j] = 'O';
    			
    			arr[i][j] = str.charAt(j);
    			if(arr[i][j]=='O') {
    				int[] a = new int[2];
    				a[0] = i;
    				a[1] = j;
    				q.add(a);
    			}
    		}
    	}
    	int[] d_row = {0, -1, 1, 0, 0};
    	int[] d_col = {0, 0, 0, 1, -1};
    	// N==1 : 초기격자 출력 / N%2==0 : 폭탄 격자 출력 / N%2==3 : 3초 뒤 격자 출력 / N>1 && N%2==1 : 5초 뒤 격자 출력
    	if(N==1) {
    		for(char[] e:arr) {
        		for(char f:e) {
        			System.out.print(f);
        		}
        		System.out.println();
        	}
    	}else if(N%2==0) {
    		for(char[] e:O_arr) {
        		for(char f:e) {
        			System.out.print(f);
        		}
        		System.out.println();
        	}
    	}else if(N==3 || N%4==3) {
    		while(!q.isEmpty()) {
    			int[] q_arr = q.poll();
    			int x = q_arr[0];
    			int y = q_arr[1];
    			
    			for(int k=0; k<d_row.length; k++) {
    				if(x+d_row[k]>=0 && x+d_row[k]<R && y+d_col[k]>=0 && y+d_col[k]<C) {
    					O_arr[x+d_row[k]][y+d_col[k]] = '.';
    				}
        		}
    		}
    		for(char[] e:O_arr) {
        		for(char f:e) {
        			System.out.print(f);
        		}
        		System.out.println();
        	}
    	}else if(N>1 && N%2==1) {
    		// 3초
    		while(!q.isEmpty()) {
    			int[] q_arr = q.poll();
    			int x = q_arr[0];
    			int y = q_arr[1];
    			
    			for(int k=0; k<d_row.length; k++) {
    				if(x+d_row[k]>=0 && x+d_row[k]<R && y+d_col[k]>=0 && y+d_col[k]<C) {
    					O_arr[x+d_row[k]][y+d_col[k]] = '.';
    				}
        		}
    		}
    		// queue에 O_arr에서 'O'인 인덱스 넣기 / arr에 O_arr 넣고, O_arr 전부 'O' 넣기
    		for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(O_arr[i][j]=='O') {
						int[] a = new int[2];
						a[0] = i;
						a[1] = j;
						q.add(a);
					}
					
					arr[i][j] = O_arr[i][j];
					O_arr[i][j] = 'O';
				}
			}
//    		arr ,,,,
    		boolean l = q.isEmpty();
    		// 5초
    		while(!q.isEmpty()) {
    			int[] q_arr = q.poll();
    			int x = q_arr[0];
    			int y = q_arr[1];
    			
    			for(int k=0; k<d_row.length; k++) {
    				if(x+d_row[k]>=0 && x+d_row[k]<R && y+d_col[k]>=0 && y+d_col[k]<C) {
    					O_arr[x+d_row[k]][y+d_col[k]] = '.';
    				}
        		}
    		}
    		if(!l) {
    			for (int i = 0; i < R; i++) {
        			for (int j = 0; j < C; j++) {
        				arr[i][j] = O_arr[i][j];
        				O_arr[i][j] = 'O';
        			}
       			}
    		}
    		for(char[] e:arr) {
        		for(char f:e) {
        			System.out.print(f);
        		}
        		System.out.println();
        	}
    	}
    }
}
//
//6 7 5
//OOOOOOO
//OOOOOOO
//OOOOOOO
//OOOO.OO
//OOOOOOO
//OOOOOOO
//
//.......
//.......
//.......
//.......
//.......
//.......
