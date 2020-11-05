import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class fenceplan {
    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("fenceplan.in"));
        //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
        //PrintWriter out = new PrintWriter(System.out, true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        int num = Integer.parseInt(k.nextToken());
        int con = Integer.parseInt(k.nextToken());
        ArrayList<point1> cows = new ArrayList<point1>();

        for (int i = 0; i < num; i++) {
            k = new StringTokenizer(f.readLine());
            int xp = Integer.parseInt(k.nextToken());
            int yp = Integer.parseInt(k.nextToken());
            cows.add(new point1(xp, yp, i));
        }
        for (int i = 0; i < con; i++) {
            k = new StringTokenizer(f.readLine());
            int one = Integer.parseInt(k.nextToken());
            int two = Integer.parseInt(k.nextToken());
            point1 now = cows.get(one - 1);
            now.conec().add(cows.get(two - 1).index);
            now = cows.get(two - 1);
            now.conec().add(cows.get(one - 1).index);
        }
        /*for (int i = 0; i < num; i++) {
            System.out.println(cows.get(i).connected);
        }*/
        out.println(bfs(cows, num));
        out.close();

    }

    public static long bfs(ArrayList<point1> cows, int num) {
        ArrayList<Integer> q = new ArrayList<Integer>();
        boolean visited[] = new boolean[num];
        long ret = Integer.MAX_VALUE;

        for (int i = 0; i < num; i++) {
            point1 now = cows.get(i);

            long minx = Integer.MAX_VALUE;
            long miny = Integer.MAX_VALUE;
            long maxx = Integer.MIN_VALUE;
            long maxy = Integer.MIN_VALUE;

            if (visited[i] == false) {
                for (int a : now.connected) {
                    q.add(a);
                }
                visited[i] = true;
                minx = (long)Math.min(minx, cows.get(i).x);
                miny = (long)Math.min(miny, cows.get(i).y);
                maxx = (long)Math.max(maxx, cows.get(i).x);
                maxy = (long)Math.max(maxy, cows.get(i).y);

                while (!q.isEmpty()) {
                    int index = q.remove(0);
                    if (visited[index] == false) {
                        point1 g = cows.get(index);
                        for (int a : g.connected) {
                            if (visited[a] == false)
                                q.add(a);
                        }
                        visited[index]=true;
                    }
                    minx = Math.min(minx, cows.get(index).x);
                    miny = Math.min(miny, cows.get(index).y);
                    maxx = Math.max(maxx, cows.get(index).x);
                    maxy = Math.max(maxy, cows.get(index).y);
                }
                ret = Math.min(ret, (2 * (maxx - minx)) + (2 * (maxy - miny)));
            }
        }
        return ret;
    }
}
class point1{
    long x;
    long y;
    int index;
    ArrayList<Integer> connected = new ArrayList<Integer>();
    public point1(long xc, long yc, int i){
        x=xc;
        y=yc;
        index=i;
    }
    public ArrayList<Integer> conec(){
        return connected;
    }
    public String toString(){
        return x+ " " + y;
    }
}