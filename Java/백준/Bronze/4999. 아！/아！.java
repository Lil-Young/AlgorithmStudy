import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
//        print();
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String a = br.readLine();
    	String b = br.readLine();
    	if(a.length() >= b.length()) {
    		System.out.println("go");
    	}else {
    		System.out.println("no");
    	}
    }
}
