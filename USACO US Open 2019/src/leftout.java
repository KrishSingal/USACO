import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class leftout {
    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("leftout.in"));
        //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("leftout.out")));
        //PrintWriter out = new PrintWriter(System.out, true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        int num = Integer.parseInt(k.nextToken());
        char grid[][] = new char[num][num];

        for (int i = 0; i < num; i++) {
            k = new StringTokenizer(f.readLine());
            String s = k.nextToken();
            for (int j = 0; j < num; j++) {
                grid[i][j] = s.charAt(j);
            }
        }
        int times=0;
        while(count(grid,'L')<num*num-1 && times<25) {
            for (int i = 0; i < grid.length; i++) {
                if (countrow(grid, i) > num / 2) {
                    fliprow(i, grid);
                }
            }
            for (int i = 0; i < grid.length; i++) {
                if (countcol(grid, i) > num / 2) {
                    flipcol(i, grid);
                }
            }
            //System.out.print(count(grid, 'L'));
            times++;
        }

        int reti=-1;
        int retj=-1;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid.length; j++){
                if(grid[i][j]!='L'){
                    reti=i;
                    retj=j;
                }
            }
        }
        reti++;
        retj++;
        if(count(grid,'L')==num*num){
            out.println(-1);
            out.close();
        }
        else if(count(grid,'L')<=num*num-1){
            for(int i=0; i<num; i++){
                for(int j=0; j<num; j++){
                    if(grid[i][j]=='R'){
                        out.println((i+1)+ " "+ (j+1));
                        out.close();
                    }
                }
            }
        }

    }

    public static void fliprow(int row, char grid[][]) {
        for (int i = 0; i < grid[row].length; i++) {
            if (grid[row][i] == 'R')
                grid[row][i] = 'L';
            else
                grid[row][i] = 'R';
        }
    }
    public static void flipcol (int col, char grid[][]){
        for(int i=0; i<grid.length; i++){
            if(grid[i][col]=='R'){
                grid[i][col]= 'L';
            }
            else
                grid[i][col]= 'R';
        }
    }

    public static int countrow(char grid[][], int row){
        int count=0;
        for(int i=0; i<grid.length; i++){
            if(grid[row][i]=='R')
               count++;
        }
        return count;
    }

    public static int countcol(char grid[][], int col){
        int count=0;
        for(int i=0; i<grid.length; i++){
            if(grid[i][col]=='R')
                count++;
        }
        return count;
    }
    public static int count(char grid[][], char q){
        int count=0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid.length; j++){
                if(grid[i][j]==q)
                    count++;
            }
        }
        return count;
    }
}


