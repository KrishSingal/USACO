import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class timeline {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("timeline.in"));
        //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));
        //PrintWriter out = new PrintWriter(System.out, true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        int sessions = Integer.parseInt(k.nextToken());
        int days = Integer.parseInt(k.nextToken());
        int memories = Integer.parseInt(k.nextToken());

        k = new StringTokenizer(f.readLine());

        long ret[] = new long[sessions];
        for(int i=0; i<sessions; i++){
            ret[i] = (long)Integer.parseInt(k.nextToken());
        }


        triplet mems[] = new triplet [memories];
        boolean seen[] = new boolean[sessions];
        ArrayList<ArrayList<triplet>> adj = new ArrayList<ArrayList<triplet>>();

        for(int i=0;  i<sessions; i++){
            adj.add(new ArrayList<triplet>());
        }

        for(int i=0; i<memories; i++){
            k = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(k.nextToken());
            int end = Integer.parseInt(k.nextToken());
            int dist = Integer.parseInt(k.nextToken());
            triplet now = new triplet(start-1, end-1, dist, i);
            mems[i] = now;
            seen[end-1] = true;
            adj.get(start-1).add(now);
        }

        //System.out.println(adj);
        ArrayList<Integer> bases = new ArrayList<Integer>();
        for(int i=0; i<sessions; i++){
            if(seen[i] == false){
                bases.add(i);
            }
        }

        for(int i=0; i<bases.size(); i++){
            for(int j=0; j<memories; j++){
                if(mems[j].first== bases.get(i)){
                    ret[mems[j].second]= (long)(mems[j].sep + ret[mems[j].first]);
                }
            }
        }
        //for(int i: ret)
            //System.out.println(i);



        for(int i=0; i<adj.size(); i++){
            ArrayList<Integer> q = new ArrayList<Integer>();
            boolean visited [] = new boolean[memories];
            for(triplet j: adj.get(i)){
                visited[j.index] =true;
                ret[j.second]= (long)Math.max(j.sep + ret[j.first], ret[j.second]);
                q.add(j.second);

            }
            while(!q.isEmpty()){
                int now = q.remove(0);
                for(triplet r: adj.get(now)){
                    long before = ret[r.second];
                    ret[r.second]= (long)Math.max(r.sep + ret[r.first], ret[r.second]);

                    if(before != ret[r.second] && visited[r.index] == false)
                        q.add(r.second);
                }

            }

        }
        for(long i: ret)
            out.println(i);
        out.close();

    }
}

class triplet{
    int first;
    int second;
    int sep;
    int index;

    public triplet(int f, int s, int se, int i){
        first = f;
        second =s;
        sep = se;
        index =i;
    }

}


