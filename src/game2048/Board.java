package game2048;

public class Board {
  int currentScore;
  int[][] matriz = new int[4][4];
  public Lanterna term = new Lanterna(matriz);

    Board()
    {
        currentScore=0;
    }

    Board(int[][]matriz)
    {
        this.matriz=matriz;
    }

    public void start(int gameMode)
    {
        Coord r1 = new Coord(),r2=new Coord();
        r1.putInRandomPosition(this.matriz);
        r2.putInRandomPosition(this.matriz);
        if(gameMode==1)
        {
          term.show();
          term.print(matriz,currentScore,searchIfInMatrix(2048),lost());
        }
        else
        {
            System.out.println("Usa, por favor, as letras L(Left), R(Right),U(Up),D(Down) para jogares! Boa sorte!\n");
            print(gameMode);
            System.out.println("Score: "+this.currentScore);
        }

    }

    public void print(int gameMode)
    {
        if(gameMode==0)
        {
          for(int i=0;i<4;i++)
          {
              for(int j=0;j<4;j++)
              {
                  System.out.printf("%4d",this.matriz[i][j]);
              }
              System.out.println();
          }
        }
        else
        {
            term.print(matriz,currentScore,searchIfInMatrix(2048),lost());
        }
    }

    public void putRandom() {
        Coord rgenerated = new Coord();
        rgenerated.putInRandomPosition(this.matriz);
    }

    public boolean lost() {
         boolean  lost = true;
         for(int i=0;i<this.matriz.length;i++)
         {
             for(int j=0;j<this.matriz.length;j++)
             {
                 if(searchIfInMatrix(0)) lost=false;
                 if(i<3 && j<3 && (matriz[i][j]==matriz[i][j+1]||matriz[i][j]==matriz[i+1][j]))
                 {
                     lost = false;
                 }
             }
         }
        return lost;
    }

    public boolean searchIfInMatrix(int val)
    {
        for(int i= 0; i < 4 ; i++)
            for(int j = 0; j<4; j++ )
                if(matriz[i][j] == val)
                    return true;
        return false;
    }

}
