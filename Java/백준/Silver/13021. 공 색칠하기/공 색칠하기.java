import java.util.*;
import java.io.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        boolean[] sel = new boolean[N + 1];
        int count = 0;

        for (int i = M - 1; i >= 0; i--) {
            int L = arr[i][0];
            int R = arr[i][1];
            boolean isTrue = false;

            for (int j = L; j <= R; j++) {
                if (!sel[j]) {
                    sel[j] = true;
                    isTrue = true;
                }
            }

            if (isTrue) count++;
        }

        long result = 1;
        for (int i = 0; i < count; i++) result *= 2;

        System.out.println(result);
    }
}

//3 3
//1 3
//1 1
//2 3

