import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class cowjump2 {
    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("cowjump.in"));
        //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));
        //PrintWriter out = new PrintWriter(System.out,true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        //input
        int num = Integer.parseInt(k.nextToken());
        ArrayList<point> x = new ArrayList<point>();
        ArrayList<line> lines = new ArrayList<line>();

        for (int i = 0; i < num; i++) {
            k = new StringTokenizer(f.readLine());
            int xp = Integer.parseInt(k.nextToken());
            int yp = Integer.parseInt(k.nextToken());
            int x2 = Integer.parseInt(k.nextToken());
            int y2 = Integer.parseInt(k.nextToken());

            double slope = (double)(y2-yp)/(x2-xp);
            double intercept = (double) y2-(slope*x2);

            if(x2-xp==0){
                slope=Integer.MAX_VALUE;
            }

            lines.add(new line(xp,yp,x2,y2,slope,intercept,i));
            x.add(new point(xp, yp,slope,intercept,i));
            x.add(new point(x2, y2,slope,intercept,i));

        }

        Collections.sort(x, new sortByx());
        System.out.println(x);

        int pos=0;
        ArrayList<Integer> active = new ArrayList<Integer>();
        ArrayList<Integer> end = new ArrayList<Integer>();

        while(pos<2*num){
            int now= x.get(pos).x;
            if(active.contains(x.get(pos).index)) {
                end.add(x.get(pos).index);
            }
            else{
                active.add(x.get(pos).index);
            }
            pos++;
            while(pos<2*num && x.get(pos).x ==now){
                if(active.contains(x.get(pos).index)) {
                    end.add(x.get(pos).index);
                }
                else{
                    active.add(x.get(pos).index);
                }
                pos++;
            }
            int s = end.size();
            //System.out.print(end); System.out.print(" " + active);
            for(int i=0; i<s;i++){
                intersect(active,end.get(i),x,lines);
            }
            //int g= active.size();
            for(int i=0; i<s;i++){
                for(int j=0; j<active.size(); j++){
                    if(end.get(i)==active.get(j)){
                        active.remove(j);
                    }
                }
            }
            end.removeAll(end);

        }
        int mindex=0;
        int max=-1;
        for(int i=0; i<lines.size(); i++){
            if(lines.get(i).numint>max){
                max= lines.get(i).numint;
                mindex = i;
            }
        }

        if(max==1){
            for(int i=0; i<lines.size(); i++){
                if(lines.get(i).numint==1){
                    mindex=i;
                }
            }
        }
        out.println(mindex+1);
        out.close();
    }
    public static void intersect(ArrayList<Integer> active, int index,ArrayList<point> x,ArrayList<line> lines){
        int f= active.size();
        boolean between =false;
        boolean between2= false;

        for(int i=0; i<f; i++){
            if(active.get(i)!=index){
                between=false;
                between2=false;
                double xint = Math.abs((lines.get(active.get(i)).inter-lines.get(index).inter)/(lines.get(active.get(i)).sl- lines.get(index).sl));
                double yint = xint*lines.get(index).sl + lines.get(index).inter;

                if(lines.get(index).x2-lines.get(index).x1==0){
                    xint = lines.get(index).x2;
                    yint = lines.get(active.get(i)).sl*(lines.get(index).x2) +lines.get(active.get(i)).inter;
                }
                //System.out.println(xint + " " + yint);

                if(xint<=lines.get(index).x2 && xint>=lines.get(index).x1 && xint<=lines.get(active.get(i)).x2 && xint>=lines.get(active.get(i)).x1){
                    between =true;
                    System.out.println(between);
                }
                if(((yint<=lines.get(index).y2 && yint>=lines.get(index).y1) || ((yint<=lines.get(index).y1 && yint>=lines.get(index).y2)))&& ((yint<=lines.get(active.get(i)).y2 && yint>=lines.get(active.get(i)).y1)||(yint<=lines.get(active.get(i)).y1 && yint>=lines.get(active.get(i)).y2))){
                    between2 = true;
                    System.out.println(between2);
                }
                if(between ==true && between2 ==true){
                    lines.get(index).numint++;
                    lines.get(active.get(i)).numint++;
                }
            }
        }
    }
}
/*class point{
    int x;
    int y;
    double sl;
    double inter;
    int index;
    public point(int xc, int yc,double slope, double intercept, int i){
        x=xc;
        y=yc;
        sl = slope;
        inter = intercept;
        index=i;
    }
    public String toString(){
        return x+ " " + y;
    }
}
class sortByx implements Comparator<point>{
    public int compare(point a, point b){
        return a.x-b.x;
    }
}
class sortByy implements Comparator<point>{
    public int compare(point a, point b){
        return a.y-b.y;
    }
}

class line{
    int x1;
    int y1;
    int x2;
    int y2;
    double sl;
    double inter;
    int index;
    int numint;
    public line(int xc, int yc, int xh, int yh,double slope, double intercept, int i){
        if(xc<xh){
            x1=xc;
            y1=yc;
            x2 = xh;
            y2=yh;}
        else{
            x1=xh;
            y1=yh;
            x2 = xc;
            y2=yc;
        }
        sl = slope;
        inter = intercept;
        index=i;
        numint=0;
    }
}*/

