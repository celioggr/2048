package game2048;

public class Play {
    public static void main(String args[])
    {
       Board letsPlay = new Board();
       GameEngine engine = new GameEngine(letsPlay);
       engine.start();
    }
}
