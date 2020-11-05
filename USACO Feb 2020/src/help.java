import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class help {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("help.in"));
        //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("help.out")));
        //PrintWriter out = new PrintWriter(System.out, true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        int lines = Integer.parseInt(k.nextToken());

        int shade [] = new int[4*lines];
        tuple arr[] = new tuple[lines];
        for(int i=0; i<lines; i++){
            k = new StringTokenizer(f.readLine());
            int s = Integer.parseInt(k.nextToken());
            int e = Integer.parseInt(k.nextToken());
            shade = fill(shade,2*s,2*e);
            arr[i] = new tuple(s,e);
        }

        int combo [] = new int [lines+1];

        combo[0] = 1;
        int max =0;
        for(int i = 1; i<=lines; i++){
            combo[i] = combo[i-1] * (lines-i+1) / (i);
            //System.out.println(combo[i]*i);
            max += combo[i]*i;
        }

        //System.out.println(max);

        /*for(int i =0; i < lines; i++){
            for(int j=i+1; j<lines; j++){

            }
        }*/
        int segments=0;
        for(int i=0; i< shade.length; i++){
            if(shade[i] ==1){
                while(i< shade.length && shade[i]==1){
                    i++;
                }
                segments++;
            }
        }

        //System.out.println(segments);
        out.println((int)(max - (Math.pow(2,lines-2)*(lines-segments))));

        out.close();

    }
    public static int[] fill(int shade[], int s, int e){
        for(int i = s-1; i<=e-1; i++){
            shade[i]=1;
        }
        return shade;
    }

}
class tuple{
    int start;
    int end;
    public tuple(int s, int e){
        start = s;
        end = e;
    }
}
