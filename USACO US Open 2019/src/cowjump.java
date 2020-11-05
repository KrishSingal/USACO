import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class cowjump {
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
            //x.add(new point(xp, yp,slope,intercept,i));
            //x.add(new point(x2, y2,slope,intercept,i));

        }

        for(int i =0; i<num; i++){
            for(int j=i; j<num; j++){
                intersect(lines.get(i),lines.get(j));
            }
        }
        /*for(int i =0; i<num; i++){
            System.out.println(lines.get(i).numint);
        }*/

        int mindex=0;
        int max=-1;
        int firstone=-1;
        boolean found=false;
        for(int i=0; i<lines.size(); i++){
            if(lines.get(i).numint==1 && found==false ){
                firstone=i;
                found=true;
            }
            if(lines.get(i).numint>max){
                max= lines.get(i).numint;
                mindex = i;
            }
        }

        if(max==1){
            /*for(int i=0; i<lines.size(); i++){
                if(lines.get(i).numint==1){
                    mindex=i;
                    break;
                }
            }*/
            mindex = firstone;
        }
        out.println(mindex+1);
        out.close();

    }
    public static void intersect(line i, line j) {
        boolean between = false;
        boolean between2 = false;

        if (i.sl != j.sl) {
            double xint = Math.abs((i.inter - j.inter) / (i.sl - j.sl));
            double yint = xint * i.sl + i.inter;

            if (j.x2 - j.x1 == 0) {
                xint = j.x2;
                yint = i.sl * (j.x2) + i.inter;
            }
            if (i.x2 - i.x1 == 0) {
                xint = i.x2;
                yint = j.sl * (i.x2) + j.inter;
            }
            //System.out.println(xint + " " + yint);

            if (((xint <= i.x2 && xint >= i.x1) || ((xint <= i.x1 && xint >= i.x2))) && ((xint <= j.x2 && xint >= j.x1) || (xint <= j.x1 && xint >= j.x2))) {
                between = true;
                //System.out.println(between);
            }
            if (((yint <= i.y2 && yint >= i.y1) || ((yint <= i.y1 && yint >= i.y2))) && ((yint <= j.y2 && yint >= j.y1) || (yint <= j.y1 && yint >= j.y2))) {
                between2 = true;
                //System.out.println(between2);
            }
            if (between == true && between2 == true) {
                i.numint++;
                j.numint++;
            }
        }
    }
    }
class point{
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
    long x1;
    long y1;
    long x2;
    long y2;
    double sl;
    double inter;
    int index;
    int numint;
    public line(long xc, long yc, long xh, long yh,double slope, double intercept, int i){
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
}
