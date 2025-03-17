import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cli = st.nextToken();
			if(cli.equals("push")) {
				stack.push(Integer.parseInt(st.nextToken()));
			}else if(cli.equals("pop")) {
				if(stack.isEmpty()) {
					System.out.println(-1);
					continue;
				}
				System.out.println(stack.pop());
			}else if(cli.equals("size")) {
				System.out.println(stack.size());
			}else if(cli.equals("empty")) {
				if(stack.isEmpty()) {
					System.out.println(1);
					continue;
				}
				System.out.println(0);
			}else if(cli.equals("top")) {
				if(stack.isEmpty()) {
					System.out.println(-1);
					continue;
				}
				System.out.println(stack.peek());
			}
		}
		
	}
}
