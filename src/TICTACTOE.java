//TIC TAC TOE IN PROCEDURAL STYLE

import java.util.Scanner;

public class TICTACTOE {

    //VARIABLES
    public static char[][] map;
    public static final int SIZE = 3;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static int COUNTER_OF_TURNS = 1;

    public enum Type {MAIN_DIAGONAL, DIAGONAL, ROW, COLUMN}

    // POPULATE THE MAP WITH DOT_EMPTY
    public static void initMap() {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    //PRINT MAP
    public static void printMap() {
        System.out.print("# ");

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }

        System.out.println();

        for (int i = 0; i < map.length; i++) {

            System.out.print((i + 1) + "|");
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    //CHECK FOR VALID CELL
    public static boolean isCellEmpty(int x, int y) {
        boolean flag = true;
        x--;
        y--;

        if (map[x][y] != DOT_EMPTY) {
            flag = false;
        }
        return flag;
    }

    //CHECK OF WIN
    public static boolean checkOfWin() {
        String checkLineX = "";
        String checkLineO = "";
        String diagonal1 = "";
        String diagonal2 = "";
        String row = "";
        String column = "";
        String draw = "";

        //LINE FOR WIN
        for (int i = 0; i < map.length; i++) {
            checkLineX += DOT_X;
            checkLineO += DOT_O;
        }

        //DIAGONALS
        for (int i = 0; i < map.length; i++) {
            diagonal1 += map[i][i];
            diagonal2 += map[i][map.length - i - 1];
        }

        if (checkLineX.equals(diagonal1) || checkLineX.equals(diagonal2) ||
                checkLineO.equals(diagonal1) || checkLineO.equals(diagonal2)) {
            return true;
        }

        //ROWS AND COLUMNS
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                row += map[i][j];
                column += map[j][i];
                draw += map[i][j];
            }

            if (checkLineX.equals(row) || checkLineX.equals(column) ||
                    checkLineO.equals(row) || checkLineO.equals(column)) {
                return true;
            }

            row = "";
            column = "";
        }

        if (!draw.contains(Character.toString(DOT_EMPTY))) {
            COUNTER_OF_TURNS = -1;
            return true;
        }

        return false;
    }

    //SET Xs AND Os
    public static void setXO(int x, int y, char mark) {
        x--;
        y--;

        if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
            map[x][y] = mark;
        } else {
            System.out.println("Point outside the grid");
        }
    }

    //CHECK POSSIBILITY OF WIN
    public static int[] checkWinCondition(String lineOfMap, Type type, int row) {
        int cntX = 0;
        int cntO = 0;
        int[] point = new int[2];

        for (char c :
                lineOfMap.toCharArray()) {
            if (c == DOT_X) {
                cntX += 1;
            } else if (c == DOT_O) {
                cntO += 1;
            }
        }

         if ((cntX == SIZE - 1 || cntO == SIZE - 1) && lineOfMap.contains(Character.toString(DOT_EMPTY))) {
            if (type == Type.MAIN_DIAGONAL) {
                point[0] = lineOfMap.indexOf(Character.toString(DOT_EMPTY)) + 1;
                point[1] = lineOfMap.indexOf(Character.toString(DOT_EMPTY)) + 1;
            } else if (type == Type.DIAGONAL) {
                if (lineOfMap.indexOf(Character.toString(DOT_EMPTY)) == 0) {
                    point[0] = lineOfMap.indexOf(Character.toString(DOT_EMPTY)) + 1;
                    point[1] = SIZE;
                } else if (lineOfMap.indexOf(Character.toString(DOT_EMPTY)) == 1) {
                    point[0] = lineOfMap.indexOf(Character.toString(DOT_EMPTY)) + 1;
                    point[1] = lineOfMap.indexOf(Character.toString(DOT_EMPTY)) + 1;
                } else {
                    point[0] = SIZE;
                    point[1] = lineOfMap.indexOf(Character.toString(DOT_EMPTY)) - 1;
                }
            } else if (type == Type.ROW) {
                point[0] = row + 1;
                point[1] = lineOfMap.indexOf(Character.toString(DOT_EMPTY)) + 1;
            } else if (type == Type.COLUMN) {
                point[0] = lineOfMap.indexOf(Character.toString(DOT_EMPTY)) + 1;
                point[1] = row + 1;
            }
        }

        return point;
    }

    //HAVE TO STOP HUMAN
    public static int[] checkHumanTurn() {
        int[] point;
        String diagonalMain = "";
        String diagonal = "";
        String row = "";
        String column = "";

        //DIAGONALS
        for (int i = 0; i < map.length; i++) {
            diagonalMain += map[i][i];
            diagonal += map[i][map.length - i - 1];
        }

        point = checkWinCondition(diagonalMain, Type.MAIN_DIAGONAL, 0);
        if (point[0] != 0 && point[1] != 0) {
            return point;
        }

        point = checkWinCondition(diagonal, Type.DIAGONAL, 0);
        if (point[0] != 0 && point[1] != 0) {
            return point;
        }

        //ROWS AND COLUMNS
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                row += map[i][j];
                column += map[j][i];
            }

            point = checkWinCondition(row, Type.ROW, i);

            if (point[0] != 0 && point[1] != 0) {
                return point;
            }

            point = checkWinCondition(column, Type.COLUMN, i);

            if (point[0] != 0 && point[1] != 0) {
                return point;
            }

            row = "";
            column = "";
        }

        return point;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x, y;
        String continueToPlay = "Y";

        System.out.println("This is game TIC TAC TOE. You go first and play crosses (X).\n" +
                "The computer goes second and plays zeroes (O).\n" +
                "In order to make a move, you need to enter the column number and row number through a space. Sample: 2 2.\n\n");

        while (continueToPlay.equals("Y")) {
            initMap();
            printMap();

            while (!checkOfWin()) {
                //HUMAN TURN
                if (COUNTER_OF_TURNS % 2 == 1) {
                    System.out.print("Your turn: ");

                    x = sc.nextInt();
                    y = sc.nextInt();

                    if (isCellEmpty(x, y)) {
                        setXO(x, y, DOT_X);
                    } else {
                        System.out.println("Cell is not empty");
                        continue;
                    }
                }
                // COMPUTER TURN
                else {
                    int[] checkPoint = checkHumanTurn();

                    if (checkPoint[0] == 0 && checkPoint[1] == 0) {
                        x = (int) (1 + Math.random() * SIZE);
                        y = (int) (1 + Math.random() * SIZE);
                    } else {
                        x = checkPoint[0];
                        y = checkPoint[1];
                    }

                    if (isCellEmpty(x, y)) {
                        setXO(x, y, DOT_O);
                    } else {
                        continue;
                    }
                }

                System.out.println();

                printMap();
                COUNTER_OF_TURNS++;
            }

            if (COUNTER_OF_TURNS % 2 == 1) {
                System.out.println("You lost");
            } else if (COUNTER_OF_TURNS % 2 == 0) {
                System.out.println("You won");
            } else {
                System.out.println("Draw");
            }

            System.out.println("Press [Y] to play one more time, or press [N] to exit.");
            continueToPlay = sc.next();

            if (continueToPlay.equals("Y")) {
                COUNTER_OF_TURNS = 1;
            }
        }
    }
}
