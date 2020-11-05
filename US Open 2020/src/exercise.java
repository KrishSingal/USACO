import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class exercise {
    public static void main(String[] args) throws IOException {

        //BufferedReader f = new BufferedReader(new FileReader("haircut.in"));
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));
        PrintWriter out = new PrintWriter(System.out, true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        int num = Integer.parseInt(k.nextToken());
        int m = Integer.parseInt(k.nextToken());
        int arr[] = new int[num];
        int total = (num*(num+1))/2;
        for(int i=2; i<Math.ceil((double)num/2); i++){
            for(int j = num-i; j>i; j--){
                int lcm = i*j/gcd(i,j);
                if(lcm > num){
                    total += lcm;
                }
            }
            System.out.println(total);
        }
        System.out.println(total);


    }
    public static int gcd(int a, int b){
            if (b == 0)
                return a;
            return gcd(b, a % b);
    }

}
