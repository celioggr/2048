package game2048;

public class Moves {

    public static Movement Arrowup(int[][] matrix) {
        Movement movement = new Movement();
        matrix = CCW(matrix);
        matrix = slideLeft(matrix);
        movement = mergeCells(matrix);
        matrix = slideLeft(matrix);
        CW(matrix);
        movement.matrix=matrix;
        return movement;
    }

    public static Movement ArrowDown(int[][] matrix) {
        Movement movement = new Movement();
        matrix = CW(matrix);
        matrix = slideLeft(matrix);
        movement = mergeCells(matrix);
        matrix = slideLeft(matrix);
        CCW(matrix);
        movement.matrix=matrix;
        return movement;
    }

    public static Movement ArrowLeft(int[][] matrix){
        Movement movement = new Movement();
        matrix = slideLeft(matrix);
        movement = mergeCells(matrix);
        slideLeft(matrix); // best effort to put all the numbers to the left ex: 0|2|2|2 will give 4|0|2|0
        // if slideLeft wasn't called again
        movement.matrix=matrix;
        return movement;
    }


    public static Movement ArrowRight(int[][] matrix) {
        Movement movement = new Movement();
        matrix = CCW(matrix);
        matrix = CCW(matrix);
        matrix = slideLeft(matrix);
        movement = mergeCells(matrix);
        matrix = slideLeft(matrix);
        matrix = CW(matrix);
        CW(matrix);
        movement.matrix=matrix;
        return movement;
    }


    public static int[][] slideLeft(int[][] matrix) {
        for (int i = 0; i < 4; i++) {
            int[] row = new int[4];
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j] != 0) {
                    int curr = 0;
                    while (row[curr] != 0) {
                        curr++;
                    }
                    row[curr] = matrix[i][j];
                }
            }
            matrix[i] = row;
        }
        return matrix;
    }

    private static Movement mergeCells(int[][] matrix) {
        Movement movement = new Movement();
        int points = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (matrix[i][j - 1] == matrix[i][j]) {
                    points+= matrix[i][j - 1] *= 2;
                    matrix[i][j] = 0;
                }
            }
        }
        movement.points=points;
        movement.matrix=matrix;
        return movement;
    }

    public static int[][] CCW(int[][] matrix) { // right

        int[][] aux = transpose(matrix);
        int n = matrix.length;

        //  swap rows
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int x = matrix[i][j];
                aux[i][j] = matrix[n - 1 - i][j];
                aux[n - 1 - i][j] = x;
            }
        }
        //Debugg.print(aux);
        return aux;
    }

    public static int[][] CW(int[][] matrix) { // rotate 90 to left counterclockwise

        int[][] aux = transpose(matrix);
        int n = matrix.length;

        // swap columns
        for (int j = 0; j < n / 2; j++) {
            for (int i = 0; i < n; i++) {

                int x = matrix[i][j];
                aux[i][j] = matrix[i][n - 1 - j];
                aux[i][n - 1 - j] = x;
            }
        }
        //Debugg.print(aux);
        return aux;
    }

    public static int[][] transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int aux = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = aux;
            }
        }
        return matrix;
    }
}
