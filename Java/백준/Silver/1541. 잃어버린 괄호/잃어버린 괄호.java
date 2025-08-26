import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String input = br.readLine();
        
        String[] sub = input.split("-");
        int result = 0;

        String[] firstNumbers = sub[0].split("\\+");
        for(String num : firstNumbers){
            result += Integer.parseInt(num);
        }

        for(int i=1; i<sub.length; i++){
            String[] numbers = sub[i].split("\\+");
            for(String num : numbers){
                result -= Integer.parseInt(num);
            }
        }

        System.out.println(result);
    }
}
