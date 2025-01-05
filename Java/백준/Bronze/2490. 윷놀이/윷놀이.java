import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ZeroCount;
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ZeroCount = 0;
			for (int j = 0; j < 4; j++) {
				if(Integer.parseInt(st.nextToken()) == 0) {
					ZeroCount++;
				}
			}
			if(ZeroCount == 0) {
				System.out.println("E");
			}else if(ZeroCount == 1) {
				System.out.println("A");
			}else if(ZeroCount == 2) {
				System.out.println("B");
			}else if(ZeroCount == 3) {
				System.out.println("C");
			}else {
				System.out.println("D");
			}
			
		}
		
	}
}
