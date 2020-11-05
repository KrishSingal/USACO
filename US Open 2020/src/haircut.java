import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class haircut {
    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("haircut.in"));
        //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));
        //PrintWriter out = new PrintWriter(System.out, true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        int num = Integer.parseInt(k.nextToken());
        int arr[] = new int[num];
        ArrayList<ArrayList<Integer>> pairs = new ArrayList<ArrayList<Integer>>();

        for(int i =0; i<num-1; i++){
            pairs.add(new ArrayList<Integer>());
        }

        ArrayList<Integer> blips = new ArrayList<Integer>();

        k = new StringTokenizer(f.readLine());

        arr[0] = Integer.parseInt(k.nextToken());
        int max = arr[0];

        for (int i = 1; i < num ; i++) {
            arr[i] = Integer.parseInt(k.nextToken());
            if(max > arr[i]){
                blips.add(i);
            }
            else
                max = arr[i];
        }

        /*System.out.println(blips);
        for(int a: arr){
            System.out.print(a);
        }*/
        int total=0;
        for(int i=0; i<num-1; i++){
            for(int j=0; j<blips.size(); j++){
                if(blips.get(j)>i && arr[i]> arr[blips.get(j)]){
                    pairs.get(i).add(blips.get(j));
                    total++;
                }
            }
        }
        //System.out.println(total);
        //System.out.println(pairs);
        ArrayList<tuple> q = new ArrayList<tuple>();
        int count=0;
        ArrayList<Integer> fin = new ArrayList<Integer>();
        for(int i=num-1; i>0; i--){
            for(int j=0; j<arr.length; j++){
                if (arr[j] > i){
                    arr[j]=i;
                    adder(pairs.get(j), j,q);
                }
            }
            /*for(int a: arr){
                System.out.print(a);
            }*/
            //System.out.println(q);
            for(int h=0; h<q.size(); h++){
                if(arr[q.get(h).x]<=arr[q.get(h).y]){
                    count++;
                }
            }
            //System.out.println(count);
            fin.add(count);
            q.clear();
            count=0;
        }
        //System.out.println(fin);

        out.println(0);
        for(int i=num-2; i>-1; i--){
            out.println(total-fin.get(i));
        }
        out.close();

    }
    public static void adder(ArrayList<Integer> arr, int j, ArrayList<tuple> q){
            for(int x=0; x<arr.size(); x++){
                q.add(new tuple(j,arr.get(x)));
            }

        }
}
class tuple{
    int x;
    int y;

    public tuple(int x, int y){
        this.x=x;
        this.y=y;
    }
    public String toString(){
        return x + " " + y;
    }
}
