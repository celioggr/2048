package game2048;

import java.util.Arrays;
import java.util.Scanner;

public class GameEngine {
      Board board = new Board();
      int boardChanged [] []=new int[board.matriz.length][board.matriz.length];
      boolean allowPlay=true,win=false,lost=false,moved=false;
      Scanner in = new Scanner(System.in);
      GameEngine(Board board)
      {
        this.board=board;
      }

      public void start()
      {
        int gameMode=-1;
        printLines(140);
        System.out.println("Bem-vindo ao 2048! Se pretendes usar Lanterna, insere o número 1. Caso apenas pretendas usar o teu terminal, por favor, insere um 0. Bom jogo! :)");
        printLines(140);
        gameMode=in.nextInt();
        while(gameMode!=1 && gameMode!=0)
        {
            System.out.println("Tens que escolher entre 0 ou 1! :)\n Se pretendes usar Lanterna, insere o número 1. Caso apenas pretendas usar o teu terminal, por favor, insere um 0.");
            gameMode=in.nextInt();
        }
        board.start(gameMode);
        copyMatrix(board.matriz,boardChanged);
        while (allowPlay)
        {
          char move = chose(gameMode);
          Movement movement = new Movement();
          switch (move)
          {
              case 'l':
              case 'L': movement=Moves.ArrowLeft(board.matriz);
                        moved=true;
                        break;
              case 'r':
              case 'R': movement=Moves.ArrowRight(board.matriz);
                      moved=true;
                      break;
              case 'u':
              case 'U': movement=Moves.Arrowup(board.matriz);
                  moved=true;
                  break;
              case 'd':
              case 'D': movement=Moves.ArrowDown(board.matriz);
                  moved=true;
                  break;
              default:System.out.println("Direção errada! Usa, por favor, as letras L(Left), R(Right),U(Up),D(Down)");
                      moved=false;

          }
          board.currentScore+=movement.points;
          if(moved && !Arrays.deepEquals(boardChanged,board.matriz)) board.putRandom();
          if(board.lost())
          {
              allowPlay=false;
              lost=true;
              System.out.print("Perdeste...");
          }
          if(gameMode==0) System.out.println("Score: "+board.currentScore);
          copyMatrix(board.matriz,boardChanged);
          board.print(gameMode);
        }
      }

      private char chose(int gameMode)
      {
          char c='1';
          if(gameMode==1)
          {
             c=board.term.moveTerminal();

          }
          else
          {
              c = in.next().charAt(0);
          }
          return c;
      }

      private void printLines(int i)
      {
         for(int j=0;j<i;j++)System.out.print("-");
         System.out.println();
      }

      public boolean won()
      {
           return board.searchIfInMatrix(2048);
      }

      private void copyMatrix(int[][] m1,int[][]m2)
      {
          for (int i = 0; i < m1.length; i++)
          {
              System.arraycopy(m1[i], 0, m2[i], 0, m1[0].length);
          }
      }
}
