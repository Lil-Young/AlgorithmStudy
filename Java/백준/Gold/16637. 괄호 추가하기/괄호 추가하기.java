import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main {
	static int N, maxNumber;
	static String str;
	static List<Integer> numbers;
	static List<Character> operators;
	
	public static void main(String[] args) throws Exception{
		/*
		 * 괄호안에는 연산자가 하나만 들어가야됨
		 * 수식이 주어졌을 때, 괄호를 적절히 추가해 만들 수 있는 식의 결과의 최댓값을 구해라
		 * 괄호 개수의 제한은 없다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		maxNumber = Integer.MIN_VALUE; // maxNumber -> 결과
		numbers = new ArrayList<Integer>(); // 숫자 리스트
		operators = new ArrayList<Character>(); // 연산자 리스트
		
		// 숫자와 연산자를 구분해서 리스트에 담는다.
		for (int i = 0; i < str.length(); i++) {
			if(Character.isDigit(str.charAt(i))) {
				numbers.add(str.charAt(i)-'0');
			}else {
				operators.add(str.charAt(i));
			}
		}
		
		// dfs에 시작 인덱스와, 첫번째 숫자를 인자로 전달한다.
		dfs(0, numbers.get(0));
		
		System.out.println(maxNumber);
	}
	
	static void dfs(int idx, int number) {
		// 인덱스가 연산자 리스트의 크기와 같아지면 maxNumber를 갱신하고 return 한다.
		if(idx==operators.size()) {
			maxNumber = Math.max(maxNumber, number);
			return;
		}
		
		// 현재 인덱스에 있는 값을 계산한다
		int temp = calc(operators.get(idx), number, numbers.get(idx+1));
		// 현재 인덱스+1 과 계산한 값을 dfs함수에 인자로 전달한다.
		dfs(idx+1, temp);
		
		// 끝났는데, idx+1이 연산자 리스트의 크기보다 작으면
		if(idx+1 < operators.size()) {
			// idx+1에 있는 값을 계산한다.
			int temp2 = calc(operators.get(idx+1), numbers.get(idx+1), numbers.get(idx+2));
			dfs(idx+2, calc(operators.get(idx), number, temp2));
		}
	}
	
	static int calc(char operator, int num1, int num2) {
		if(operator=='+') {
			return num1+num2;
		}else if(operator=='-') {
			return num1-num2;
		}else if(operator=='*') {
			return num1*num2;
		}
		return -1;
	}
}
