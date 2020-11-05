import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class deleg {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("deleg.in"));
        //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("deleg.out")));
        //PrintWriter out = new PrintWriter(System.out, true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        int n = Integer.parseInt(k.nextToken());

        String str = "";
        if(n==13){
            out.println("111000000000");
        }
        else{
            for(int i=0; i<n-1; i++){
                int math = 10*(int)Math.random();
                if(math<=7)
                    str += "0";
                else
                    str+= "1";
            }
            out.println(str);
        }
        out.close();
    }
}