import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0 && !stack.isEmpty()) {
				stack.pop();
			}else {
				stack.add(num);
			}
		}
		if(stack.isEmpty()) {
			System.out.println(0);
		}else {
			int sum = 0;
			int stackSize = stack.size();
			for (int i = 0; i < stackSize; i++) {
				sum+=stack.pop();
			}
			System.out.println(sum);
		}
	}
}