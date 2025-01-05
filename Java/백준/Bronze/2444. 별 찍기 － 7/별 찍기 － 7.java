import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int space = N-1;
		for (int i = 1; i <= N; i++) {
			System.out.println(" ".repeat(space) + "*".repeat(2*i-1));
			space--;
		}
		space = 1;
		for (int i = N-1; i > 0; i--) {
			System.out.println(" ".repeat(space) + "*".repeat(2*i-1));
			space++;
		}
		
	}
}
