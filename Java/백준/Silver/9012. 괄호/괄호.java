import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		for (int n = 0; n < N; n++) {
			Stack<Character> stack = new Stack<Character>();
			String str = br.readLine().trim();
			String answer = "YES";
			for (int i = 0; i < str.length(); i++) {
				if(str.charAt(i) == '(') {
					stack.add(str.charAt(i));
				}else if(!stack.isEmpty() & str.charAt(i) == ')') {
					stack.pop();
				}else {
					answer = "NO";
				}
			}
			if(!stack.isEmpty()) answer = "NO";
			System.out.println(answer);
		}
		
	}
}
