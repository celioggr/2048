package game2048;

import java.util.LinkedList;
import java.util.Random;

public class Coord {
    int x,y;
    Coord( int x, int y){
        this.x = x;
        this.y = y;
    }
    public Coord() {
        x=0;y=0;
    }

    public void putInRandomPosition(int[][] matrix)
    {
        Random r = new Random();
        LinkedList<Coord> l = new LinkedList<Coord>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0)
                    l.addLast(new Coord(i, j));
            }
        }

        if(l.size()>0)
        {
            int target = r.nextInt(l.size());
            matrix[l.get(target).x][l.get(target).y]=r.nextFloat() < 0.9 ? 2 : 4;
        }
    }
}
