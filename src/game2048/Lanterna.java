package game2048;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

public class Lanterna {

   int matrix[][] = new int[4][4];
   Terminal term= TerminalFacade.createTerminal();
   Lanterna(int[][] matrix) {
       this.matrix=matrix;
       term.clearScreen();
   }

    public void show()
    {
       term.enterPrivateMode();
    }

   public char moveTerminal()
   {
       boolean didit=false;
       char c='1';
       Key k=term.readInput();
      while(!didit)
      {
          if(k!=null)
               {
                  switch (k.getKind())
                  {
                      case ArrowUp: c='U';
                           break;
                      case ArrowDown: c='D';
                          break;
                      case ArrowLeft: c='L';
                          break;
                      case ArrowRight: c='R';
                          break;
                  }
                didit=true;
               }
          else k=term.readInput();
      }
   return c;
   }

    public void print(int[][]matrix,int score,boolean win,boolean lost)
    {

        int x=30,y=10,init=x;
        term.clearScreen();
        for(int i=0;i<matrix.length;i++)
        {
                    for(int j=0;j<matrix.length;j++)
                    {
                        term.moveCursor(x,y);
                        switch (matrix[i][j])
                        {
                            case 0: term.applyBackgroundColor(Terminal.Color.WHITE);
                                term.applyForegroundColor(Terminal.Color.WHITE);break;
                            case 2: term.applyBackgroundColor(Terminal.Color.GREEN); break;
                            case 4: term.applyBackgroundColor(Terminal.Color.CYAN); break;
                            case 8: term.applyBackgroundColor(Terminal.Color.RED); break;
                            case 16: term.applyBackgroundColor(Terminal.Color.BLUE); break;
                            case 32: term.applyBackgroundColor(Terminal.Color.YELLOW); break;
                            case 64: term.applyBackgroundColor(Terminal.Color.MAGENTA); break;
                            default: term.applyBackgroundColor(Terminal.Color.MAGENTA); break;
                        }
                        String ss = String.format("%4d ",matrix[i][j]);
                        for(int k=0; k<ss.length(); k++)
                            term.putCharacter(ss.charAt(k));
                        x+=ss.length()+1;
                    }
            x=init;
            y+=2;

        }
        term.moveCursor(init,y+3);
        term.setCursorVisible(false);
        term.applyBackgroundColor(Terminal.Color.BLACK);
        term.applyForegroundColor(Terminal.Color.WHITE);
        String sf = "Score: "+score;
        for(int k=0; k<sf.length(); k++)
            term.putCharacter(sf.charAt(k));

        term.flush();

        if(win)
        {
            term.moveCursor(init+15,y+3);
            String s="Parabéns! Ganhaste!";
            for(int k=0; k<s.length(); k++)
                term.putCharacter(s.charAt(k));

        }

        if(lost)
        {
            term.moveCursor(init+15,y+3);
            String s="Perdeste... Melhor sorte para a próxima!";
            for(int k=0; k<s.length(); k++)
                term.putCharacter(s.charAt(k));
        }

    }
}

